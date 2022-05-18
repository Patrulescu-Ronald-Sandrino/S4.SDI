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

    @Override
    public CompletableFuture<String> updateAgency(Long id, String name, String address) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.updateAgency(id, name, address);
            return "Agency was updated";
        });
    }

    @Override
    public CompletableFuture<String> removeAgency(Long id) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.removeAgency(id);
            return "Agency was removed";
        });
    }

    @Override
    public CompletableFuture<String> getCustomers() {
        return getAsyncCompletableFutureForGetAll(() -> service.getCustomers());
    }

    @Override
    public CompletableFuture<String> addCustomer(String name, String email) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.addCustomer(name, email);
            return "Customer was added";
        });
    }

    @Override
    public CompletableFuture<String> updateCustomer(Long id, String name, String email) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.updateCustomer(id, name, email);
            return "Customer was updated";
        });
    }

    @Override
    public CompletableFuture<String> removeCustomer(Long id) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.removeCustomer(id);
            return "Customer was removed";
        });
    }

    @Override
    public CompletableFuture<String> getEstates() {
        return getAsyncCompletableFutureForGetAll(() -> service.getEstates());
    }

    public CompletableFuture<String> addEstate(String address, double surface) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.addEstate(address, surface);
            return "Estate was added";
        });
    }

    @Override
    public CompletableFuture<String> updateEstate(Long id, String address, double surface) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.updateEstate(id, address, surface);
            return "Estate was updated";
        });
    }

    @Override
    public CompletableFuture<String> removeEstate(Long id) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.removeEstate(id);
            return "Estate was removed";
        });
    }

    @Override
    public CompletableFuture<String> getOffers() {
        return getAsyncCompletableFutureForGetAll(() -> service.getOffers());
    }

    @Override
    public CompletableFuture<String> addOffer(Long agencyId, Long estateId, double price) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.addOffer(agencyId, estateId, price);
            return "Offer was added";
        });
    }

    @Override
    public CompletableFuture<String> updateOffer(Long agencyId, Long estateId, double price) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.updateOffer(agencyId, estateId, price);
            return "Offer was updated";
        });
    }

    @Override
    public CompletableFuture<String> removeOffer(Long agencyId, Long estateId) {
        return getAsyncCompletableFutureForExceptionableServiceCall(() -> {
            service.removeOffer(agencyId, estateId);
            return "Offer was removed";
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
