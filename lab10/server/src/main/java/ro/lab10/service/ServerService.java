package ro.lab10.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.lab10.domain.Agency;
import ro.lab10.exceptions.AppException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@org.springframework.stereotype.Service
public class ServerService implements AppService {
    @Autowired
    private ExecutorService executorService;
    @Autowired
    private Service service;

    @Override
    public CompletableFuture<Iterable<Agency>> getAgencies() {
        return CompletableFuture.supplyAsync(() -> service.getAgencies(), executorService);
    }

    @Override
    public CompletableFuture<String> addAgency(String name, String address) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                service.addAgency(name, address);
                return "Agency was added";
            }
            catch (AppException e) {
                e.printStackTrace(System.err);
                return e.getMessage();
            }
        }, executorService);
    }


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
}
