package ro.lab10.service;

import java.util.concurrent.CompletableFuture;

public interface AppService {
    String LINE_SEPARATOR = "#";
    String ARGUMENTS_SEPARATOR = ",";

    // TODO
    String GET_AGENCIES = "getAgencies";
    String ADD_AGENCY = "addAgency";

    CompletableFuture<String> getAgencies();

    CompletableFuture<String> addAgency(String name, String address);
}
