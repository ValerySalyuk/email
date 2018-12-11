package lv.helloit.email.controllers;

import com.sparkpost.exception.SparkPostException;
import lv.helloit.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/text")
    public void sendText(
            @RequestParam("recipient") String recipient,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body
            ) throws SparkPostException {

        emailService.sendTextMail(recipient, subject, body);
    }


    @PostMapping("/html")
    public void sendHtml(
            @RequestParam("recipient") String recipient,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body
            ) throws SparkPostException {

        emailService.sendHtmlMail(recipient, subject, body);
    }


}
