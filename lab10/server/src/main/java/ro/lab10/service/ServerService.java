package ro.lab10.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;

@org.springframework.stereotype.Service
public class ServerService implements AppService {
    @Autowired
    private ExecutorService executorService;
    @Autowired
    private Service service;


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
