package th.co.ais.enterprisecloud.service;

import java.math.BigInteger;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.vmware.vcloud.sdk.VCloudException;
import com.vmware.vcloud.sdk.Vapp;
import com.vmware.vcloud.sdk.admin.EdgeGateway;
import th.co.ais.enterprisecloud.domain.OrganizationType;
import th.co.ais.enterprisecloud.domain.VmType;
import th.co.ais.enterprisecloud.utils.NetworkUtils;

@Service
@Profile("dev")
public class DevService implements CloudService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private NetworkUtils networkUtils;
		
	public DevService(NetworkUtils networkUtils) {
		super();
		this.networkUtils = networkUtils;
	}

	@Override
	public void connect() throws VCloudException {
		// TODO Auto-generated method stub
		logger.info("DevService.connect() called");
	}

	@Override
	public void disconnect() throws VCloudException {
		// TODO Auto-generated method stub
		logger.info("DevService.disconnect() called");
		logger.info("disconnected from vcloud director");
	}

	@Override
	public th.co.ais.enterprisecloud.domain.response.OrganizationType prepareResponse(OrganizationType org,
			EdgeGateway edgeGateway, Vapp vapp, String orgVdcName) throws VCloudException {
		// TODO Auto-generated method stub
		return null;
	}

	@Async
	public Future<th.co.ais.enterprisecloud.domain.response.OrganizationType> provisioning(OrganizationType in)
			throws VCloudException, TimeoutException {
		
		logger.info("DevService.provisioning() called");
		
		th.co.ais.enterprisecloud.domain.response.OrganizationType res = new th.co.ais.enterprisecloud.domain.response.OrganizationType();
		
		StringBuffer orgName = new StringBuffer();	
		
		if(in.getOrderType().name().equalsIgnoreCase("trial"))
			orgName.append("Trial-");	
		orgName.append(in.getShortName());
		
		res.setName(orgName.toString());
		res.setOrderId(in.getOrderId());
		res.setUrl("https://enterprisecloud.ais.co.thcloud/org/"+orgName.toString()+"/");
		
		th.co.ais.enterprisecloud.domain.response.NetworkServicesType rNetworkServices = new th.co.ais.enterprisecloud.domain.response.NetworkServicesType();
		th.co.ais.enterprisecloud.domain.response.FirewallServiceType rFirewallService = new th.co.ais.enterprisecloud.domain.response.FirewallServiceType();
		th.co.ais.enterprisecloud.domain.response.FirewallRulesType rFirewallRules = new th.co.ais.enterprisecloud.domain.response.FirewallRulesType();
				
		th.co.ais.enterprisecloud.domain.response.FirewallRuleType rFirewallRule1 = new th.co.ais.enterprisecloud.domain.response.FirewallRuleType();
		th.co.ais.enterprisecloud.domain.response.FirewallRuleType rFirewallRule2 = new th.co.ais.enterprisecloud.domain.response.FirewallRuleType();

		rFirewallRule1.setName("ICMP IN");
		rFirewallRule1.setSourceIp("Any");
		rFirewallRule1.setSourcePort("Any");
		rFirewallRule1.setDestinationIp("internal");
		rFirewallRule1.setProtocol("ICMP");
		
		rFirewallRule2.setName("HTTP OUT");
		rFirewallRule2.setSourceIp("10.1.1.0/24");
		rFirewallRule2.setSourcePort("Any");
		rFirewallRule2.setDestinationIp("Any");
		rFirewallRule2.setDestinationPortRange("80");
		rFirewallRule2.setProtocol("TCP");
	
		rFirewallRules.getRule().add(rFirewallRule1);
		rFirewallRules.getRule().add(rFirewallRule2);
				
		rFirewallService.setRules(rFirewallRules);		
		rNetworkServices.setFirewallService(rFirewallService);
		
		th.co.ais.enterprisecloud.domain.response.NatServiceType rNatService = new th.co.ais.enterprisecloud.domain.response.NatServiceType();
		
		// NAT report section
		th.co.ais.enterprisecloud.domain.response.NatRulesType rNatRules = new th.co.ais.enterprisecloud.domain.response.NatRulesType();

		th.co.ais.enterprisecloud.domain.response.NatRuleType rNatRule1 = new th.co.ais.enterprisecloud.domain.response.NatRuleType();
		th.co.ais.enterprisecloud.domain.response.NatRuleType rNatRule2 = new th.co.ais.enterprisecloud.domain.response.NatRuleType();

		rNatRule1.setDescription("SNAT Rule");
		rNatRule1.setNetworkName("Tenant-External-Internet03");
		rNatRule1.setOriginalIp("10.1.1.11");
		rNatRule1.setOriginalPort("Any");
		rNatRule1.setProtocol("Any");
		rNatRule1.setTranslatedIp("103.20.205.250");
		rNatRule1.setTranslatedPort("Any");
		rNatRule1.setType("SNAT");
		
		rNatRule2.setDescription("DNAT Rule");
		rNatRule2.setNetworkName("Tenant-External-Internet03");
		rNatRule2.setOriginalIp("103.20.205.250");
		rNatRule2.setOriginalPort("Any");
		rNatRule2.setProtocol("Any");
		rNatRule2.setTranslatedIp("10.1.1.11");
		rNatRule2.setTranslatedPort("Any");
		rNatRule2.setType("DNAT");
											
		rNatRules.getRule().add(rNatRule1);
		rNatRules.getRule().add(rNatRule2);
		
		rNatService.setRules(rNatRules);		
		rNetworkServices.setNatService(rNatService);
		
		res.setNetworkServices(rNetworkServices);		
		
		
		res.setOrgVdcName(orgName.toString()+"-vdc");
		
		// Transfering Vms section
		th.co.ais.enterprisecloud.domain.response.VmsType rVms = new th.co.ais.enterprisecloud.domain.response.VmsType();
				
		long priStart = networkUtils.ipToLong("10.1.1.11");
		
		int i =1;
		for(VmType vm: in.getvApp().getVms()){
			
			th.co.ais.enterprisecloud.domain.response.VmType rVm = new th.co.ais.enterprisecloud.domain.response.VmType();
			
			rVm.setVmName(orgName.append("-").append("VM").append(i).toString());
			rVm.setCoresPerSocket(vm.getvCpu().getCoresPerSocket());
			rVm.setNoOfCpus(vm.getvCpu().getNoOfCpus());
			
			BigInteger memSize = vm.getvMemory().getMemorySize();
		
			rVm.setMemorySize(memSize.intValue());
			rVm.setStorageSize(vm.getStorageSize());
				
			rVm.setOsName(vm.getTemplateType());
			rVm.setUsername("root");
			rVm.setPassword("x3eaGq4K");			
			rVm.setIpAddress(networkUtils.longToIp(priStart));
				
			priStart = priStart + 1;
			
			rVms.getVm().add(rVm);
		}

		res.setVms(rVms);
		
		th.co.ais.enterprisecloud.domain.response.UsersType rUsers = new th.co.ais.enterprisecloud.domain.response.UsersType();
		th.co.ais.enterprisecloud.domain.response.CredentialType rCredential = new th.co.ais.enterprisecloud.domain.response.CredentialType();
				
		rCredential.setUsername(in.getShortName()+"_admin");
		rCredential.setPassword("98XUz5ek");		
		rUsers.setUser(rCredential);
		
		res.setUsers(rUsers);		
				
		return new AsyncResult<>(res);
	}

	@Override
	public Future<Boolean> findOrgByName(String orgName) {
		// TODO Auto-generated method stub
		logger.debug("findOrgByName(orgName) called, orgName="+ orgName);
						
		return new AsyncResult<>(Boolean.FALSE);
	}

}
