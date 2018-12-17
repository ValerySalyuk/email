package lv.helloit.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.Message;

import java.util.Collections;
import java.util.List;

@Configuration
public class SpringIntegrationConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpringIntegrationConfig.class);

    @Autowired
    private final MessageProcessor messageProcessor;

    public SpringIntegrationConfig(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    @Bean
    public DirectChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel responseChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel validMessageChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel invalidMessageChannel() {
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
//                .<String>filter(payload -> !payload.contains("test"))
                //.filter(this::filter)
                .log(LoggingHandler.Level.INFO, "Incoming request: ", m -> m.getPayload())
                .route((SendMailRequest payload) -> messageRouter(payload))
                .get();
    }

    @Bean
    public IntegrationFlow validMessageFlow(DirectChannel validMessageChannel) {
        return IntegrationFlows
                .from(validMessageChannel)
                .handle(messageProcessor)
                .log(LoggingHandler.Level.INFO, "Sending response: ", m -> m.getPayload())
                .channel("responseChannel")
                .get();
    }

    @Bean
    public IntegrationFlow invalidMessageFlow(DirectChannel invalidMessageChannel) {
        return IntegrationFlows
                .from(invalidMessageChannel)
                .transform(m -> new SendMailResponse.Builder()
                        .successful(false)
                        .errorMessage("Validation failed")
                        .build())
                .log(LoggingHandler.Level.WARN, "Sending response: ", Message::getPayload)
                .channel("responseChannel")
                .get();
    }

    @Router
    public List<String> messageRouter(SendMailRequest input) {

        boolean valid = true;

        if (input.getBody().contains("don't send")) {
            valid = false;
        }

        if (!input.getTo().contains("@") || !input.getTo().contains(".")
                ) {
            valid = false;
        }

        if (valid) {
            return Collections.singletonList("validMessageChannel");
        } else {
            return Collections.singletonList("invalidMessageChannel");
        }

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
