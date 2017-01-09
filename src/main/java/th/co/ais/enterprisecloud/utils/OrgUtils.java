package th.co.ais.enterprisecloud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vmware.vcloud.api.rest.schema.AdminOrgType;
import com.vmware.vcloud.api.rest.schema.OrgGeneralSettingsType;
import com.vmware.vcloud.api.rest.schema.OrgLeaseSettingsType;
import com.vmware.vcloud.api.rest.schema.OrgPasswordPolicySettingsType;
import com.vmware.vcloud.api.rest.schema.OrgSettingsType;
import com.vmware.vcloud.api.rest.schema.OrgVAppTemplateLeaseSettingsType;
import com.vmware.vcloud.api.rest.schema.TaskType;
import com.vmware.vcloud.api.rest.schema.TasksInProgressType;

import th.co.ais.enterprisecloud.domain.OrganizationType;
import th.co.ais.enterprisecloud.exception.MissingInputParameterException;

import com.vmware.vcloud.sdk.Task;
import com.vmware.vcloud.sdk.VCloudException;
import com.vmware.vcloud.sdk.VcloudClient;
import com.vmware.vcloud.sdk.admin.AdminOrganization;

@Component
public class OrgUtils {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Creates a new admin org type object
	 * 
	 * @throws VCloudException
	 * @throws MissingInputParameterException 
	 * 
	 */
	public AdminOrgType createNewAdminOrgType(OrganizationType org) throws VCloudException, MissingInputParameterException {

		// Setting orgLeaseSettings
		OrgLeaseSettingsType orgLeaseSettings = new OrgLeaseSettingsType();
				
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgLeaseSettings() != null && org.getOrgSettings().getOrgLeaseSettings().isDeleteOnStorageLeaseExpiration() != null)
			orgLeaseSettings.setDeleteOnStorageLeaseExpiration(org.getOrgSettings().getOrgLeaseSettings().isDeleteOnStorageLeaseExpiration());
		else
			orgLeaseSettings.setDeleteOnStorageLeaseExpiration(Boolean.FALSE);
		
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgLeaseSettings() != null && org.getOrgSettings().getOrgLeaseSettings().getDeploymentLeaseSeconds() != null)
			orgLeaseSettings.setDeploymentLeaseSeconds(org.getOrgSettings().getOrgLeaseSettings().getDeploymentLeaseSeconds());
		else
			orgLeaseSettings.setDeploymentLeaseSeconds(0);
			
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgLeaseSettings() != null && org.getOrgSettings().getOrgLeaseSettings().getStorageLeaseSeconds() != null)	
			orgLeaseSettings.setStorageLeaseSeconds(org.getOrgSettings().getOrgLeaseSettings().getStorageLeaseSeconds());
		else
			orgLeaseSettings.setStorageLeaseSeconds(0);

		// Setting orgGeneralSettings
		OrgGeneralSettingsType orgGeneralSettings = new OrgGeneralSettingsType();
		
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgLeaseSettings() != null && org.getOrgSettings().getOrgGeneralSettings().getStoredVmQuota() != null)		
			orgGeneralSettings.setStoredVmQuota(org.getOrgSettings().getOrgGeneralSettings().getStoredVmQuota());
		else
			orgGeneralSettings.setStoredVmQuota(0);
		
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgLeaseSettings() != null && org.getOrgSettings().getOrgGeneralSettings().getDeployedVMQuota() != null)
			orgGeneralSettings.setDeployedVMQuota(org.getOrgSettings().getOrgGeneralSettings().getDeployedVMQuota());
		else
			orgGeneralSettings.setDeployedVMQuota(0);
			
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgLeaseSettings() != null && org.getOrgSettings().getOrgGeneralSettings().isCanPublishCatalogs() != null)	
			orgGeneralSettings.setCanPublishCatalogs(org.getOrgSettings().getOrgGeneralSettings().isCanPublishCatalogs());
		else
			orgGeneralSettings.setCanPublishCatalogs(Boolean.FALSE);
		
		// Setting orgVAppTemplateLeaseSettings
		OrgVAppTemplateLeaseSettingsType orgVAppTemplateLeaseSettings = new OrgVAppTemplateLeaseSettingsType();
		
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgVAppTemplateLeaseSettings() != null && org.getOrgSettings().getOrgVAppTemplateLeaseSettings().isDeleteOnStorageLeaseExpiration() != null)
			orgVAppTemplateLeaseSettings.setDeleteOnStorageLeaseExpiration(org.getOrgSettings().getOrgVAppTemplateLeaseSettings().isDeleteOnStorageLeaseExpiration());
		else
			orgVAppTemplateLeaseSettings.setDeleteOnStorageLeaseExpiration(Boolean.FALSE);
		
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgVAppTemplateLeaseSettings() != null && org.getOrgSettings().getOrgVAppTemplateLeaseSettings().getStorageLeaseSeconds() != null)
			orgVAppTemplateLeaseSettings.setStorageLeaseSeconds(org.getOrgSettings().getOrgVAppTemplateLeaseSettings().getStorageLeaseSeconds());
		else
			orgVAppTemplateLeaseSettings.setStorageLeaseSeconds(0);
		
		// Setting orgPasswordPolicySettings
		OrgPasswordPolicySettingsType orgPasswordPolicySettings = new OrgPasswordPolicySettingsType();
		
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgPasswordPolicySettings() != null && org.getOrgSettings().getOrgPasswordPolicySettings().isAccountLockoutEnabled() != null)
			orgPasswordPolicySettings.setAccountLockoutEnabled(org.getOrgSettings().getOrgPasswordPolicySettings().isAccountLockoutEnabled());		
		else 
			orgPasswordPolicySettings.setAccountLockoutEnabled(Boolean.TRUE);
			
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgPasswordPolicySettings() != null && org.getOrgSettings().getOrgPasswordPolicySettings().getAccountLockoutIntervalMinutes() != null)
			orgPasswordPolicySettings.setAccountLockoutIntervalMinutes(org.getOrgSettings().getOrgPasswordPolicySettings().getAccountLockoutIntervalMinutes());
		else
			orgPasswordPolicySettings.setAccountLockoutIntervalMinutes(15);
		
		if(org.getOrgSettings()!= null && org.getOrgSettings().getOrgPasswordPolicySettings() != null && org.getOrgSettings().getOrgPasswordPolicySettings().getInvalidLoginsBeforeLockout() != null)
			orgPasswordPolicySettings.setInvalidLoginsBeforeLockout(org.getOrgSettings().getOrgPasswordPolicySettings().getInvalidLoginsBeforeLockout());
		else
			orgPasswordPolicySettings.setInvalidLoginsBeforeLockout(15);
				
		OrgSettingsType orgSettings = new OrgSettingsType();
		orgSettings.setOrgGeneralSettings(orgGeneralSettings);
		orgSettings.setVAppLeaseSettings(orgLeaseSettings);
		orgSettings.setVAppTemplateLeaseSettings(orgVAppTemplateLeaseSettings);
		orgSettings.setOrgPasswordPolicySettings(orgPasswordPolicySettings);

		AdminOrgType adminOrgType = new AdminOrgType();
						
		if(org.getShortName() != null){
			StringBuffer orgName = new StringBuffer();	
				
			if(org.getOrderType().name().equalsIgnoreCase("trial"))
				orgName.append("Trial-");	
				
			orgName.append(org.getShortName());
				
			adminOrgType.setName(orgName.toString());
			org.setName(orgName.toString());	
			
			logger.debug("Set organization name to " + orgName.toString());
		} else {
			logger.error("throwing MissingParameterException due to missing shortName parameter");
			throw new MissingInputParameterException("Missing shortName parameter");
		}
		
		StringBuffer descBuff = new StringBuffer();
		
		if(org.getCaNumber() != null)
			descBuff.append("CA Number :").append(org.getCaNumber()).append("\n");
					
		descBuff.append("Customer Contact :").append("\n");
		if(org.getUser() != null && org.getUser().getFullName() != null)
			descBuff.append("   Name: ").append(org.getUser().getFullName()).append("\n");
		if(org.getUser() != null && org.getUser().getEmailAddress() != null)
			descBuff.append("   Email: ").append(org.getUser().getEmailAddress()).append("\n");
		if(org.getUser() != null && org.getUser().getPhone() != null)
			descBuff.append("   Tel.: ").append(org.getUser().getPhone()).append("\n");
				
		if(org.getDescription() != null && !org.getDescription().isEmpty())
			adminOrgType.setDescription(org.getDescription());
		else
			adminOrgType.setDescription(descBuff.toString());
		
		if(org.getName() != null)
			adminOrgType.setFullName(org.getName());
		else
			throw new NullPointerException("Organization full name cannot be NULL");
			
		adminOrgType.setSettings(orgSettings);
		
		if(org.isEnabled() != null)
			adminOrgType.setIsEnabled(org.isEnabled());
		else
			adminOrgType.setIsEnabled(Boolean.TRUE);

		return adminOrgType;
	}

	/**
	 * Check for tasks if any
	 * 
	 * @param adminOrg
	 * @return {@link Task}
	 * @throws VCloudException
	 */
	public Task returnTask(VcloudClient client, AdminOrganization adminOrg) throws VCloudException {
		TasksInProgressType tasksInProgress = adminOrg.getResource().getTasks();
		if (tasksInProgress != null)
			for (TaskType task : tasksInProgress.getTask()) {
				return new Task(client, task);
			}
		return null;
	}

}
