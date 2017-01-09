package th.co.ais.enterprisecloud.domain;

import java.math.BigInteger;

public class VMemoryType {
	private BigInteger memorySize;

	public VMemoryType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VMemoryType(BigInteger memorySize) {
		super();
		this.memorySize = memorySize;
	}

	public BigInteger getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(BigInteger memorySize) {
		this.memorySize = memorySize;
	}
	
}
