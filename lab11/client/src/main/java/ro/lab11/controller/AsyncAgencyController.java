package ro.lab11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.lab11.domain.Agency;
import ro.lab11.tools.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Component
public class AsyncAgencyController implements AsyncController {
    public static final Logger logger = new Logger(AsyncAgencyController.class);
    public String url = API_URL + "agencies";

    @Autowired
    ExecutorService executorService;

    @Autowired
    private RestTemplate restTemplate;

//    public CompletableFuture<Iterable<Agency>> getAgencies() {
//        logger.trace("start");
//        return CompletableFuture.supplyAsync(() -> {
//
//        });
//
//    }
//
//    public CompletableFuture<String> addAgency(String name, String address) {
//
//    }
}
