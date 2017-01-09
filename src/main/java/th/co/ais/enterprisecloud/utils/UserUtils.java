package th.co.ais.enterprisecloud.utils;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vmware.vcloud.api.rest.schema.ReferenceType;
import com.vmware.vcloud.api.rest.schema.UserType;
import com.vmware.vcloud.sdk.Task;
import com.vmware.vcloud.sdk.VCloudException;
import com.vmware.vcloud.sdk.admin.AdminOrganization;
import com.vmware.vcloud.sdk.admin.User;
import com.vmware.vcloud.sdk.admin.VcloudAdmin;

import th.co.ais.enterprisecloud.domain.OrganizationType;
import th.co.ais.enterprisecloud.exception.UserRoleNotFoundException;

@Component
public class UserUtils {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void addUserToOrg(OrganizationType org, VcloudAdmin admin, AdminOrganization adminOrg) throws TimeoutException, UserRoleNotFoundException, VCloudException {
		
		UserType newUserType = new UserType();

		// Credential	
		if(org.getUser() != null && org.getUser().getName() != null)
			newUserType.setName(org.getUser().getName());
		else {
			newUserType.setName(org.getShortName() + "_admin");
			
			if(org.getUser() == null)
				org.setUser(new th.co.ais.enterprisecloud.domain.UserType());
			
			org.getUser().setName(org.getShortName() + "_admin");
		}
		
		if(org.getUser() != null && org.getUser().getPassword() != null)
			newUserType.setPassword(org.getUser().getPassword());
		else{
			String newPassword = VappUtils.genPassword();
			newUserType.setPassword(newPassword);
			org.getUser().setPassword(newPassword);
		}
			
		if(org.getUser() != null && org.getUser().isEnabled() != null)
			newUserType.setIsEnabled(org.getUser().isEnabled());
		else
			newUserType.setIsEnabled(Boolean.TRUE);

		// Role : 'Customer Managed Service'
		ReferenceType usrRoleRef = null;
		
		if(org.getUser() != null && org.getUser().getRoleName() != null)
			usrRoleRef = admin.getRoleRefByName(org.getUser().getRoleName());
		else
			usrRoleRef = admin.getRoleRefByName("Customer Managed Service");
		
		if(usrRoleRef == null)
			throw new UserRoleNotFoundException("User role not found");
		
		newUserType.setRole(usrRoleRef);

		// Contact Info:
		if(org.getUser() != null && org.getUser().getFullName() != null)
			newUserType.setFullName(org.getUser().getFullName());
		
		if(org.getUser() != null && org.getUser().getEmailAddress() != null)
			newUserType.setEmailAddress(org.getUser().getEmailAddress());
		
		if (org.getUser() != null && org.getUser().getPhone() != null)
			newUserType.setTelephone(org.getUser().getPhone());
		
		// Use defaults for rest of the fields.
		User user = adminOrg.createUser(newUserType);

		logger.info("Creating admin user for organization : " 
				+ user.getResource().getName() + " : "
				+ user.getResource().getHref());
		List<Task> tasks = user.getTasks();
		if (tasks.size() > 0)
			tasks.get(0).waitForTask(0);
		
	}
}
