package th.co.ais.enterprisecloud.domain;

public class OrgPasswordPolicySettingsType {
	private Boolean accountLockoutEnabled;
	private Integer accountLockoutIntervalMinutes;
	private Integer invalidLoginsBeforeLockout;
	
	public OrgPasswordPolicySettingsType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrgPasswordPolicySettingsType(Boolean accountLockoutEnabled, Integer accountLockoutIntervalMinutes,
			Integer invalidLoginsBeforeLockout) {
		super();
		this.accountLockoutEnabled = accountLockoutEnabled;
		this.accountLockoutIntervalMinutes = accountLockoutIntervalMinutes;
		this.invalidLoginsBeforeLockout = invalidLoginsBeforeLockout;
	}

	public Boolean isAccountLockoutEnabled() {
		return accountLockoutEnabled;
	}

	public void setAccountLockoutEnabled(Boolean accountLockoutEnabled) {
		this.accountLockoutEnabled = accountLockoutEnabled;
	}

	public Integer getAccountLockoutIntervalMinutes() {
		return accountLockoutIntervalMinutes;
	}

	public void setAccountLockoutIntervalMinutes(Integer accountLockoutIntervalMinutes) {
		this.accountLockoutIntervalMinutes = accountLockoutIntervalMinutes;
	}

	public Integer getInvalidLoginsBeforeLockout() {
		return invalidLoginsBeforeLockout;
	}

	public void setInvalidLoginsBeforeLockout(Integer invalidLoginsBeforeLockout) {
		this.invalidLoginsBeforeLockout = invalidLoginsBeforeLockout;
	}
	
}
