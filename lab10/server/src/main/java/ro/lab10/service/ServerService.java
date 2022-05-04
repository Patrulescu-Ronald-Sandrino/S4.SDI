package ro.lab10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.lab10.tcp.TcpServer;

import java.util.concurrent.ExecutorService;

@Component
public class ServerService implements AppService {

    private final ExecutorService executorService;

    /*
    TODO: add services:
        - CustomerService
        - EstateService
        - CityService
        - CityEstateService
     */

    @Autowired
    public ServerService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void addHandlers(TcpServer tcpServer) {

    }
}
