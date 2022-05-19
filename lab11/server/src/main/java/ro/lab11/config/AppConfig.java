package ro.lab11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan({"ro.lab11.domain.validators", "ro.lab11.repository", "ro.lab11.service", "ro.lab11.tcp"})
public class AppConfig {
    @Bean
    ExecutorService executorService() {
        return Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }
}
