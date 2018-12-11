package lv.helloit.email;

import com.sparkpost.exception.SparkPostException;
import lv.helloit.email.services.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EmailApplication {

	public static void main(String[] args) throws SparkPostException {

		/*ConfigurableApplicationContext context = */SpringApplication.run(EmailApplication.class, args);

		/*EmailService emailService = context.getBean(EmailService.class);

		emailService.sendHtmlMail(
				"valery.salyuk@gmail.com",
				"Java test mail",
				"Test e-mail");*/

	}
}
