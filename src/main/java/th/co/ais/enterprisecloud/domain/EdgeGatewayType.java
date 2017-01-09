package th.co.ais.enterprisecloud.domain;

public class EdgeGatewayType {
	private String name;
	private String description;
	private GatewayConfigurationType gatewayConfiguration;
	private GatewayFeaturesType gatewayFeatures;
	
	public EdgeGatewayType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EdgeGatewayType(String name, String description, GatewayConfigurationType gatewayConfiguration,
			GatewayFeaturesType gatewayFeatures) {
		super();
		this.name = name;
		this.description = description;
		this.gatewayConfiguration = gatewayConfiguration;
		this.gatewayFeatures = gatewayFeatures;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GatewayConfigurationType getGatewayConfiguration() {
		return gatewayConfiguration;
	}

	public void setGatewayConfiguration(GatewayConfigurationType gatewayConfiguration) {
		this.gatewayConfiguration = gatewayConfiguration;
	}

	public GatewayFeaturesType getGatewayFeatures() {
		return gatewayFeatures;
	}

	public void setGatewayFeatures(GatewayFeaturesType gatewayFeatures) {
		this.gatewayFeatures = gatewayFeatures;
	}
		
}
