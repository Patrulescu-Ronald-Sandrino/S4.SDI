package ro.lab11.web.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.lab11.web.controller.Controller;

@Configuration
@EnableWebMvc
@ComponentScan({"ro.lab11.web.controller", "ro.lab11.web.converter"})
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:4200", "http://localhost:8080")
                        .allowedOrigins("http://localhost:4200", Controller.URL)
                        .allowedMethods("GET", "PUT", "POST", "DELETE");
            }
        };
    }
}
