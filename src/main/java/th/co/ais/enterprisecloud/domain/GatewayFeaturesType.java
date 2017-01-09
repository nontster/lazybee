package th.co.ais.enterprisecloud.domain;

public class GatewayFeaturesType {
	private FirewallServiceType firewallService;

	public GatewayFeaturesType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GatewayFeaturesType(FirewallServiceType firewallService) {
		super();
		this.setFirewallService(firewallService);
	}

	public FirewallServiceType getFirewallService() {
		return firewallService;
	}

	public void setFirewallService(FirewallServiceType firewallService) {
		this.firewallService = firewallService;
	}
	
}
