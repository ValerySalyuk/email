package lv.helloit.email;

import com.sparkpost.exception.SparkPostException;
import lv.helloit.email.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@SpringBootApplication
@ImportResource("classpath:spring-config.xml")
public class EmailApplication {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(EmailApplication.class, args);

//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

//		EmailService emailService = context.getBean("emailService", EmailService.class);

//		EmailService emailService = context.getBean(EmailService.class);

		/*emailService.sendHtmlMail(
				"valery.salyuk@gmail.com",
				"Java test mail",
				"Test e-mail");*/

//		LOGGER.info("Application started");
//		LOGGER.info("Press Enter to exit");
//		System.in.read();
//		context.close();

	}
}
