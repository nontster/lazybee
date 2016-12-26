package th.co.ais.enterprisecloud.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.vmware.vcloud.sdk.VCloudException;
import com.vmware.vcloud.sdk.VcloudClient;
import com.vmware.vcloud.sdk.admin.AdminVdc;
import com.vmware.vcloud.sdk.admin.EdgeGateway;
import com.vmware.vcloud.sdk.admin.VcloudAdmin;
import com.vmware.vcloud.sdk.constants.Version;

@Component
@ConfigurationProperties("vcloud")
public class VCloudDirectorConfiguration implements CloudConfiguration {

	@Value("${vcloud.url}")
	private String url;
	
	@Value("${vcloud.username}")
	private String username;
	
	@Value("${vcloud.password}")
	private String password;
	
	private VcloudClient client;
	private VcloudAdmin admin;
	private AdminVdc adminVdc;
	private EdgeGateway edgeGateway;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public void connect() throws VCloudException {
		// TODO Auto-generated method stub
		this.client = new VcloudClient(url, Version.V5_5);

		this.client.login(username, password);
		logger.info("Success sigin to vCloud Director, %s", url);
		
		logger.info("Get Vcloud Admin");
		this.admin = client.getVcloudAdmin();
		logger.info("Get Vcloud Admin, %s", admin.getResource().getHref());
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public VcloudClient getClient() {
		return client;
	}

	public void setClient(VcloudClient client) {
		this.client = client;
	}

	public VcloudAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(VcloudAdmin admin) {
		this.admin = admin;
	}

	public AdminVdc getAdminVdc() {
		return adminVdc;
	}

	public void setAdminVdc(AdminVdc adminVdc) {
		this.adminVdc = adminVdc;
	}

	public EdgeGateway getEdgeGateway() {
		return edgeGateway;
	}

	public void setEdgeGateway(EdgeGateway edgeGateway) {
		this.edgeGateway = edgeGateway;
	}

	public Logger getLogger() {
		return logger;
	}

}
