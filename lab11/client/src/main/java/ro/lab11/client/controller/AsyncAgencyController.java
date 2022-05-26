package ro.lab11.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.lab11.core.domain.Agency;
import ro.lab11.core.domain.exceptions.AppException;
import ro.lab11.core.tools.Logger;
import ro.lab11.web.controller.Controller;
import ro.lab11.web.converter.AgencyConvertor;
import ro.lab11.web.dto.AgenciesDTO;
import ro.lab11.web.dto.AgencyDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class AsyncAgencyController implements Controller {
    public static final Logger logger = new Logger(AsyncAgencyController.class);
    public String url = API_URL + "/agencies";

    @Autowired
    ExecutorService executorService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AgencyConvertor convertor;

    public CompletableFuture<String> test() {
        logger.traceStartArgs();
//        new Throwable().printStackTrace();
        Supplier<String> stringSupplier = () -> {
            logger.trace("START", 4);
            String result = restTemplate.getForObject(url + "/test", String.class);
            if (result == null) {
                throw new AppException("test() result is null");
            }
            return result;
        };
        return getAsyncCompletableFuture(stringSupplier, executorService);
    }

    public CompletableFuture<Iterable<Agency>> getAll() {
        logger.traceStartArgs();
        return getAsyncCompletableFuture(() -> {
            AgenciesDTO agenciesDTO = restTemplate.getForObject(url + "/all", AgenciesDTO.class);
            if (agenciesDTO == null) {
                throw new AppException("Could not retrieve from server");
            }
            return agenciesDTO.getAgencies().stream().map(convertor::convertDTOToModel).collect(Collectors.toSet());
        }, executorService);
    }

    public CompletableFuture<String> add(String name, String address) {
        logger.traceStartArgs(name, address);
        return getAsyncCompletableFuture(() -> {
            restTemplate.postForObject(url + "/add", new AgencyDTO(name, address), AgenciesDTO.class);
            return "added";
        }, executorService);
    }

    public CompletableFuture<String> update(Long id, String name, String address) {
        logger.traceStartArgs(id, name, address);
        return getAsyncCompletableFuture(() -> {
            var agencyDTO = new AgencyDTO(name, address);
            agencyDTO.setId(id);
            restTemplate.postForLocation(url + "/{id}/update", agencyDTO, id);
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
