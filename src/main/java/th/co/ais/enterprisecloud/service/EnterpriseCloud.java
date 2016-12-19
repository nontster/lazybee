package th.co.ais.enterprisecloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EnterpriseCloud implements CloudService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Boolean provisioning() {
		// TODO Auto-generated method stub
		logger.debug("provisioning() called!");
		
		return Boolean.TRUE;
	}

}
