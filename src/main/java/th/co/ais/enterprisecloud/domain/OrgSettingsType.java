package th.co.ais.enterprisecloud.domain;

public class OrgSettingsType {
	private OrgLeaseSettingsType orgLeaseSettings;
	private OrgGeneralSettingsType orgGeneralSettings;
	private OrgVAppTemplateLeaseSettingsType orgVAppTemplateLeaseSettings;
	private OrgPasswordPolicySettingsType orgPasswordPolicySettings;

	public OrgSettingsType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrgSettingsType(OrgLeaseSettingsType orgLeaseSettings, OrgGeneralSettingsType orgGeneralSettings,
			OrgVAppTemplateLeaseSettingsType orgVAppTemplateLeaseSettings,
			OrgPasswordPolicySettingsType orgPasswordPolicySettings) {

		this.orgLeaseSettings = orgLeaseSettings;
		this.orgGeneralSettings = orgGeneralSettings;
		this.orgVAppTemplateLeaseSettings = orgVAppTemplateLeaseSettings;
		this.orgPasswordPolicySettings = orgPasswordPolicySettings;
	}

	public OrgLeaseSettingsType getOrgLeaseSettings() {
		return orgLeaseSettings;
	}

	public void setOrgLeaseSettings(OrgLeaseSettingsType orgLeaseSettings) {
		this.orgLeaseSettings = orgLeaseSettings;
	}

	public OrgGeneralSettingsType getOrgGeneralSettings() {
		return orgGeneralSettings;
	}

	public void setOrgGeneralSettings(OrgGeneralSettingsType orgGeneralSettings) {
		this.orgGeneralSettings = orgGeneralSettings;
	}

	public OrgVAppTemplateLeaseSettingsType getOrgVAppTemplateLeaseSettings() {
		return orgVAppTemplateLeaseSettings;
	}

	public void setOrgVAppTemplateLeaseSettings(OrgVAppTemplateLeaseSettingsType orgVAppTemplateLeaseSettings) {
		this.orgVAppTemplateLeaseSettings = orgVAppTemplateLeaseSettings;
	}

	public OrgPasswordPolicySettingsType getOrgPasswordPolicySettings() {
		return orgPasswordPolicySettings;
	}

	public void setOrgPasswordPolicySettings(OrgPasswordPolicySettingsType orgPasswordPolicySettings) {
		this.orgPasswordPolicySettings = orgPasswordPolicySettings;
	}
	
}
