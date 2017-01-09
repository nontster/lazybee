package th.co.ais.enterprisecloud.domain;

public class ComputeCapacityType {
	private CpuType cpu;
	private MemoryType memory;
	
	public ComputeCapacityType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComputeCapacityType(CpuType cpu, MemoryType memory) {
		super();
		this.cpu = cpu;
		this.memory = memory;
	}

	public CpuType getCpu() {
		return cpu;
	}

	public void setCpu(CpuType cpu) {
		this.cpu = cpu;
	}

	public MemoryType getMemory() {
		return memory;
	}

	public void setMemory(MemoryType memory) {
		this.memory = memory;
	}
	
}
