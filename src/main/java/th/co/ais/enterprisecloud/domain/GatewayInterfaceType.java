package th.co.ais.enterprisecloud.domain;

public class GatewayInterfaceType {
	private String displayName;
	private InterfaceTypeEnums interfaceType;
	private SubnetParticipationType subnetParticipation;
	private Boolean useForDefaultRoute;
	
	public GatewayInterfaceType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GatewayInterfaceType(String displayName, InterfaceTypeEnums interfaceType,
			SubnetParticipationType subnetParticipation, Boolean useForDefaultRoute) {
		super();
		this.displayName = displayName;
		this.interfaceType = interfaceType;
		this.subnetParticipation = subnetParticipation;
		this.useForDefaultRoute = useForDefaultRoute;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public InterfaceTypeEnums getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(InterfaceTypeEnums interfaceType) {
		this.interfaceType = interfaceType;
	}

	public SubnetParticipationType getSubnetParticipation() {
		return subnetParticipation;
	}

	public void setSubnetParticipation(SubnetParticipationType subnetParticipation) {
		this.subnetParticipation = subnetParticipation;
	}

	public Boolean isUseForDefaultRoute() {
		return useForDefaultRoute;
	}

	public void setUseForDefaultRoute(Boolean useForDefaultRoute) {
		this.useForDefaultRoute = useForDefaultRoute;
	}

}
