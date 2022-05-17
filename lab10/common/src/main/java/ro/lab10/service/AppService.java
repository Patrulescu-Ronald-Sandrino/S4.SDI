package ro.lab10.service;

import java.util.concurrent.CompletableFuture;

public interface AppService {
    // TODO
    String ARGUMENTS_SEPARATOR = ",";
    String GET_AGENCIES = "getAgencies";
    String ADD_AGENCY = "addAgency";

    CompletableFuture<String> getAgencies();

    CompletableFuture<String> addAgency(String name, String address);
}
