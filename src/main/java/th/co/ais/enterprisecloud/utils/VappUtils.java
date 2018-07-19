package th.co.ais.enterprisecloud.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vmware.vcloud.api.rest.schema.ComposeVAppParamsType;
import com.vmware.vcloud.api.rest.schema.GuestCustomizationSectionType;
import com.vmware.vcloud.api.rest.schema.InstantiationParamsType;
import com.vmware.vcloud.api.rest.schema.NetworkConfigSectionType;
import com.vmware.vcloud.api.rest.schema.NetworkConfigurationType;
import com.vmware.vcloud.api.rest.schema.NetworkConnectionSectionType;
import com.vmware.vcloud.api.rest.schema.NetworkConnectionType;
import com.vmware.vcloud.api.rest.schema.ObjectFactory;
import com.vmware.vcloud.api.rest.schema.QueryResultAdminVAppTemplateRecordType;
import com.vmware.vcloud.api.rest.schema.ReferenceType;
import com.vmware.vcloud.api.rest.schema.SourcedCompositionItemParamType;
import com.vmware.vcloud.api.rest.schema.VAppNetworkConfigurationType;
import com.vmware.vcloud.api.rest.schema.VmType;
import com.vmware.vcloud.api.rest.schema.ovf.MsgType;
import com.vmware.vcloud.api.rest.schema.ovf.SectionType;
import com.vmware.vcloud.sdk.Expression;
import com.vmware.vcloud.sdk.Filter;
import com.vmware.vcloud.sdk.QueryParams;
import com.vmware.vcloud.sdk.RecordResult;
import com.vmware.vcloud.sdk.VCloudException;
import com.vmware.vcloud.sdk.VM;
import com.vmware.vcloud.sdk.Vapp;
import com.vmware.vcloud.sdk.VappTemplate;
import com.vmware.vcloud.sdk.VcloudClient;
import com.vmware.vcloud.sdk.Vdc;
import com.vmware.vcloud.sdk.VirtualCpu;
import com.vmware.vcloud.sdk.VirtualDisk;
import com.vmware.vcloud.sdk.VirtualMemory;
import com.vmware.vcloud.sdk.constants.FenceModeValuesType;
import com.vmware.vcloud.sdk.constants.IpAddressAllocationModeType;
import com.vmware.vcloud.sdk.constants.query.ExpressionType;
import com.vmware.vcloud.sdk.constants.query.QueryAdminVAppTemplateField;
import com.vmware.vcloud.sdk.constants.query.QueryRecordType;
import th.co.ais.enterprisecloud.domain.OrderType;
import th.co.ais.enterprisecloud.domain.OrganizationType;
import th.co.ais.enterprisecloud.exception.MissingVmTemplateException;
import th.co.ais.enterprisecloud.exception.VdcNetworkNotAvailableException;

@Component
public class VappUtils {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Create the compose vapp params. Creating compose vapp params containing
	 * the vapp templates vms. The same vm is added 3 times with different
	 * names.
	 * 
	 * @param vappTemplateRef
	 * @param vdc
	 * @return
	 * @throws VCloudException
	 * @throws MissingVmTemplateException 
	 * @throws VdcNetworkNotAvailableException 
	 */

	public ComposeVAppParamsType createComposeParams(VcloudClient client, OrganizationType org, String catalogName,
			Vdc vdc) throws VCloudException, MissingVmTemplateException, VdcNetworkNotAvailableException {

		// Get the href of the OrgNetwork to which we can connect the vApp network
		NetworkConfigurationType networkConfigurationType = new NetworkConfigurationType();
		if (vdc.getResource().getAvailableNetworks().getNetwork().size() == 0)
			throw new VdcNetworkNotAvailableException("At least 1 Vdc network required");
		
		// Specify the NetworkConfiguration for the vApp network
		logger.info("Setting vApp ParantNetwork: "+ vdc.getResource().getAvailableNetworks().getNetwork().get(0).getName());
		networkConfigurationType.setParentNetwork(vdc.getResource().getAvailableNetworks().getNetwork().get(0));
		
		// FIXME: use NATROUTED and change BRIDGED later to solve vCloud Director bug
		networkConfigurationType.setFenceMode(FenceModeValuesType.BRIDGED.value()); 

		VAppNetworkConfigurationType vAppNetworkConfigurationType = new VAppNetworkConfigurationType();
		vAppNetworkConfigurationType.setConfiguration(networkConfigurationType); 
		vAppNetworkConfigurationType.setNetworkName(vdc.getResource().getAvailableNetworks().getNetwork().get(0).getName());
		
		NetworkConfigSectionType networkConfigSectionType = new NetworkConfigSectionType();
		MsgType networkInfo = new MsgType();
		networkConfigSectionType.setInfo(networkInfo);
		
		List<VAppNetworkConfigurationType> vAppNetworkConfigs = networkConfigSectionType.getNetworkConfig();
		vAppNetworkConfigs.add(vAppNetworkConfigurationType);

		// create vApp config
		InstantiationParamsType vappOrvAppTemplateInstantiationParamsType = new InstantiationParamsType(); 
		List<JAXBElement<? extends SectionType>> vappSections = vappOrvAppTemplateInstantiationParamsType.getSection();
		vappSections.add(new ObjectFactory().createNetworkConfigSection(networkConfigSectionType));
		
		ComposeVAppParamsType composeVAppParamsType = new ComposeVAppParamsType(); 
		composeVAppParamsType.setDeploy(false);
		composeVAppParamsType.setInstantiationParams(vappOrvAppTemplateInstantiationParamsType);
		
		if (org.getvApp() != null && org.getvApp().getName() != null)
			composeVAppParamsType.setName(org.getvApp().getName());
		else
			composeVAppParamsType.setName("vApp_system_1");
		
		if (org.getvApp() != null && org.getvApp().getDescription() != null)
			composeVAppParamsType.setDescription(org.getvApp().getDescription());
		else
			composeVAppParamsType.setDescription("vApp_system_1");
		
		List<SourcedCompositionItemParamType> items = composeVAppParamsType.getSourcedItem();

		int i = 1;
		if (org.getvApp() != null && org.getvApp().getVms() != null)
			for (th.co.ais.enterprisecloud.domain.VmType childVM : org.getvApp().getVms()) {

				// getting the vApp Templates first vm.
				ReferenceType vappTemplateRef = VappUtils.findVappTemplateRef(client, catalogName, childVM.getTemplateType());
				VappTemplate vappTemplate = VappTemplate.getVappTemplateByReference(client, vappTemplateRef);
				VappTemplate vm = null;

				for (VappTemplate child : vappTemplate.getChildren()) {
					if (child.isVm()) {
						vm = child;
					}
				}

				if (vm == null) {
					logger.error("throw MissingVmTemplateException due to Missing VM template");
					throw new MissingVmTemplateException("Missing VM template");
				}
				
				String vmHref = vm.getReference().getHref();

				SourcedCompositionItemParamType vappTemplateItem = new SourcedCompositionItemParamType();
				ReferenceType vappTemplateVMRef = new ReferenceType();
				vappTemplateVMRef.setHref(vmHref);
				
				if (childVM.getName() != null)
					vappTemplateVMRef.setName(childVM.getName());
				else{
					vappTemplateVMRef.setName(org.getShortName() +"-VM"+i);
					childVM.setName(org.getShortName() +"-VM"+i);
				}
				++i;
				
				vappTemplateItem.setSource(vappTemplateVMRef);

				NetworkConnectionSectionType networkConnectionSectionType = new NetworkConnectionSectionType();
				networkConnectionSectionType.setInfo(networkInfo);

				NetworkConnectionType networkConnectionType = new NetworkConnectionType();
				networkConnectionType.setNetwork(vdc.getResource().getAvailableNetworks().getNetwork().get(0).getName());
				networkConnectionType.setIpAddressAllocationMode(IpAddressAllocationModeType.POOL.value());
				networkConnectionType.setIsConnected(Boolean.TRUE);
				networkConnectionSectionType.getNetworkConnection().add(networkConnectionType);

				InstantiationParamsType vmInstantiationParamsType = new InstantiationParamsType();
				List<JAXBElement<? extends SectionType>> vmSections = vmInstantiationParamsType.getSection();
				vmSections.add(new ObjectFactory().createNetworkConnectionSection(networkConnectionSectionType));

				vappTemplateItem.setInstantiationParams(vmInstantiationParamsType);

				items.add(vappTemplateItem);
			}
		
		return composeVAppParamsType;
	}
	

	/* Generate a random password */
	static String genPassword(){
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*?";
		String pwd = RandomStringUtils.random( 8, 0, 0, false, false, characters.toCharArray(), new SecureRandom() );
		return pwd;
	}	
	
	/**
	 * Search the vapp template reference. Since the vapptemplate is not unique
	 * under a vdc. This method returns the first occurance of the vapptemplate
	 * in that vdc.
	 * 
	 * @return
	 * @throws VCloudException
	 */
	static ReferenceType findVappTemplateRef(VcloudClient client, String catalogName, String vappTemplateName)
			throws VCloudException {
		ReferenceType vappTemplateRef = new ReferenceType();
		
		QueryParams<QueryAdminVAppTemplateField> queryParams = new QueryParams<QueryAdminVAppTemplateField>();
		
		queryParams.setFilter(new Filter(new Expression(QueryAdminVAppTemplateField.CATALOGNAME, catalogName, ExpressionType.EQUALS)));
		
		RecordResult<QueryResultAdminVAppTemplateRecordType> vappTemplateResult = client.getQueryService().queryRecords(QueryRecordType.ADMINVAPPTEMPLATE, queryParams);
		for (QueryResultAdminVAppTemplateRecordType vappTemplateRecord : vappTemplateResult.getRecords()) { 
			if(vappTemplateRecord.getName().equals(vappTemplateName)){				
				vappTemplateRef.setName(vappTemplateName);
				vappTemplateRef.setHref(vappTemplateRecord.getHref());				
				return vappTemplateRef;
			}
		}		
		
		return null;	
	}
	
	public void reconfigureVms(Vapp vapp, OrganizationType org) throws VCloudException, TimeoutException {
					
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer descBuff = new StringBuffer();
		
		for (VM vm : vapp.getChildrenVms()) {
			logger.info("Reconfigure VM: " + vm.getReference().getName());
			logger.info("Reconfigure GuestOS...");
			
			for (th.co.ais.enterprisecloud.domain.VmType childVM : org.getvApp().getVms()) {
				if (vm.getResource().getName().equalsIgnoreCase(childVM.getName())) {

					// Set VM description
					VmType vmType = vm.getResource();
					
					// Set VM id
					childVM.setId(vmType.getId());
			
					if (childVM.getDescription() != null)
						vmType.setDescription(childVM.getDescription());
					else {

						if (childVM.getNonMobileNo() != null)
							descBuff.append("Non-Mobile :" + childVM.getNonMobileNo());
						else
							descBuff.append("Non-Mobile : NA");

						if (childVM.getStartDate() != null && !df.format(childVM.getStartDate()).isEmpty()) {

							descBuff.append("Start Date :").append(df.format(childVM.getStartDate()));

							if (childVM.getEndDate() != null && !df.format(childVM.getEndDate()).isEmpty()
									&& org.getOrderType().name().equalsIgnoreCase(OrderType.TRIAL.name())) {
								descBuff.append(" - ").append(df.format(childVM.getEndDate()));
							}
						}

						vmType.setDescription(descBuff.toString());
					}
					
					// clear StringBuffer
					descBuff.delete(0, descBuff.length());
					
					vm.updateVM(vmType).waitForTask(0);

					// Set administrator password
					GuestCustomizationSectionType guestCustomizationSection = vm.getGuestCustomizationSection();
					
					if(childVM.getComputerName() != null) // allow only 15 characters long due to MS Windows limitation
						guestCustomizationSection.setComputerName(childVM.getComputerName().substring(0, Math.min(childVM.getComputerName().length(), 15)));
					else
						guestCustomizationSection.setComputerName(childVM.getName().substring(0, Math.min(childVM.getName().length(), 15)));
					
					guestCustomizationSection.setAdminPasswordEnabled(Boolean.TRUE);
					guestCustomizationSection.setAdminPasswordAuto(Boolean.FALSE);
					guestCustomizationSection.setResetPasswordRequired(Boolean.FALSE);

					// For Microsoft Windows VM
					guestCustomizationSection.setChangeSid(Boolean.TRUE);

					String adminPass = VappUtils.genPassword();
					guestCustomizationSection.setAdminPassword(adminPass);

					// Update username/password in VCloudOrganization
					childVM.setPassword(adminPass);
					
					vm.updateSection(guestCustomizationSection).waitForTask(0);

					// Configure CPU
					logger.info("Updating CPU Section...");
					VirtualCpu virtualCpuItem = vm.getCpu();
					
					// Set core per socket, default is 1
					if (childVM.getvCpu() != null && childVM.getvCpu().getCoresPerSocket() != null)
						virtualCpuItem.setCoresPerSocket(childVM.getvCpu().getCoresPerSocket());
					else
						virtualCpuItem.setCoresPerSocket(1);
					
					// Set number of Cpu, default is 1
					if (childVM.getvCpu() != null && childVM.getvCpu().getNoOfCpus() != null)
						virtualCpuItem.setNoOfCpus(childVM.getvCpu().getNoOfCpus());
					else
						virtualCpuItem.setNoOfCpus(1);
					
					vm.updateCpu(virtualCpuItem).waitForTask(0);

					// Configure Memory
					logger.info("Updating Memory Section...");
					VirtualMemory virtualMemoryItem = vm.getMemory();

					// Set memory, default is 2GB
					if (childVM.getvMemory() != null && childVM.getvMemory().getMemorySize() != null)
						virtualMemoryItem.setMemorySize(BigInteger.valueOf(1024).multiply(childVM.getvMemory().getMemorySize()));
					else
						virtualMemoryItem.setMemorySize(BigInteger.valueOf(2048));
					
					vm.updateMemory(virtualMemoryItem).waitForTask(0);

					// Configure Disks
					
					 logger.info("Updating Disks Section..."); List
					 <VirtualDisk> disks = vm.getDisks();
					  
					for (VirtualDisk disk : disks) {
						if (disk.isHardDisk()) {
							logger.info("Template size: " + disk.getHardDiskSize());

							if (childVM.getStorageSize() != null) {
								if (disk.getHardDiskSize().intValue() < (childVM.getStorageSize().intValue()*1024)) {
									BigInteger newDiskSize = BigInteger.valueOf(childVM.getStorageSize().intValue()*1024);
									logger.info("Update to size: " + newDiskSize.intValue());
									disk.updateHardDiskSize(newDiskSize);
								}
							}
						}
					}

					vm.updateDisks(disks).waitForTask(0);
					
					// Deploy VM
					vm.deploy(true, 0, false).waitForTask(0);
					logger.info("VM has deployed");

					// Display summary
					logger.info("Status : " + vm.getVMStatus());
					logger.info("No of CPUs : " + vm.getCpu().getNoOfCpus());
					logger.info("Core per Socket : " + vm.getCpu().getCoresPerSocket());
					logger.info("Memory : " + vm.getMemory().getMemorySize() + " MB");
					for (VirtualDisk disk : vm.getDisks())
						if (disk.isHardDisk())
							logger.info("HardDisk : " + disk.getHardDiskSize() + " MB\n");
				}
			}
			
		}
	}
}
