package th.co.ais.enterprisecloud.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.co.ais.enterprisecloud.exception.InvalidParameterException;
import th.co.ais.enterprisecloud.exception.MissingParameterException;
import th.co.ais.enterprisecloud.exception.UserRoleNotFoundException;
import th.co.ais.enterprisecloud.model.request.OrganizationType;
import th.co.ais.enterprisecloud.service.CloudService;
import th.co.ais.enterprisecloud.utils.ParamsValidator;

@RestController
public class ProvisioningController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private CloudService service;
	private ParamsValidator validator;
	
	public ProvisioningController(CloudService service, ParamsValidator validator) {
		super();
		this.service = service;
		this.validator = validator;
	}

	@RequestMapping(value="/provisioning", method=RequestMethod.POST)
	@ResponseBody
	public HttpEntity<OrganizationType> provisioning(@RequestBody OrganizationType org) throws InterruptedException,
			UserRoleNotFoundException, MissingParameterException, InvalidParameterException {
	
		logger.debug("calling service.provisioning()");
		validator.validate(org);
		service.provisioning(org);	
				
		//org.add(linkTo(methodOn(ProvisioningController.class).provisioning(param)).withSelfRel());

		
		return new ResponseEntity<OrganizationType>(org, HttpStatus.OK); 
	}
}
