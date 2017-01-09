package th.co.ais.enterprisecloud.domain;

import java.util.List;

public class FirewallServiceType {
	private Boolean enabled;
	private FirewallActionType defaultAction;
	private Boolean logDefaultAction;	
	private List <FirewallRuleType> firewallRules;
	
	public FirewallServiceType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FirewallServiceType(Boolean enabled, FirewallActionType defaultAction, Boolean logDefaultAction,
			List<FirewallRuleType> firewallRules) {
		super();
		this.enabled = enabled;
		this.defaultAction = defaultAction;
		this.logDefaultAction = logDefaultAction;
		this.firewallRules = firewallRules;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public FirewallActionType getDefaultAction() {
		return defaultAction;
	}

	public void setDefaultAction(FirewallActionType defaultAction) {
		this.defaultAction = defaultAction;
	}

	public Boolean isLogDefaultAction() {
		return logDefaultAction;
	}

	public void setLogDefaultAction(Boolean logDefaultAction) {
		this.logDefaultAction = logDefaultAction;
	}

	public List<FirewallRuleType> getFirewallRules() {
		return firewallRules;
	}

	public void setFirewallRules(List<FirewallRuleType> firewallRules) {
		this.firewallRules = firewallRules;
	}
	
}
