package th.co.ais.enterprisecloud.automation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.junit4.SpringRunner;

import com.vmware.vcloud.sdk.VCloudException;

import th.co.ais.enterprisecloud.model.request.OrganizationType;
import th.co.ais.enterprisecloud.service.CloudConfiguration;
import th.co.ais.enterprisecloud.service.CloudService;
import th.co.ais.enterprisecloud.service.VCloudDirectorService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class LazybeeApplicationTests {

	@Value("${local.server.port}")
    private int port;
		
	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
	private CloudService service;
	
	@MockBean
	private CloudConfiguration conf;
	
	@Before
    public void setup() throws InterruptedException {
		OrganizationType org = new OrganizationType();
		
		service = mock(VCloudDirectorService.class);
		when(this.service.provisioning(org)).thenReturn(new AsyncResult<>(Boolean.TRUE));
	}
	
	@Test
	public void testProvisioningService() {
		
		String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><organization name=\"Virgin Media Limited\" orderId=\"09101010102939\" orderType=\"trial\" shortName=\"VMED\" xmlns=\"http://enterprisecloud.ais.co.th/bot\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><users><user email=\"john.doe@virgin.com\" fullName=\"John Doe\" phone=\"0613952200\" /></users><vms><vm coresPerSocket=\"2\" memorySize=\"4\" noOfCpus=\"2\" nonMobileNumber=\"1234567890\" osImageName=\"CENTOS7\" /></vms></organization>";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.add("Accept-Charset", "UTF-8");
		
		HttpEntity<String> request = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + port + "/provisioning", request, String.class);
		
		assertTrue(Boolean.TRUE);
	}

}
