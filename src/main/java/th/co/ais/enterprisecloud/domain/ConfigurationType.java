package th.co.ais.enterprisecloud.domain;

import java.util.List;

public class ConfigurationType {
	private Boolean retainNetInfoAcrossDeployments;
	private FenceModeType fenceMode;
	private List<IpScopeType> ipScopes;
	
	public ConfigurationType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConfigurationType(Boolean retainNetInfoAcrossDeployments, FenceModeType fenceMode, List<IpScopeType> ipScopes) {
		super();
		this.retainNetInfoAcrossDeployments = retainNetInfoAcrossDeployments;
		this.fenceMode = fenceMode;
		this.ipScopes = ipScopes;
	}

	public Boolean isRetainNetInfoAcrossDeployments() {
		return retainNetInfoAcrossDeployments;
	}

	public void setRetainNetInfoAcrossDeployments(Boolean retainNetInfoAcrossDeployments) {
		this.retainNetInfoAcrossDeployments = retainNetInfoAcrossDeployments;
	}

	public FenceModeType getFenceMode() {
		return fenceMode;
	}

	public void setFenceMode(FenceModeType fenceMode) {
		this.fenceMode = fenceMode;
	}

	public List<IpScopeType> getIpScopes() {
		return ipScopes;
	}

	public void setIpScopes(List<IpScopeType> ipScopes) {
		this.ipScopes = ipScopes;
	}
	
}
