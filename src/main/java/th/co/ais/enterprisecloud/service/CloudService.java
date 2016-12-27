package th.co.ais.enterprisecloud.service;

import java.util.concurrent.Future;

import com.vmware.vcloud.sdk.VCloudException;

public interface CloudService {
	public void connect() throws VCloudException;
	public Future<Boolean> provisioning(th.co.ais.enterprisecloud.model.request.OrganizationType org) throws InterruptedException, VCloudException;
}
