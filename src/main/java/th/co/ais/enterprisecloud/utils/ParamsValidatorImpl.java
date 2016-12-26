package th.co.ais.enterprisecloud.utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import th.co.ais.enterprisecloud.exception.InvalidParameterException;
import th.co.ais.enterprisecloud.exception.MissingParameterException;
import th.co.ais.enterprisecloud.model.request.OrganizationType;
import th.co.ais.enterprisecloud.model.request.UserType;
import th.co.ais.enterprisecloud.model.request.VmType;

@Component
public class ParamsValidatorImpl implements ParamsValidator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Boolean validate(OrganizationType org) throws MissingParameterException, InvalidParameterException {
		// TODO Auto-generated method stub
				
		if(org.getCaNumber() == null) {
			String errMsg = "Missing CA number parameter";
			logger.error(errMsg);
			throw new MissingParameterException(errMsg);
		}
		
		if(org.getName() == null){
			String errMsg = "Missing customer name parameter";
			logger.error(errMsg);
			throw new MissingParameterException(errMsg);			
		} 
		
		if(org.getOrderId() == null){
			String errMsg = "Missing OrderId parameter";
			logger.error(errMsg);
			throw new MissingParameterException(errMsg);
		}
		
		if(org.getOrderType() == null){
			String errMsg = "Missing orderType parameter";
			logger.error(errMsg);
			throw new MissingParameterException(errMsg);
		} else if(!org.getOrderType().equalsIgnoreCase("trial") && !org.getOrderType().equalsIgnoreCase("standard") && !org.getOrderType().equalsIgnoreCase("customized")){			
			String errMsg = "Wrong orderType parameter, only trial|standard|customized permitted";
			logger.error(errMsg);
			throw new InvalidParameterException(errMsg);
		}
		
		if(org.getShortName() == null){
			String errMsg = "Missing shortName parameter";
			logger.error(errMsg);
			throw new MissingParameterException(errMsg);
		}
		
		if(org.getUsers() == null){
			String errMsg = "Missing users parameter";
			logger.error(errMsg);
			throw new MissingParameterException(errMsg);
		}
		
		if(org.getUsers().getUser() == null){
			String errMsg = "Missing user parameter";
			logger.error(errMsg);
			throw new MissingParameterException(errMsg);			
		}
				
		for(UserType user : org.getUsers().getUser()){
			
			String email = user.getEmail();
			String fullName = user.getFullName();
			
			if(email == null){
				String errMsg = "Missing email parameter";
				logger.error(errMsg);
				throw new MissingParameterException(errMsg);
			} else {
				EmailValidator emailValidator = EmailValidator.getInstance();
				if(!emailValidator.isValid(email)){
					String errMsg = "Invalid email format";
					logger.error(errMsg);
					throw new InvalidParameterException(errMsg);
				}
			}
			
			if(fullName == null){
				String errMsg = "Missing fullName parameter";
				logger.error(errMsg);
				throw new MissingParameterException(errMsg);
			}
		}

		
		if(org.getVms() == null){
			String errMsg = "Missing vms parameter";
			logger.error(errMsg);
			throw new MissingParameterException(errMsg);
		}
		
		if(org.getVms().getVm() == null){
			String errMsg = "Missing vm parameter";
			logger.error(errMsg);
			throw new MissingParameterException(errMsg);			
		}
		
		for(VmType vm : org.getVms().getVm()){
			if(vm.getCoresPerSocket()< 1){
				String errMsg = "Cores per socker less than 1";
				logger.error(errMsg);
				throw new InvalidParameterException(errMsg);
			}
			
			if(vm.getNoOfCpus()< 1){
				String errMsg = "No. of cpus less than 1";
				logger.error(errMsg);
				throw new InvalidParameterException(errMsg);
			}
			
			if(vm.getMemorySize()< 1){
				String errMsg = "Memory size less than 1GB";
				logger.error(errMsg);
				throw new InvalidParameterException(errMsg);
			}
			
			if(vm.getOsImageName() == null){
				String errMsg = "Missing osImageName parameter";
				logger.error(errMsg);
				throw new MissingParameterException(errMsg);	
			}
			
			if(vm.getNonMobileNumber() == null){
				String errMsg = "Missing nonMobileNumber parameter";
				logger.error(errMsg);
				throw new MissingParameterException(errMsg);
			}
		}
		
		return Boolean.TRUE;
	}

}
