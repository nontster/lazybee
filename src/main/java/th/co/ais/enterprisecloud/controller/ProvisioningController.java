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

import th.co.ais.enterprisecloud.model.request.OrganizationType;
import th.co.ais.enterprisecloud.service.CloudService;

@RestController
public class ProvisioningController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private CloudService service;
	
	public ProvisioningController(CloudService service) {
		super();
		this.service = service;
	}


	@RequestMapping(value="/provisioning", method=RequestMethod.POST)
	@ResponseBody
	public HttpEntity<OrganizationType> provisioning(@RequestBody OrganizationType organization) {
		
		service.provisioning();	
		
/*		Organization org = new Organization("COMPANY", "Organization for COMPANY.COM",
				"COMPANY.COM");*/
		
		//org.add(linkTo(methodOn(ProvisioningController.class).provisioning(param)).withSelfRel());

		return new ResponseEntity<OrganizationType>(organization, HttpStatus.OK);
	}
}
