package lv.helloit.email;

import com.sparkpost.exception.SparkPostException;
import lv.helloit.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service("messageProcessor")
public class MessageProcessor {

    private final EmailService emailService;

    @Autowired
    public MessageProcessor(EmailService emailService) {
        this.emailService = emailService;
    }

    public Message process(Message<String> input) throws SparkPostException {
        String to = input.getHeaders().get("to", String.class);
        String subject = input.getHeaders().get("subject", String.class);
        String body = input.getHeaders().get("body", String.class);
//        String result = to.substring(1, to.length()-1) + " "
//                + subject.substring(1, subject.length()-1) + " "
//                + body.substring(1, body.length()-1);
        emailService.sendHtmlMail(to.substring(1, to.length()-1), subject.substring(1, subject.length()-1), body.substring(1, body.length()-1));
        return new GenericMessage<>("Successful");
    }

}
