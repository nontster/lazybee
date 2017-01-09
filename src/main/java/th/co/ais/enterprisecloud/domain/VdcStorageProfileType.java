package th.co.ais.enterprisecloud.domain;

public class VdcStorageProfileType {
    private Boolean enabled;
    private Boolean def;
    private Integer limit;
    private String units;
    
	public VdcStorageProfileType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VdcStorageProfileType(Boolean enabled, Boolean def, Integer limit, String units) {
		super();
		this.enabled = enabled;
		this.def = def;
		this.limit = limit;
		this.units = units;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean isDef() {
		return def;
	}

	public void setDef(Boolean def) {
		this.def = def;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
    
}
