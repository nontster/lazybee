package th.co.ais.enterprisecloud.domain;

import java.util.List;

public class SubnetParticipationType {
	private String gateway;
	private String netmask;
	private List<IpRangeType> ipRanges;
	
	public SubnetParticipationType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubnetParticipationType(String gateway, String netmask, List<IpRangeType> ipRanges) {
		super();
		this.gateway = gateway;
		this.netmask = netmask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public List<IpRangeType> getIpRanges() {
		return ipRanges;
	}

	public void setIpRanges(List<IpRangeType> ipRanges) {
		this.ipRanges = ipRanges;
	}
	
}
