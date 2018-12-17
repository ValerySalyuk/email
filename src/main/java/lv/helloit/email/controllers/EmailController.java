package lv.helloit.email.controllers;

import com.sparkpost.exception.SparkPostException;
import lv.helloit.email.EmailGateway;
import lv.helloit.email.SendMailRequest;
import lv.helloit.email.SendMailResponse;
import lv.helloit.email.SpringIntegrationConfig;
import lv.helloit.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/send")
public class EmailController {

    private final EmailService emailService;
    private final EmailGateway gateway;

    @Autowired
    public EmailController(EmailService emailService, EmailGateway gateway) {
        this.emailService = emailService;
        this.gateway = gateway;

    }

    @GetMapping("/sendTextMail")
    public SendMailResponse sendText(
            @RequestParam String recipientAddress,
            @RequestParam String subject,
            @RequestParam String body
    ) {
        return gateway.process(new SendMailRequest.Builder()
                .to(recipientAddress)
                .subject(subject)
                .body(body)
                .isHtml(false)
                .build());
    }

    @GetMapping("sendHtmlMail")
    public SendMailResponse sendHtml(
            @RequestParam String recipientAddress,
            @RequestParam String subject,
            @RequestParam String body
    ) {
        return gateway.process(new SendMailRequest.Builder()
                .to(recipientAddress)
                .subject(subject)
                .body(body)
                .isHtml(true)
                .build());
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
