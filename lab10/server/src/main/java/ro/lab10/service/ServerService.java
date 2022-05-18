package ro.lab10.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.lab10.exceptions.AppException;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;


@org.springframework.stereotype.Service
public class ServerService extends ExecutorAppService {
    @Autowired
    private Service service;

    @Autowired
    protected ServerService(ExecutorService executorService) {
        super(executorService);
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

    @Override
    public CompletableFuture<String> getAgencies() {
        return getAsyncCompletableFutureForGetAll(() -> service.getAgencies());
    }

    @Override
    public CompletableFuture<String> addAgency(String name, String address) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.addAgency(name, address);
            return "Agency was added";
        });
    }

    // L2

    private <T> CompletableFuture<String> getAsyncCompletableFutureForGetAll(Supplier<List<T>> listSupplier) {
        return getAsyncCompletableFuture(() ->
                listSupplier.get().stream()
                        .map(Objects::toString)
                        .map(agency -> agency + LINE_SEPARATOR)
                        .reduce(String::concat)
                        .orElse("Empty"));
    }

    private CompletableFuture<String> getAsyncCompletableFutureForExceptionableServiceCall(Supplier<String> supplier) {
        return getAsyncCompletableFuture(() -> {
            try {
                return supplier.get();
            }
            catch (AppException e) {
                e.printStackTrace(System.err);
                return e.getMessage();
            }
        });
    }
}
