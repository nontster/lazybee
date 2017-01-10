package th.co.ais.enterprisecloud.automation;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("staging")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class LazybeeApplicationTests {

	@Value("${local.server.port}")
    private int port;
		
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	public void testProvisioningService() throws JAXBException {
		
		String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><organization name=\"Virgin Media Limited\" orderId=\"09101010102939\" caNumber=\"90111045678\" orderType=\"trial\" shortName=\"VMED\" xmlns=\"http://enterprisecloud.ais.co.th/bot\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><users><user email=\"john.doe@virgin.com\" fullName=\"John Doe\" phone=\"0613952200\" /></users><vms><vm coresPerSocket=\"2\" memorySize=\"4\" noOfCpus=\"2\" nonMobileNumber=\"1234567890\" osImageName=\"CENTOS7\" /></vms></organization>";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.add("Accept-Charset", "UTF-8");
		headers.add("Accept", "text/xml, application/json");
		
		HttpEntity<String> request = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + port + "/orgs", request, String.class);
		
		JAXBContext jaxbContextRes = JAXBContext.newInstance(th.co.ais.enterprisecloud.domain.response.OrganizationType.class);
		Unmarshaller unmarshaller = jaxbContextRes.createUnmarshaller();
		
		InputStream stream = new ByteArrayInputStream(response.getBody().getBytes(StandardCharsets.UTF_8));
		
		th.co.ais.enterprisecloud.domain.response.OrganizationType res = (th.co.ais.enterprisecloud.domain.response.OrganizationType) unmarshaller.unmarshal(stream);
		
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
		assertThat(res.getName()).isEqualTo("Trial-VMED");
		
	}

}
