package th.co.ais.enterprisecloud.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.vmware.vcloud.api.rest.schema.ComposeVAppParamsType;
import com.vmware.vcloud.api.rest.schema.FirewallRuleType;
import com.vmware.vcloud.api.rest.schema.FirewallServiceType;
import com.vmware.vcloud.api.rest.schema.GatewayNatRuleType;
import com.vmware.vcloud.api.rest.schema.NatRuleType;
import com.vmware.vcloud.api.rest.schema.NatServiceType;
import com.vmware.vcloud.api.rest.schema.NetworkConnectionType;
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

import th.co.ais.enterprisecloud.domain.OrderType;
import th.co.ais.enterprisecloud.domain.OrganizationType;
import th.co.ais.enterprisecloud.domain.VmType;
import th.co.ais.enterprisecloud.utils.NetworkUtils;
import th.co.ais.enterprisecloud.utils.OrgUtils;
import th.co.ais.enterprisecloud.utils.UserUtils;
import th.co.ais.enterprisecloud.utils.VappUtils;
import th.co.ais.enterprisecloud.utils.VcloudProperties;
import th.co.ais.enterprisecloud.utils.VdcUtils;

@Component
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

	@PostConstruct
	@Override
	public void connect() throws VCloudException {
		// TODO Auto-generated method stub
		this.client = new VcloudClient(prop.getUrl(), Version.V5_5);

		this.client.login(prop.getUsername(), prop.getPassword());
		logger.info("Success sigin to vCloud Director : "+prop.getUrl());
		
		this.admin = client.getVcloudAdmin();
		logger.info("Get Vcloud Admin : "+admin.getResource().getHref());
	}
	
	@Async
	public Future<th.co.ais.enterprisecloud.domain.response.OrganizationType> provisioning(th.co.ais.enterprisecloud.domain.OrganizationType org) throws VCloudException, TimeoutException {
		// TODO Auto-generated method stub
	
		th.co.ais.enterprisecloud.domain.response.OrganizationType res = new th.co.ais.enterprisecloud.domain.response.OrganizationType();
	
		adminOrg = admin.createAdminOrg(orgUtils.createNewAdminOrgType(org));
		Task task = orgUtils.returnTask(client, adminOrg);
		if (task != null)
			task.waitForTask(0);
		logger.info("Add New Organization : "+adminOrg.getResource().getName()+" - "+adminOrg.getResource().getHref());
		
		// Set vCloud director URL for organization
		org.setUrl(prop.getUrl() + "cloud/org/" + org.getName() + "/");

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
				
		
		
		return new AsyncResult<>(res);
	}


	@Override
	public OrganizationType transfer(th.co.ais.enterprisecloud.domain.request.OrganizationType in) {
		// TODO Auto-generated method stub
		logger.debug(in.toString());
		OrganizationType out = new OrganizationType();
				
		out.setCaNumber(in.getCaNumber());
		out.setName(in.getName());
		out.setShortName(in.getShortName());
		out.setOrderId(in.getOrderId());		
		out.setOrderType(OrderType.valueOf(in.getOrderType().toUpperCase()));
		
		// Transfer UserType object
		th.co.ais.enterprisecloud.domain.UserType inUser = new th.co.ais.enterprisecloud.domain.UserType();	
		inUser.setEmailAddress(in.getUsers().getUser().get(0).getEmail());
		inUser.setFullName(in.getUsers().getUser().get(0).getFullName());
		inUser.setPhone(in.getUsers().getUser().get(0).getPhone());
				
		th.co.ais.enterprisecloud.domain.UserType outUser = new th.co.ais.enterprisecloud.domain.UserType();
		outUser.setEmailAddress(inUser.getEmailAddress());
		outUser.setFullName(inUser.getFullName());
		outUser.setPhone(inUser.getPhone());
		
		out.setUser(outUser);

		// Transfer VmsType
		List <th.co.ais.enterprisecloud.domain.request.VmType> inVms = new ArrayList<th.co.ais.enterprisecloud.domain.request.VmType>();
		inVms = in.getVms().getVm();
		
		th.co.ais.enterprisecloud.domain.VAppType outVapp = new th.co.ais.enterprisecloud.domain.VAppType();
		
		List <VmType> outVms = new ArrayList<VmType>(); 
		
		for(th.co.ais.enterprisecloud.domain.request.VmType inVm :inVms){
			
			th.co.ais.enterprisecloud.domain.VmType outVm = new th.co.ais.enterprisecloud.domain.VmType();
			
			outVm.setName(inVm.getVmName());			
			outVm.setComputerName(inVm.getComputerName());
			
			if(inVm.getStart() != null)
				outVm.setStartDate(inVm.getStart().toGregorianCalendar().getTime());
			
			if(inVm.getEnd() != null)
			outVm.setEndDate(inVm.getEnd().toGregorianCalendar().getTime());	
			
			outVm.setTemplateType(inVm.getOsImageName());
			outVm.setNonMobileNo(inVm.getNonMobileNumber());
			
			th.co.ais.enterprisecloud.domain.VCpuType vCpu = new th.co.ais.enterprisecloud.domain.VCpuType();
			vCpu.setCoresPerSocket(inVm.getCoresPerSocket());
			vCpu.setNoOfCpus(inVm.getNoOfCpus());			
			outVm.setvCpu(vCpu);
			
			th.co.ais.enterprisecloud.domain.VMemoryType vMemory = new th.co.ais.enterprisecloud.domain.VMemoryType();
			vMemory.setMemorySize( BigInteger.valueOf(inVm.getMemorySize()));
			outVm.setvMemory(vMemory);
			
			if(inVm.getStorageSize() != null)
				outVm.setStorageSize(Integer.valueOf(inVm.getStorageSize()));
				
			outVms.add(outVm);
		}

		outVapp.setVms(outVms);
		out.setvApp(outVapp);
		
		return out;
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
				rNatRule.setProtocol(translatedIp);
				rNatRule.setTranslatedIp(translatedPort);
				rNatRule.setTranslatedPort(protocol);
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
}
