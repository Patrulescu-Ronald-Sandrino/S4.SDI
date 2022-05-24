package ro.lab11.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ro.lab11.core.domain.exceptions.AppException;
import ro.lab11.core.tools.Logger;
import ro.lab11.web.controller.Controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class AsyncAgencyController implements Controller {
    public static final Logger logger = new Logger(AsyncAgencyController.class);
    public String url = API_URL + "/agencies";

    @Autowired
    ExecutorService executorService;

    @Autowired
    private RestTemplate restTemplate;

    public CompletableFuture<String> test() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                logger.debug("start");
                String result = restTemplate.getForObject(url + "/test", String.class);
                if (result == null) {
                    throw new AppException("test result is null");
                }
                return result;
            }
            catch (ResourceAccessException e) {
                e.printStackTrace();
                throw new AppException("Inaccessible server: " + e.getMessage());
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new AppException(e.getMessage());
            }
        }, executorService);
    }

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
