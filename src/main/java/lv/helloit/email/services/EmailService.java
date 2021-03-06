package lv.helloit.email.services;

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

    public void sendTextMail(String recipient, String subject, String body) throws SparkPostException {

        client.sendMessage(fromEmail, recipient, subject, body, null);

    }

    public void sendHtmlMail(String recipient, String subject, String body) throws SparkPostException {

        client.sendMessage(fromEmail, recipient, subject, null, body);

    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }
}
