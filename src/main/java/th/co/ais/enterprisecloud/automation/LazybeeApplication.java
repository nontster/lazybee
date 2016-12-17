package th.co.ais.enterprisecloud.automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("th.co.ais.enterprisecloud")
public class LazybeeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LazybeeApplication.class, args);
	}
}
