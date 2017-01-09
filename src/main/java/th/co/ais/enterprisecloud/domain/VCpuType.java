package th.co.ais.enterprisecloud.domain;

public class VCpuType {
    private Integer noOfCpus; 
    private Integer coresPerSocket;
    
	public VCpuType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VCpuType(Integer noOfCpus, Integer coresPerSocket) {
		super();
		this.noOfCpus = noOfCpus;
		this.coresPerSocket = coresPerSocket;
	}

	public Integer getNoOfCpus() {
		return noOfCpus;
	}

	public void setNoOfCpus(Integer noOfCpus) {
		this.noOfCpus = noOfCpus;
	}

	public Integer getCoresPerSocket() {
		return coresPerSocket;
	}

	public void setCoresPerSocket(Integer coresPerSocket) {
		this.coresPerSocket = coresPerSocket;
	}
    

}
