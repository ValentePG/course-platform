package dev.valente.course_platform.testconfig;


import dev.valente.course_platform.common.DataHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class TestsConfig {

    @Bean
    public RestClient restClient(){
        return RestClient.builder().build();
    }

    @Bean
    public DataHolder dataHolder(){
        return new DataHolder();
    }
}
