package ro.lab11.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

@Service
public abstract class ExecutorAppService implements AppService {
    @Autowired
    protected final ExecutorService executorService;

    protected ExecutorAppService(ExecutorService executorService) {
        this.executorService = executorService;
    }


    // L2

    public <T> CompletableFuture<T> getAsyncCompletableFuture(Supplier<T> tSupplier) {
        return CompletableFuture.supplyAsync(tSupplier, executorService);
    }
}
