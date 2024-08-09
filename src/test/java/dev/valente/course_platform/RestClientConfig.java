package dev.valente.course_platform;


import dev.valente.course_platform.common.HolderData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(){
        return RestClient.builder().build();
    }

    @Bean
    public HolderData aux(){
        return new HolderData();
    }
}
