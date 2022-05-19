package ro.lab11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.lab11.core.ClientService;
import ro.lab11.tcp.TcpClient;
import ro.lab11.ui.ConsoleMenuUI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ClientConfig {
    @Bean
    ConsoleMenuUI console() {
        return new ConsoleMenuUI(service(), executor());
    }

    private ClientService service() {
        return new ClientService(executor(), tcpClient());
    }

    @Bean
    ExecutorService executor() {
        return Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    private TcpClient tcpClient() {
        return new TcpClient();
    }
}
