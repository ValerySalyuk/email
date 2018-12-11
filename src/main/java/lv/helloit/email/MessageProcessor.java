package lv.helloit.email;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service("messageProcessor")
public class MessageProcessor {

    public Message process(Message<String> input) {
        String result = "Hello " + input.getPayload();
        return MessageBuilder
                .createMessage(result, new MessageHeaders(input.getHeaders()));
    }

}
