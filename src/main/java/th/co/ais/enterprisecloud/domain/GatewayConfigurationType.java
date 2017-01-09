package th.co.ais.enterprisecloud.domain;

import java.util.List;

public class GatewayConfigurationType {
	
	private GatewayBackingConfigType gatewayBackingConfig;
	private Boolean haEnabled;
	private Boolean useDefaultRouteForDnsRelay;
	private List<GatewayInterfaceType> gatewayInterfaces;
	
	public GatewayConfigurationType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GatewayConfigurationType(GatewayBackingConfigType gatewayBackingConfig, Boolean haEnabled,
			Boolean useDefaultRouteForDnsRelay, List<GatewayInterfaceType> gatewayInterfaces) {
		super();
		this.gatewayBackingConfig = gatewayBackingConfig;
		this.haEnabled = haEnabled;
		this.useDefaultRouteForDnsRelay = useDefaultRouteForDnsRelay;
		this.gatewayInterfaces = gatewayInterfaces;
	}

	public GatewayBackingConfigType getGatewayBackingConfig() {
		return gatewayBackingConfig;
	}

	public void setGatewayBackingConfig(GatewayBackingConfigType gatewayBackingConfig) {
		this.gatewayBackingConfig = gatewayBackingConfig;
	}

	
	public Boolean isHaEnabled() {
		return haEnabled;
	}

	public void setHaEnabled(Boolean haEnabled) {
		this.haEnabled = haEnabled;
	}

	public Boolean isUseDefaultRouteForDnsRelay() {
		return useDefaultRouteForDnsRelay;
	}

	public void setUseDefaultRouteForDnsRelay(Boolean useDefaultRouteForDnsRelay) {
		this.useDefaultRouteForDnsRelay = useDefaultRouteForDnsRelay;
	}

	public List<GatewayInterfaceType> getGatewayInterfaces() {
		return gatewayInterfaces;
	}

	public void setGatewayInterfaces(List<GatewayInterfaceType> gatewayInterfaces) {
		this.gatewayInterfaces = gatewayInterfaces;
	}

}
