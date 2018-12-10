package lv.helloit.email;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${api.key}")
    private String apiKey;
    @Value("${from.email}")
    private String fromEmail;

    public void sendMail(String recipient, String subject, String body) throws SparkPostException {

        Client client = new Client(apiKey);

        client.sendMessage(
                fromEmail,
                recipient,
                subject,
                body,
                "<b>HTML " + body + "</b>");

    }

}
