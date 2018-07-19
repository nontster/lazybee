package th.co.ais.enterprisecloud.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.vcloud.sdk.VCloudException;

import th.co.ais.enterprisecloud.service.CloudService;
import th.co.ais.enterprisecloud.utils.DtoUtils;
import th.co.ais.enterprisecloud.utils.ParamsValidator;

@RestController
public class ProvisioningController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private CloudService service;
	private ParamsValidator validator;
	private DtoUtils dtoUtils;
	
	public ProvisioningController(CloudService service, ParamsValidator validator, DtoUtils dtoUtils) {
		super();
		this.service = service;
		this.validator = validator;
		this.dtoUtils = dtoUtils;
	}

	@RequestMapping(value = "/api/admin/orgs", method = RequestMethod.POST, consumes = "application/vnd.ais.admin.organization+xml;version=1.0")
	@ResponseBody
	public HttpEntity<th.co.ais.enterprisecloud.domain.response.OrganizationType> provisioning(
			@RequestBody th.co.ais.enterprisecloud.domain.request.OrganizationType req)
			throws VCloudException, TimeoutException, InterruptedException, ExecutionException {
		
		logger.debug("calling service.provisioning()");
		logger.info(req.toString());
		
		validator.validate(req);
			
		th.co.ais.enterprisecloud.domain.OrganizationType org = dtoUtils.transfer(req);
		Future<th.co.ais.enterprisecloud.domain.response.OrganizationType> res = service.provisioning(org);	
					
		// Wait until they are all done
	    while (!res.isDone()) {
	    	Thread.sleep(10); //10-millisecond pause between each check
	    }
	        				
		//org.add(linkTo(methodOn(ProvisioningController.class).provisioning(param)).withSelfRel());
		
		return new ResponseEntity<th.co.ais.enterprisecloud.domain.response.OrganizationType>(res.get(), HttpStatus.CREATED); 
	}
	
	@RequestMapping(value = "/api/orgs/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpEntity<Boolean> queryMethod(@RequestParam("name") String name) throws VCloudException, InterruptedException, ExecutionException{
		
		logger.debug("calling service.findOrgByName(name)");
		
		Future <Boolean> status = service.findOrgByName(name);
		
		// Wait until they are all done
		while (!status.isDone()) {
			Thread.sleep(10); // 10-millisecond pause between each check
		}
		
		return new ResponseEntity<Boolean>(status.get(), HttpStatus.OK);		
	}
		
}
