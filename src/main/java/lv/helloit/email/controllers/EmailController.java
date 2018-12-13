package lv.helloit.email.controllers;

import com.sparkpost.exception.SparkPostException;
import lv.helloit.email.EmailGateway;
import lv.helloit.email.SpringIntegrationConfig;
import lv.helloit.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send")
public class EmailController {

    private final EmailService emailService;
    private final EmailGateway gateway;

    @Autowired
    public EmailController(EmailService emailService, EmailGateway gateway) {
        this.emailService = emailService;
        this.gateway = gateway;

    }

    @GetMapping
    public String send(
//            @RequestParam("recipient") String recipient,
//            @RequestParam("subject") String subject,
            @RequestParam("body") String body
    ) {
        return gateway.process(body);
    }

//    @PostMapping("/text")
//    public void sendText(
//            @RequestParam("recipient") String recipient,
//            @RequestParam("subject") String subject,
//            @RequestParam("body") String body
//            ) throws SparkPostException {
//
//        emailService.sendTextMail(recipient, subject, body);
//    }
//
//
//    @PostMapping("/html")
//    public void sendHtml(
//            @RequestParam("recipient") String recipient,
//            @RequestParam("subject") String subject,
//            @RequestParam("body") String body
//            ) throws SparkPostException {
//
//        emailService.sendHtmlMail(recipient, subject, body);
//    }


}
