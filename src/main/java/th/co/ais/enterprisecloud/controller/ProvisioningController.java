package th.co.ais.enterprisecloud.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.co.ais.enterprisecloud.model.Organization;

@RestController
public class ProvisioningController {

	@RequestMapping("/provisioning")
	@ResponseBody
	public HttpEntity<Organization> provisioning(
			@RequestParam(value = "param", required = false, defaultValue = "HATEOAS") String param) {

		Organization org = new Organization("ThaiBev", "Organization for Thai Beverage Public Company Limited",
				"Thai Beverage Public Company Limited");
		
		org.add(linkTo(methodOn(ProvisioningController.class).provisioning(param)).withSelfRel());

		return new ResponseEntity<Organization>(org, HttpStatus.OK);
	}
}
