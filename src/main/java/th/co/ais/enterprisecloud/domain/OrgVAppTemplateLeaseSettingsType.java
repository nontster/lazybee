package th.co.ais.enterprisecloud.domain;

public class OrgVAppTemplateLeaseSettingsType {
	private Boolean deleteOnStorageLeaseExpiration;
	private Integer storageLeaseSeconds;
	
	public OrgVAppTemplateLeaseSettingsType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrgVAppTemplateLeaseSettingsType(Boolean deleteOnStorageLeaseExpiration, Integer storageLeaseSeconds) {
		super();
		this.deleteOnStorageLeaseExpiration = deleteOnStorageLeaseExpiration;
		this.storageLeaseSeconds = storageLeaseSeconds;
	}

	public Boolean isDeleteOnStorageLeaseExpiration() {
		return deleteOnStorageLeaseExpiration;
	}

	public void setDeleteOnStorageLeaseExpiration(Boolean deleteOnStorageLeaseExpiration) {
		this.deleteOnStorageLeaseExpiration = deleteOnStorageLeaseExpiration;
	}

	public Integer getStorageLeaseSeconds() {
		return storageLeaseSeconds;
	}

	public void setStorageLeaseSeconds(Integer storageLeaseSeconds) {
		this.storageLeaseSeconds = storageLeaseSeconds;
	}
	
}
