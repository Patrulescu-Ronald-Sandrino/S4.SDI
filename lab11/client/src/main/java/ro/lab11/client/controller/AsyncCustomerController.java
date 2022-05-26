package ro.lab11.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.lab11.core.domain.Customer;
import ro.lab11.core.domain.exceptions.AppException;
import ro.lab11.core.tools.Logger;
import ro.lab11.web.controller.Controller;
import ro.lab11.web.converter.CustomerConvertor;
import ro.lab11.web.dto.CustomerDTO;
import ro.lab11.web.dto.CustomersDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class AsyncCustomerController implements Controller {
    public static final Logger logger = new Logger(AsyncCustomerController.class);
    public String url = API_URL + "/customers";

    @Autowired
    ExecutorService executorService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerConvertor convertor;

    public CompletableFuture<Iterable<Customer>> getAll() {
        logger.traceStartArgs();
        return getAsyncCompletableFuture(() -> {
            CustomersDTO customersDTO = restTemplate.getForObject(url + "/all", CustomersDTO.class);
            if (customersDTO == null) {
                throw new AppException("Could not retrieve from server");
            }
            return customersDTO.getCustomers().stream().map(convertor::convertDTOToModel).collect(Collectors.toSet());
        }, executorService);
    }

    public CompletableFuture<String> add(String name, String email) {
        logger.traceStartArgs(name, email);
        return getAsyncCompletableFuture(() -> {
            restTemplate.postForObject(url + "/add", new CustomerDTO(name, email), CustomersDTO.class);
            return "added";
        }, executorService);
    }

    public CompletableFuture<String> update(Long id, String name, String email) {
        logger.traceStartArgs(id, name, email);
        return getAsyncCompletableFuture(() -> {
            var customerDTO = new CustomerDTO(name, email);
            customerDTO.setId(id);
            restTemplate.postForLocation(url + "/{id}/update", customerDTO, id);
            return "updated";
        }, executorService);
    }

    public CompletableFuture<String> remove(Long id) {
        logger.traceStartArgs(id);
        return getAsyncCompletableFuture(() -> {
            restTemplate.postForLocation(url + "/{id}/remove", null, id);
            return "removed";
        }, executorService);
    }
}
