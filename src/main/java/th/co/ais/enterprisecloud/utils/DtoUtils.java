package th.co.ais.enterprisecloud.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import th.co.ais.enterprisecloud.domain.OrderType;
import th.co.ais.enterprisecloud.domain.OrganizationType;
import th.co.ais.enterprisecloud.domain.VmType;

@Component
public class DtoUtils {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public OrganizationType transfer(th.co.ais.enterprisecloud.domain.request.OrganizationType in) {

		logger.debug("DtoUtils.transfer() called");
		logger.debug("OrganizationType: "+in.toString());
		
		OrganizationType out = new OrganizationType();
				
		out.setCaNumber(in.getCaNumber());
		out.setName(in.getName());
		out.setShortName(in.getShortName());
		out.setOrderId(in.getOrderId());		
		out.setOrderType(OrderType.valueOf(in.getOrderType().toUpperCase()));
		
		// Transfer UserType object
		th.co.ais.enterprisecloud.domain.UserType inUser = new th.co.ais.enterprisecloud.domain.UserType();	
		inUser.setEmailAddress(in.getUsers().getUser().get(0).getEmail());
		inUser.setFullName(in.getUsers().getUser().get(0).getFullName());
		inUser.setPhone(in.getUsers().getUser().get(0).getPhone());
				
		th.co.ais.enterprisecloud.domain.UserType outUser = new th.co.ais.enterprisecloud.domain.UserType();
		outUser.setEmailAddress(inUser.getEmailAddress());
		outUser.setFullName(inUser.getFullName());
		outUser.setPhone(inUser.getPhone());
		
		out.setUser(outUser);

		// Transfer VmsType
		List <th.co.ais.enterprisecloud.domain.request.VmType> inVms = new ArrayList<th.co.ais.enterprisecloud.domain.request.VmType>();
		inVms = in.getVms().getVm();
		
		th.co.ais.enterprisecloud.domain.VAppType outVapp = new th.co.ais.enterprisecloud.domain.VAppType();
		
		List <VmType> outVms = new ArrayList<VmType>(); 
		
		for(th.co.ais.enterprisecloud.domain.request.VmType inVm :inVms){
			
			th.co.ais.enterprisecloud.domain.VmType outVm = new th.co.ais.enterprisecloud.domain.VmType();
			
			outVm.setName(inVm.getVmName());			
			outVm.setComputerName(inVm.getComputerName());
			
			if(inVm.getStart() != null)
				outVm.setStartDate(inVm.getStart().toGregorianCalendar().getTime());
			
			if(inVm.getEnd() != null)
			outVm.setEndDate(inVm.getEnd().toGregorianCalendar().getTime());	
			
			outVm.setTemplateType(inVm.getOsImageName());
			outVm.setNonMobileNo(inVm.getNonMobileNumber());
			
			th.co.ais.enterprisecloud.domain.VCpuType vCpu = new th.co.ais.enterprisecloud.domain.VCpuType();
			vCpu.setCoresPerSocket(inVm.getCoresPerSocket());
			vCpu.setNoOfCpus(inVm.getNoOfCpus());			
			outVm.setvCpu(vCpu);
			
			th.co.ais.enterprisecloud.domain.VMemoryType vMemory = new th.co.ais.enterprisecloud.domain.VMemoryType();
			vMemory.setMemorySize( BigInteger.valueOf(inVm.getMemorySize()));
			outVm.setvMemory(vMemory);
			
			if(inVm.getStorageSize() != null)
				outVm.setStorageSize(Integer.valueOf(inVm.getStorageSize()));
				
			outVms.add(outVm);
		}

		outVapp.setVms(outVms);
		out.setvApp(outVapp);
		
		return out;
	}
	
}
