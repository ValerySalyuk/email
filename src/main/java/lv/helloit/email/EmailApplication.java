package lv.helloit.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
//@ImportResource("classpath:spring-config.xml")
public class EmailApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmailApplication.class, args);

	}
}
