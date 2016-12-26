package th.co.ais.enterprisecloud.service;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.vmware.vcloud.sdk.VCloudException;

import th.co.ais.enterprisecloud.model.request.OrganizationType;

@Component
@ConfigurationProperties("vcloud")
public class VCloudDirectorService implements CloudService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private CloudConfiguration conf;
		
	public VCloudDirectorService(CloudConfiguration conf) throws VCloudException {
		super();
		this.conf = conf;
		
		conf.connect();
	}

	@Async
	public Future<Boolean> provisioning(OrganizationType org) throws InterruptedException {
		// TODO Auto-generated method stub
		logger.debug("provisioning() called!");
		Thread.sleep(5000L);
		return new AsyncResult<>(Boolean.TRUE);
	}

}
