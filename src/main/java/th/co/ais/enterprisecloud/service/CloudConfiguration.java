package th.co.ais.enterprisecloud.service;

import com.vmware.vcloud.sdk.VCloudException;

public interface CloudConfiguration {
	public void connect() throws VCloudException;
}
