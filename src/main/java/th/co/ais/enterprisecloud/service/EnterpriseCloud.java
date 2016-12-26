package th.co.ais.enterprisecloud.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import th.co.ais.enterprisecloud.model.request.OrganizationType;

@Component
public class EnterpriseCloud implements CloudService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Async
	public Future<Boolean> provisioning(OrganizationType org) throws InterruptedException {
		// TODO Auto-generated method stub
		logger.debug("provisioning() called!");
		Thread.sleep(5000L);
		return new AsyncResult<>(Boolean.TRUE);
	}

}
