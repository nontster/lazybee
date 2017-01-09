package th.co.ais.enterprisecloud.domain;

public class CloudResourcesType {
	private ProviderVdcType providerVdc;
	private NetworkPoolType networkPool;
	private ExternalNetworkType externalNetwork;
	private CatalogType catalog;
	
	public CloudResourcesType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CloudResourcesType(ProviderVdcType providerVdc, NetworkPoolType networkPool, ExternalNetworkType externalNetwork, CatalogType catalog) {
		super();
		this.providerVdc = providerVdc;
		this.networkPool = networkPool;
		this.externalNetwork = externalNetwork;
		this.setCatalog(catalog);
	}

	public ProviderVdcType getProviderVdc() {
		return providerVdc;
	}

	public void setProviderVdc(ProviderVdcType providerVdc) {
		this.providerVdc = providerVdc;
	}

	public NetworkPoolType getNetworkPool() {
		return networkPool;
	}

	public void setNetworkPool(NetworkPoolType networkPool) {
		this.networkPool = networkPool;
	}

	public ExternalNetworkType getExternalNetwork() {
		return externalNetwork;
	}

	public void setExternalNetwork(ExternalNetworkType externalNetwork) {
		this.externalNetwork = externalNetwork;
	}

	public CatalogType getCatalog() {
		return catalog;
	}

	public void setCatalog(CatalogType catalog) {
		this.catalog = catalog;
	}
}
