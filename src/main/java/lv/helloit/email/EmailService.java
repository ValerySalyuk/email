package lv.helloit.email;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${from.email}")
    private String fromEmail;

    private final Client client;

    @Autowired
    public EmailService(Client client) {
        this.client = client;
    }

    public void sendMail(String recipient, String subject, String body) throws SparkPostException {

        client.sendMessage(
                fromEmail,
                recipient,
                subject,
                body,
                "<b>HTML " + body + "</b>");

    }

}
