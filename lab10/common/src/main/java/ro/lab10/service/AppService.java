package ro.lab10.service;

import ro.lab10.domain.Agency;

import java.util.concurrent.CompletableFuture;

public interface AppService {
    // TODO
    String GET_AGENCIES = "getAgencies";
    String ADD_AGENCY = "addAgency";

    CompletableFuture<Iterable<Agency>> getAgencies();

    CompletableFuture<String> addAgency(String name, String address);
}
