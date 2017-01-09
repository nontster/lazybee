package th.co.ais.enterprisecloud.domain;

public class OrgLeaseSettingsType {
	private Boolean deleteOnStorageLeaseExpiration;
	private Integer deploymentLeaseSeconds;
	private Integer storageLeaseSeconds;
	
	public OrgLeaseSettingsType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrgLeaseSettingsType(Boolean deleteOnStorageLeaseExpiration, Integer deploymentLeaseSeconds,
			Integer storageLeaseSeconds) {
		super();
		this.deleteOnStorageLeaseExpiration = deleteOnStorageLeaseExpiration;
		this.deploymentLeaseSeconds = deploymentLeaseSeconds;
		this.storageLeaseSeconds = storageLeaseSeconds;
	}

	public Boolean isDeleteOnStorageLeaseExpiration() {
		return deleteOnStorageLeaseExpiration;
	}

	public void setDeleteOnStorageLeaseExpiration(Boolean deleteOnStorageLeaseExpiration) {
		this.deleteOnStorageLeaseExpiration = deleteOnStorageLeaseExpiration;
	}

	public Integer getDeploymentLeaseSeconds() {
		return deploymentLeaseSeconds;
	}

	public void setDeploymentLeaseSeconds(Integer deploymentLeaseSeconds) {
		this.deploymentLeaseSeconds = deploymentLeaseSeconds;
	}

	public Integer getStorageLeaseSeconds() {
		return storageLeaseSeconds;
	}

	public void setStorageLeaseSeconds(Integer storageLeaseSeconds) {
		this.storageLeaseSeconds = storageLeaseSeconds;
	}

}
