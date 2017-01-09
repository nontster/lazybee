package th.co.ais.enterprisecloud.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "vcloud")
@PropertySource("classpath:/vcloud.properties")
@Component
public class VcloudProperties {
	private String url;
	private String username;
	private String password;
	private String providerVdc;
	private String networkPool;
	private String externalNetwork;
	private String catalog;
	private String providerStorageProfile;
	private String netmask;
	private String gateway;
	private String dns1;
	private String dns2;
	private String startAddress;
	private String endAddress;

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

	public String getProviderVdc() {
		return providerVdc;
	}

	public void setProviderVdc(String providerVdc) {
		this.providerVdc = providerVdc;
	}

	public String getNetworkPool() {
		return networkPool;
	}

	public void setNetworkPool(String networkPool) {
		this.networkPool = networkPool;
	}

	public String getExternalNetwork() {
		return externalNetwork;
	}

	public void setExternalNetwork(String externalNetwork) {
		this.externalNetwork = externalNetwork;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getProviderStorageProfile() {
		return providerStorageProfile;
	}

	public void setProviderStorageProfile(String providerStorageProfile) {
		this.providerStorageProfile = providerStorageProfile;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getDns1() {
		return dns1;
	}

	public void setDns1(String dns1) {
		this.dns1 = dns1;
	}

	public String getDns2() {
		return dns2;
	}

	public void setDns2(String dns2) {
		this.dns2 = dns2;
	}

	public String getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	public String getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

}
