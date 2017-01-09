package th.co.ais.enterprisecloud.domain;

import java.util.Date;

public class VmType {
	private String id;
	private String nonMobileNo;
    private String name;
    private String description;
    private String templateType;
    private String computerName;
    private VCpuType vCpu;
    private VMemoryType vMemory;
    private Integer storageSize;
    private String userName;
    private String password;
	private Date startDate;
	private Date endDate;
    
	public VmType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VmType(String name, String description, String templateType, String computerName, VCpuType vCpu, VMemoryType vMemory, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.description = description;
		this.templateType = templateType;
		this.computerName = computerName;
		this.vCpu = vCpu;
		this.vMemory = vMemory;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the nonMobileNo
	 */
	public String getNonMobileNo() {
		return nonMobileNo;
	}

	/**
	 * @param nonMobileNo the nonMobileNo to set
	 */
	public void setNonMobileNo(String nonMobileNo) {
		this.nonMobileNo = nonMobileNo;
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

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public VCpuType getvCpu() {
		return vCpu;
	}

	public void setvCpu(VCpuType vCpu) {
		this.vCpu = vCpu;
	}

	public VMemoryType getvMemory() {
		return vMemory;
	}

	public void setvMemory(VMemoryType vMemory) {
		this.vMemory = vMemory;
	}

	/**
	 * @return the storageSize
	 */
	public Integer getStorageSize() {
		return storageSize;
	}

	/**
	 * @param storageSize the storageSize to set
	 */
	public void setStorageSize(Integer storageSize) {
		this.storageSize = storageSize;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
     
}
