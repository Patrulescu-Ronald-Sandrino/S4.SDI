package ro.lab11.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.lab11.core.domain.Agency;
import ro.lab11.core.domain.Estate;
import ro.lab11.core.domain.Offer;
import ro.lab11.core.domain.OfferPK;
import ro.lab11.core.domain.exceptions.AppException;
import ro.lab11.core.tools.Logger;
import ro.lab11.web.controller.Controller;
import ro.lab11.web.converter.OfferConvertor;
import ro.lab11.web.dto.OfferDTO;
import ro.lab11.web.dto.OffersDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class AsyncOfferController implements Controller {
    public static final Logger logger = new Logger(AsyncOfferController.class);
    public String url = API_URL + "/offers";

    @Autowired
    ExecutorService executorService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OfferConvertor convertor;

    public CompletableFuture<Iterable<Offer>> getAll() {
        logger.traceStartArgs();
        return getAsyncCompletableFuture(() -> {
            OffersDTO offersDTO = restTemplate.getForObject(url + "/all", OffersDTO.class);
            if (offersDTO == null) {
                throw new AppException("Could not retrieve from server");
            }
            return offersDTO.getOffers().stream().map(convertor::convertDTOToModel).collect(Collectors.toSet());
        }, executorService);
    }

    public CompletableFuture<String> add(long agencyId, long estateId, double price) {
        logger.traceStartArgs(agencyId, estateId, price);
        return getAsyncCompletableFuture(() -> {
            OfferDTO offerDTO = new OfferDTO(price);
            offerDTO.setId(new OfferPK(agencyId, estateId));
            restTemplate.postForObject(url + "/add", offerDTO, OffersDTO.class);
            return "added";
        }, executorService);
    }

    public CompletableFuture<String> update(long agencyId, long estateId, double price) {
        logger.traceStartArgs(agencyId, estateId, price);
        return getAsyncCompletableFuture(() -> {
            var offerDTO = new OfferDTO(price);
            offerDTO.setId(new OfferPK(agencyId, estateId));
            restTemplate.postForLocation(url + "/{agencyId}&{estateId}/update", offerDTO, agencyId, estateId);
            return "updated";
        }, executorService);
    }

    public CompletableFuture<String> remove(long agencyId, long estateId) {
        logger.traceStartArgs(agencyId, estateId);
        return getAsyncCompletableFuture(() -> {
            restTemplate.postForLocation(url + "/{agencyId}&{estateId}/remove", null, agencyId, estateId);
            return "removed";
        }, executorService);
    }
}
