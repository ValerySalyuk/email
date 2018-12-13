package lv.helloit.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;

@Configuration
public class SpringIntegrationConfig {

    @Bean
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel responseChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "requestChannel", outputChannel = "responseChannel")
    public String handle(Message<String> input) {
        return "Hello " + input.getPayload();
    }

    @MessagingGateway
    public interface EmailGateway {

        @Gateway(requestChannel = "requestChannel", replyChannel = "responseChannel")
        String process(String payload);

    }

}
