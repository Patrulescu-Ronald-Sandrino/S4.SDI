package ro.lab11.web.controller;


import org.springframework.web.client.ResourceAccessException;
import ro.lab11.core.domain.exceptions.AppException;
import ro.lab11.core.tools.IO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

public interface Controller { // TODO: idea make Controller an abstract class with the field executor, restService, convertor, logger and abstract method get inner url
    String URL = "http://localhost:8080";
    String API_URL = URL + "/api";

    default <T> CompletableFuture<T> getAsyncCompletableFuture(Supplier<T> supplier, ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return supplier.get();
            } catch (ResourceAccessException e) {
                e.printStackTrace();
                throw new AppException("Inaccessible server: " + e.getMessage());
            }
        }, executorService);
    }
}
