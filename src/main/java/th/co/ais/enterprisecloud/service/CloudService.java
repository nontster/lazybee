package th.co.ais.enterprisecloud.service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import com.vmware.vcloud.sdk.VCloudException;
import com.vmware.vcloud.sdk.Vapp;
import com.vmware.vcloud.sdk.admin.EdgeGateway;

public interface CloudService {
	public void connect() throws VCloudException;

	public th.co.ais.enterprisecloud.domain.response.OrganizationType prepareResponse(
			th.co.ais.enterprisecloud.domain.OrganizationType org, EdgeGateway edgeGateway, Vapp vapp, String orgVdcName) throws VCloudException;
	
	public Future<th.co.ais.enterprisecloud.domain.response.OrganizationType> provisioning(th.co.ais.enterprisecloud.domain.OrganizationType in) throws VCloudException, TimeoutException;
	
	public void disconnect() throws VCloudException;
}
