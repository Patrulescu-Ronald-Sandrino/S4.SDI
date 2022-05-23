package ro.lab11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan({"ro.lab11.ui", "ro.lab11.controller"})
public class ClientConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    ExecutorService executorService() {
        return Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }
}
