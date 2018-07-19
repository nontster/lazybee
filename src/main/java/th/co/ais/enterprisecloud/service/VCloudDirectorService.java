package th.co.ais.enterprisecloud.service;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.vmware.vcloud.api.rest.schema.ComposeVAppParamsType;
import com.vmware.vcloud.api.rest.schema.FirewallRuleType;
import com.vmware.vcloud.api.rest.schema.FirewallServiceType;
import com.vmware.vcloud.api.rest.schema.GatewayNatRuleType;
import com.vmware.vcloud.api.rest.schema.NatRuleType;
import com.vmware.vcloud.api.rest.schema.NatServiceType;
import com.vmware.vcloud.api.rest.schema.NetworkConnectionType;
import com.vmware.vcloud.api.rest.schema.ReferenceType;
import com.vmware.vcloud.sdk.Expression;
import com.vmware.vcloud.sdk.Filter;
import com.vmware.vcloud.sdk.QueryParams;
import com.vmware.vcloud.sdk.ReferenceResult;
import com.vmware.vcloud.sdk.Task;
import com.vmware.vcloud.sdk.VCloudException;
import com.vmware.vcloud.sdk.VM;
import com.vmware.vcloud.sdk.Vapp;
import com.vmware.vcloud.sdk.VcloudClient;
import com.vmware.vcloud.sdk.Vdc;
import com.vmware.vcloud.sdk.VirtualDisk;
import com.vmware.vcloud.sdk.admin.AdminOrganization;
import com.vmware.vcloud.sdk.admin.AdminVdc;
import com.vmware.vcloud.sdk.admin.EdgeGateway;
import com.vmware.vcloud.sdk.admin.ExternalNetwork;
import com.vmware.vcloud.sdk.admin.VcloudAdmin;
import com.vmware.vcloud.sdk.constants.Version;
import com.vmware.vcloud.sdk.constants.query.ExpressionType;
import com.vmware.vcloud.sdk.constants.query.QueryReferenceField;
import com.vmware.vcloud.sdk.constants.query.QueryReferenceType;

import th.co.ais.enterprisecloud.domain.VmType;
import th.co.ais.enterprisecloud.exception.InvalidParameterException;
import th.co.ais.enterprisecloud.utils.NetworkUtils;
import th.co.ais.enterprisecloud.utils.OrgUtils;
import th.co.ais.enterprisecloud.utils.UserUtils;
import th.co.ais.enterprisecloud.utils.VappUtils;
import th.co.ais.enterprisecloud.utils.VcloudProperties;
import th.co.ais.enterprisecloud.utils.VdcUtils;

@Service
@Profile("!dev")
public class VCloudDirectorService implements CloudService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private VcloudProperties prop;
	private VcloudClient client;
	private VcloudAdmin admin;
	private AdminOrganization adminOrg;	
	private AdminVdc adminVdc;
	private EdgeGateway edgeGateway;
	
	private OrgUtils orgUtils;
	private VdcUtils vdcUtils;
	private UserUtils userUtils;
	private NetworkUtils networkUtils;
	private VappUtils vappUtils;

	@Autowired
	public VCloudDirectorService(OrgUtils orgUtils, VdcUtils vdcUtils, UserUtils userUtils, NetworkUtils networkUtils,
			VappUtils vappUtils, VcloudProperties prop) {
		super();
		this.orgUtils = orgUtils;
		this.vdcUtils = vdcUtils;
		this.userUtils = userUtils;
		this.networkUtils = networkUtils;
		this.vappUtils = vappUtils;
		this.prop = prop;
	}

	@Override
	public void connect() throws VCloudException {
		
		logger.debug("username:password - " + prop.getUsername() + ":" + prop.getPassword());
		this.client.login(prop.getUsername(), prop.getPassword());
		logger.info("Success sigin to vCloud Director : "+prop.getUrl());
		
		this.admin = client.getVcloudAdmin();
		logger.info("Get Vcloud Admin : "+admin.getResource().getHref());
	}


	@Override
	public void disconnect() throws VCloudException {
		this.client.logout();	
		this.client = null;
		this.admin = null;
		this.adminOrg = null;
		this.adminVdc = null;
		this.edgeGateway = null;
		
		logger.info("disconnected from vcloud director");
	}
	
	@Async
	public Future<th.co.ais.enterprisecloud.domain.response.OrganizationType> provisioning(th.co.ais.enterprisecloud.domain.OrganizationType org) throws VCloudException, TimeoutException {
		// TODO Auto-generated method stub
	
		th.co.ais.enterprisecloud.domain.response.OrganizationType res = new th.co.ais.enterprisecloud.domain.response.OrganizationType();
	
		if(this.client == null){		
			this.client = new VcloudClient(prop.getUrl(), Version.V5_5);
			connect();
		} else if(!this.client.extendSession()){
			connect();
		}
		
		adminOrg = admin.createAdminOrg(orgUtils.createNewAdminOrgType(org));
		Task task = orgUtils.returnTask(client, adminOrg);
		if (task != null)
			task.waitForTask(0);
		logger.info("Add New Organization : "+adminOrg.getResource().getName()+" - "+adminOrg.getResource().getHref());
		logger.info("Organization Id: "+adminOrg.getResource().getId());
		// Set vCloud director URL for organization		
		String vcdurl = ((prop.getUrl().length() - 1)!= '/')? prop.getUrl()+'/' : prop.getUrl();		
		org.setUrl(vcdurl + "cloud/org/" + org.getName() + "/");

		// Create vDC You may end using one of the following.
		adminVdc = this.vdcUtils.addPayAsYouGoVdc(org, admin, client, adminOrg, prop);

		// Create user on the organization
		this.userUtils.addUserToOrg(org, admin, adminOrg);
					
		// Create org vdc networks on the organizaiton
		edgeGateway = networkUtils.addNatRoutedOrgVdcNetwork(client, org, adminVdc, adminOrg, prop);

		// find the vdc ref
		Vdc vdc = Vdc.getVdcByReference(client, adminVdc.getVdcReference());

		// find the vapp template ref
		String catalogName = null;

		if (org.getCloudResources() != null && org.getCloudResources().getCatalog() != null
				&& org.getCloudResources().getCatalog().getName() != null)
			catalogName = org.getCloudResources().getCatalog().getName();
		else
			catalogName = prop.getCatalog();

		// Composed vApp.
		if (org.getvApp() != null && org.getvApp().getName() != null)
			logger.info("vApp : " + org.getvApp().getName());
		else
			logger.info("vApp : vApp_system_1");

		ComposeVAppParamsType composeParams = vappUtils.createComposeParams(client, org, catalogName, vdc);
		Vapp vapp = vdc.composeVapp(composeParams);

		List<Task> tasks = vapp.getTasks();
		if (tasks.size() > 0)
			tasks.get(0).waitForTask(0);

		// refresh the vapp
		vapp = Vapp.getVappByReference(client, vapp.getReference());

		logger.info("Composing vApp : "+vapp.getResource().getName()+" - "+vapp.getResource().getHref());

		// reconfigure Vms
		vappUtils.reconfigureVms(vapp, org);

		// generate report
		//ReportUtils.generateReport(client, vapp, edgeGateway, vCloudOrg, adminVdc.getResource().getName(), output);
		
		res = prepareResponse(org, edgeGateway, vapp, adminVdc.getResource().getName());
		
		logger.info("Provisioning completed!");
				
		//disconnect();
				
		return new AsyncResult<>(res);
	}

	@Override
	public th.co.ais.enterprisecloud.domain.response.OrganizationType prepareResponse(
			th.co.ais.enterprisecloud.domain.OrganizationType org, EdgeGateway edgeGateway, Vapp vapp, String orgVdcName) throws VCloudException {
		// TODO Auto-generated method stub
		
		th.co.ais.enterprisecloud.domain.response.OrganizationType res = new th.co.ais.enterprisecloud.domain.response.OrganizationType();
		
		res.setName(org.getName());
		res.setOrderId(org.getOrderId());
		res.setUrl(org.getUrl());
		
		th.co.ais.enterprisecloud.domain.response.NetworkServicesType rNetworkServices = new th.co.ais.enterprisecloud.domain.response.NetworkServicesType();
		th.co.ais.enterprisecloud.domain.response.FirewallServiceType rFirewallService = new th.co.ais.enterprisecloud.domain.response.FirewallServiceType();
		th.co.ais.enterprisecloud.domain.response.FirewallRulesType rFirewallRules = new th.co.ais.enterprisecloud.domain.response.FirewallRulesType();
				
		FirewallServiceType fs = networkUtils.getFirewallService(edgeGateway);
		
		for (FirewallRuleType fwRule : fs.getFirewallRule()) {			
			th.co.ais.enterprisecloud.domain.response.FirewallRuleType rFirewallRule = new th.co.ais.enterprisecloud.domain.response.FirewallRuleType();
			
			String protocol = null;
			String sourcePort = ((fwRule.getSourcePort() == -1)? "Any" : fwRule.getSourcePort().toString());
						
			if(fwRule.getProtocols().isAny() != null && fwRule.getProtocols().isAny())
				protocol = "Any";		
			if(fwRule.getProtocols().isIcmp() != null && fwRule.getProtocols().isIcmp())
				protocol = "ICMP";			
			if(fwRule.getProtocols().isTcp() != null && fwRule.getProtocols().isTcp()){
				protocol = "TCP";
			}			
			if(fwRule.getProtocols().isUdp() != null && fwRule.getProtocols().isUdp()){
				protocol = ((protocol != null && protocol.equalsIgnoreCase("TCP"))? "TCP & UDP" : "UDP");
			}
			
			rFirewallRule.setName(fwRule.getDescription());
			rFirewallRule.setSourceIp(fwRule.getSourceIp());
			rFirewallRule.setSourcePort(sourcePort);
			rFirewallRule.setDestinationIp(fwRule.getDestinationIp());
			rFirewallRule.setDestinationPortRange(fwRule.getDestinationPortRange());
			rFirewallRule.setProtocol(protocol);
	
			rFirewallRules.getRule().add(rFirewallRule);
		}
		
		rFirewallService.setRules(rFirewallRules);		
		rNetworkServices.setFirewallService(rFirewallService);
		
		th.co.ais.enterprisecloud.domain.response.NatServiceType rNatService = new th.co.ais.enterprisecloud.domain.response.NatServiceType();
		
		// NAT report section
		NatServiceType ns = networkUtils.getNatService(edgeGateway);
		th.co.ais.enterprisecloud.domain.response.NatRulesType rNatRules = new th.co.ais.enterprisecloud.domain.response.NatRulesType();

		for (NatRuleType natRule : ns.getNatRule()) {

			th.co.ais.enterprisecloud.domain.response.NatRuleType rNatRule = new th.co.ais.enterprisecloud.domain.response.NatRuleType();
			
			String natDescription = (natRule.getDescription() != null) ? natRule.getDescription() : "";
			String natRuleType = (natRule.getRuleType() != null) ? natRule.getRuleType() : "";
			String interfaceName = null;
			String originalIp = null;
			String translatedIp = null;
			String originalPort = null;
			String translatedPort = null;
			String protocol = null;

			if (natRule.getGatewayNatRule() != null) {
				GatewayNatRuleType gwNatRule = natRule.getGatewayNatRule();

				ExternalNetwork externalNet = ExternalNetwork.getExternalNetworkByReference(client,
						gwNatRule.getInterface());
				interfaceName = (externalNet.getResource() != null) ? externalNet.getResource().getName() : "";

				originalIp = (gwNatRule.getOriginalIp() != null) ? gwNatRule.getOriginalIp() : "";
				translatedIp = (gwNatRule.getTranslatedIp() != null) ? gwNatRule.getTranslatedIp() : "";
				originalPort = (gwNatRule.getOriginalPort() != null) ? gwNatRule.getOriginalPort() : "any";
				translatedPort = (gwNatRule.getTranslatedPort() != null) ? gwNatRule.getTranslatedPort() : "any";
				protocol = (gwNatRule.getProtocol() != null) ? gwNatRule.getProtocol() : "any";
				
				rNatRule.setDescription(natDescription);
				rNatRule.setNetworkName(interfaceName);
				rNatRule.setOriginalIp(originalIp);
				rNatRule.setOriginalPort(originalPort);
				rNatRule.setProtocol(protocol);
				rNatRule.setTranslatedIp(translatedIp);
				rNatRule.setTranslatedPort(translatedPort);
				rNatRule.setType(natRuleType);
			}

			rNatRules.getRule().add(rNatRule);

		}	
		
		rNatService.setRules(rNatRules);		
		rNetworkServices.setNatService(rNatService);
		
		res.setNetworkServices(rNetworkServices);		
		res.setOrgVdcName(orgVdcName);

		// Transfering Vms section
		th.co.ais.enterprisecloud.domain.response.VmsType rVms = new th.co.ais.enterprisecloud.domain.response.VmsType();
				
		for(VM vm: vapp.getChildrenVms()){
			
			th.co.ais.enterprisecloud.domain.response.VmType rVm = new th.co.ais.enterprisecloud.domain.response.VmType();
			
			rVm.setVmName(vm.getResource().getName());
			rVm.setCoresPerSocket(vm.getCpu().getCoresPerSocket());
			rVm.setNoOfCpus(vm.getCpu().getNoOfCpus());
			
			BigInteger memSize = vm.getMemory().getMemorySize();
			memSize = memSize.divide(BigInteger.valueOf(1024));

			rVm.setMemorySize(memSize.intValue());
			
			List <VirtualDisk> disks = vm.getDisks();
			for (VirtualDisk disk : disks) {
				if (disk.isHardDisk()) {
					BigInteger diskSize = disk.getHardDiskSize();
					diskSize = diskSize.divide(BigInteger.valueOf(1024));
					rVm.setStorageSize(diskSize.intValue());
				}
			}
	
			// Set non-mobile number
			for(VmType orgVm : org.getvApp().getVms()){
				if(vm.getResource().getName().equalsIgnoreCase(orgVm.getName())){
					rVm.setNonMobileNumber(orgVm.getNonMobileNo());
				}
			}
			
			Credential cred = getCredential(vm);
			
			rVm.setOsName(vm.getOperatingSystemSection().getDescription().getValue());
			rVm.setUsername(cred.getUser());
			rVm.setPassword(cred.getPassword());
			
			// FIXME: need the better way to handle network connection in response message
			for(NetworkConnectionType networkConnectionType : vm.getNetworkConnections()){
				rVm.setIpAddress(networkConnectionType.getIpAddress());
			}
			
			rVms.getVm().add(rVm);
		}

		res.setVms(rVms);
		
		th.co.ais.enterprisecloud.domain.response.UsersType rUsers = new th.co.ais.enterprisecloud.domain.response.UsersType();
		th.co.ais.enterprisecloud.domain.response.CredentialType rCredential = new th.co.ais.enterprisecloud.domain.response.CredentialType();
				
		rCredential.setUsername(org.getUser().getName());
		rCredential.setPassword(org.getUser().getPassword());
		
		rUsers.setUser(rCredential);
		
		res.setUsers(rUsers);
				
		return res;
	}

	Credential getCredential(VM vm) throws VCloudException {
	      String user = "root";
	      if (vm.getOperatingSystemSection() != null && vm.getOperatingSystemSection().getDescription() != null
	               && vm.getOperatingSystemSection().getDescription().getValue().indexOf("Windows") >= 0)
	         user = "Administrator";
	      String password = null;
	      if (vm.getGuestCustomizationSection() != null)
	         password = vm.getGuestCustomizationSection().getAdminPassword();
	      return new Credential(user, password);
	   }

	public VcloudAdmin getAdmin() {
		return admin;
	}

	public AdminOrganization getAdminOrg() {
		return adminOrg;
	}

	@Override
	public Future<Boolean> findOrgByName(String orgName) throws VCloudException {

		if(!orgName.matches("^[a-zA-Z0-9_\\-]*$"))
			throw new InvalidParameterException("Allow only Alphabet, Number, Minus and Underscore");
						
		if(orgName.length() > 10)
			throw new InvalidParameterException("name parameter cannot logner than 10 characters");
		
		AdminOrganization checkedAdminOrg = null;
		Boolean existStatus = Boolean.FALSE;

		if(!this.client.extendSession())
			connect();
		
		QueryParams<QueryReferenceField> params = new QueryParams<QueryReferenceField>();
		Filter filter = new Filter(new Expression(QueryReferenceField.NAME, orgName, ExpressionType.EQUALS));
		params.setFilter(filter);
      
		ReferenceResult result  = client.getQueryService().queryReferences(QueryReferenceType.ORGANIZATION, params);
		
		for (ReferenceType orgReference : result.getReferences()) {
			checkedAdminOrg = AdminOrganization.getAdminOrgById(client, orgReference.getId());
			System.out.println(checkedAdminOrg.getResource().getName());
		}
					
		if(checkedAdminOrg != null){
			existStatus = Boolean.TRUE;					
		}
					
		//disconnect();
		
		return new AsyncResult<>(existStatus);
	}
}
