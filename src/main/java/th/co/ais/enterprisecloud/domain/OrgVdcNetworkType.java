package th.co.ais.enterprisecloud.domain;

public class OrgVdcNetworkType {
	private String name;
	private String description;
	private ConfigurationType configuration;
	
	public OrgVdcNetworkType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrgVdcNetworkType(String name, String description, ConfigurationType configuration) {
		super();
		this.name = name;
		this.description = description;
		this.setConfiguration(configuration);
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

	public ConfigurationType getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ConfigurationType configuration) {
		this.configuration = configuration;
	}
	
}
