package th.co.ais.enterprisecloud.automation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class LazybeeApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	public void testProvisioningService() {
		//this.restTemplate.postForEntity(url, request, responseType)
		//Greet greet = this.restTemplate.getForObject("/", Greet.class);
		//assertEquals("Hello World!", greet.getMessage());
	}

}
