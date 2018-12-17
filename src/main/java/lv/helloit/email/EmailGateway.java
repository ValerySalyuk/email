package lv.helloit.email;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface EmailGateway {

    @Gateway(requestChannel = "requestChannel", replyChannel = "responseChannel")
    SendMailResponse process(SendMailRequest sendMailRequest);

}
