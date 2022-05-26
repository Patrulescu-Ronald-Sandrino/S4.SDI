package ro.lab11.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.lab11.core.domain.Estate;
import ro.lab11.core.domain.exceptions.AppException;
import ro.lab11.core.tools.Logger;
import ro.lab11.web.controller.Controller;
import ro.lab11.web.converter.EstateConvertor;
import ro.lab11.web.dto.EstateDTO;
import ro.lab11.web.dto.EstatesDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class AsyncEstateController implements Controller {
    public static final Logger logger = new Logger(AsyncEstateController.class);
    public String url = API_URL + "/estates";

    @Autowired
    ExecutorService executorService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EstateConvertor convertor;

    public CompletableFuture<Iterable<Estate>> getAll() {
        logger.traceStartArgs();
        return getAsyncCompletableFuture(() -> {
            EstatesDTO estatesDTO = restTemplate.getForObject(url + "/all", EstatesDTO.class);
            if (estatesDTO == null) {
                throw new AppException("Could not retrieve from server");
            }
            return estatesDTO.getEstates().stream().map(convertor::convertDTOToModel).collect(Collectors.toSet());
        }, executorService);
    }

    public CompletableFuture<String> add(String address, double surface, Long customerId) {
        logger.traceStartArgs(address, surface);
        return getAsyncCompletableFuture(() -> {
            restTemplate.postForObject(url + "/add", new EstateDTO(address, surface, customerId), EstatesDTO.class);
            return "added";
        }, executorService);
    }

    public CompletableFuture<String> update(Long id, String address, double surface, Long customerId) {
        logger.traceStartArgs(id, address, surface);
        return getAsyncCompletableFuture(() -> {
            var estateDTO = new EstateDTO(address, surface, customerId);
            estateDTO.setId(id);
            restTemplate.postForLocation(url + "/{id}/update", estateDTO, id);
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

    public CompletableFuture<Iterable<Estate>> findByAddressContainingOrderBySurface(String addressSubstring) {
        logger.traceStartArgs(addressSubstring);
        return getAsyncCompletableFuture(() -> {
            EstatesDTO estatesDTO = restTemplate.getForObject(url + "/all/find-by-address-containing-ordered-by-surface/{addressSubstring}", EstatesDTO.class, addressSubstring);
            if (estatesDTO == null) {
                throw new AppException("Could not retrieve from server");
            }
            return estatesDTO.getEstates().stream().map(convertor::convertDTOToModel).collect(Collectors.toSet());
        }, executorService);
    }
}
