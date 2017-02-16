package th.co.ais.enterprisecloud.domain;

import java.util.UUID;

public class OrganizationType {
	private UUID id;
	private String name;
	private String description;
	private String shortName;
	private String url;
	private Boolean enabled;
	private OrderType orderType;
	private String caNumber;
	private String orderId;
	private UserType user;
	private OrgSettingsType orgSettings;
	private CloudResourcesType cloudResources;
	private VdcType vdc;
	private EdgeGatewayType edgeGateway;
	private OrgVdcNetworkType orgVdcNetwork;
	private VAppType vApp;
	
	public OrganizationType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganizationType(UUID id, String name, String description, Boolean enabled, OrderType orderType,
			String caNumber, String orderId, UserType user, OrgSettingsType orgSettings,
			CloudResourcesType cloudResources, VdcType vdc, EdgeGatewayType edgeGateway, OrgVdcNetworkType orgVdcNetwork, VAppType vApp) {
		super();
		this.setId(id);
		this.name = name;
		this.description = description;
		this.enabled = enabled;
		this.orderType = orderType;
		this.caNumber = caNumber;
		this.user = user;
		this.orgSettings = orgSettings;
		this.cloudResources = cloudResources;
		this.vdc = vdc;
		this.edgeGateway = edgeGateway;
		this.orgVdcNetwork = orgVdcNetwork;
		this.vApp = vApp;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the orderType
	 */
	public OrderType getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public String getCaNumber() {
		return caNumber;
	}

	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public UserType getUser() {
		return user;
	}

	public void setUser(UserType user) {
		this.user = user;
	}

	public OrgSettingsType getOrgSettings() {
		return orgSettings;
	}

	public void setOrgSettings(OrgSettingsType orgSettings) {
		this.orgSettings = orgSettings;
	}

	public CloudResourcesType getCloudResources() {
		return cloudResources;
	}

	public void setCloudResources(CloudResourcesType cloudResources) {
		this.cloudResources = cloudResources;
	}

	public VdcType getVdc() {
		return vdc;
	}

	public void setVdc(VdcType vdc) {
		this.vdc = vdc;
	}


	public EdgeGatewayType getEdgeGateway() {
		return edgeGateway;
	}


	public void setEdgeGateway(EdgeGatewayType edgeGateway) {
		this.edgeGateway = edgeGateway;
	}

	public OrgVdcNetworkType getOrgVdcNetwork() {
		return orgVdcNetwork;
	}

	public void setOrgVdcNetwork(OrgVdcNetworkType orgVdcNetwork) {
		this.orgVdcNetwork = orgVdcNetwork;
	}

	public VAppType getvApp() {
		return vApp;
	}

	public void setvApp(VAppType vApp) {
		this.vApp = vApp;
	}
	
}