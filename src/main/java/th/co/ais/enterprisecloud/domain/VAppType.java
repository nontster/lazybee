package th.co.ais.enterprisecloud.domain;

import java.util.List;

public class VAppType {
	private String name;
	private String description;
    private List <VmType> vms;
    
	public VAppType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VAppType(String name, String description, List <VmType> vms) {
		super();
		this.name = name;
		this.description = description;
		this.setVms(vms);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the vms
	 */
	public List <VmType> getVms() {
		return vms;
	}

	/**
	 * @param vms the vms to set
	 */
	public void setVms(List <VmType> vms) {
		this.vms = vms;
	}

}
