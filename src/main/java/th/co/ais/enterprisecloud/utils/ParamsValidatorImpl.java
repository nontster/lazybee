package th.co.ais.enterprisecloud.utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import th.co.ais.enterprisecloud.domain.request.OrganizationType;
import th.co.ais.enterprisecloud.domain.request.UserType;
import th.co.ais.enterprisecloud.domain.request.VmType;
import th.co.ais.enterprisecloud.exception.InvalidParameterException;
import th.co.ais.enterprisecloud.exception.MissingInputParameterException;

@Component
public class ParamsValidatorImpl implements ParamsValidator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Boolean validate(OrganizationType org) throws MissingInputParameterException, InvalidParameterException {
		// TODO Auto-generated method stub
				
		logger.debug("validating input parameters");

		if(org.getOrderType() == null){
			String errMsg = "Missing orderType parameter";
			logger.error(errMsg);
			throw new MissingInputParameterException(errMsg);
		} else if(!org.getOrderType().equalsIgnoreCase("trial") && !org.getOrderType().equalsIgnoreCase("standard") && !org.getOrderType().equalsIgnoreCase("customized")){			
			String errMsg = "Wrong orderType parameter, only trial|standard|customized permitted";
			logger.error(errMsg);
			throw new InvalidParameterException(errMsg);
		}
		
		if(org.getOrderType().equalsIgnoreCase("trial") && org.getCaNumber() == null) {
			String errMsg = "Missing CA number parameter";
			logger.error(errMsg);
			throw new MissingInputParameterException(errMsg);
		}
		
		if(org.getName() == null){
			String errMsg = "Missing customer name parameter";
			logger.error(errMsg);
			throw new MissingInputParameterException(errMsg);			
		} 
		
		if(org.getOrderId() == null){
			String errMsg = "Missing OrderId parameter";
			logger.error(errMsg);
			throw new MissingInputParameterException(errMsg);
		}
				
		if(org.getShortName() == null){
			String errMsg = "Missing shortName parameter";
			logger.error(errMsg);
			throw new MissingInputParameterException(errMsg);
		}
		
		if(org.getUsers() == null){
			String errMsg = "Missing users parameter";
			logger.error(errMsg);
			throw new MissingInputParameterException(errMsg);
		}
		
		if(org.getUsers() == null || org.getUsers().getUser() == null || org.getUsers().getUser().isEmpty()){
			String errMsg = "Missing user parameter";
			logger.error(errMsg);
			throw new MissingInputParameterException(errMsg);			
		}
				
		for(UserType user : org.getUsers().getUser()){
			
			String email = user.getEmail();
			String fullName = user.getFullName();
			
			if(email == null){
				String errMsg = "Missing email parameter";
				logger.error(errMsg);
				throw new MissingInputParameterException(errMsg);
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
				throw new MissingInputParameterException(errMsg);
			}
		}

		
		if(org.getVms() == null){
			String errMsg = "Missing vms parameter";
			logger.error(errMsg);
			throw new MissingInputParameterException(errMsg);
		}
		
		if(org.getVms().getVm() == null || org.getVms().getVm().isEmpty()){
			String errMsg = "Missing vm parameter";
			logger.error(errMsg);
			throw new MissingInputParameterException(errMsg);			
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
				throw new MissingInputParameterException(errMsg);	
			}
			
			if(vm.getNonMobileNumber() == null){
				String errMsg = "Missing nonMobileNumber parameter";
				logger.error(errMsg);
				throw new MissingInputParameterException(errMsg);
			}
		}
		
		return Boolean.TRUE;
	}
}
