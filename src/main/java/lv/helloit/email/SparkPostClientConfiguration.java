package lv.helloit.email;

import com.sparkpost.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkPostClientConfiguration {

    @Value("${api.key}")
    private String apiKey;

    @Bean
    public Client client () {
        return new Client(apiKey);
    }

}
