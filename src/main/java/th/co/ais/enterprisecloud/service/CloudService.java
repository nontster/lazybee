package th.co.ais.enterprisecloud.service;

import java.util.concurrent.Future;

public interface CloudService {
	public Future<Boolean> provisioning(th.co.ais.enterprisecloud.model.request.OrganizationType org) throws InterruptedException;
}
