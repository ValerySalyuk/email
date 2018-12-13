package lv.helloit.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

@Configuration
public class SpringIntegrationConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpringIntegrationConfig.class);

    @Bean
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel responseChannel() {
        return new DirectChannel();
    }

//    @Bean
//    public DirectChannel handleChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public DirectChannel logChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public DirectChannel filterChannel() {
//        return new DirectChannel();
//    }

    @Bean
    public IntegrationFlow emailFlow() {
        return IntegrationFlows
                .from("requestChannel")
                .<String>filter(payload -> !payload.contains("test"))
                //.filter(this::filter)
                .log(LoggingHandler.Level.INFO, "Incoming request: ", m -> m.getPayload())
                .<String>handle((payload, headers) -> "Hello " + payload)
                .log(LoggingHandler.Level.INFO, "Sending response: ", m -> m.getPayload())
                .channel("responseChannel")
                .get();
    }

//    @Filter(inputChannel = "filterChannel", outputChannel = "requestChannel")
//    public boolean filter(Message<String> input) {
//        return !input.getPayload().contains("test");
//    }

//    @ServiceActivator(inputChannel = "handleChannel", outputChannel = "logChannel")
//    public Message<String> handle(Message<String> input) {
//        LOGGER.info("Handling message");
//        return new GenericMessage<>("Hello " + input.getPayload());
//    }

//    @ServiceActivator(inputChannel = "requestChannel", outputChannel = "handleChannel")
//    public Message<String> requestLogger(Message<String> input) {
//        LOGGER.info("Request: " + input.getPayload());
//        return input;
//    }

//    @ServiceActivator(inputChannel = "logChannel", outputChannel = "responseChannel")
//    public Message<String> responseLogger(Message<String> input) {
//        LOGGER.info("Response: " + input.getPayload());
//        return input;
//    }

}
