package ro.lab10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.lab10.tcp.TcpServer;

import java.util.concurrent.ExecutorService;

@Component
public class ServerService implements AppService {
    private final ExecutorService executorService;


    /*
     Agency m:n Estates
        An agency sells/offers more estates
        The same estate can be sold/offered by more companies
      Customer 1:n Estates
        A customer can own 1 or more estates
     */

    /*
    TODO: add services:
        - AgencyService
        - CustomerService
        - EstateService
        - ?OfferService
     */
    @Autowired
    public ServerService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void addHandlers(TcpServer tcpServer) {

    }
}
