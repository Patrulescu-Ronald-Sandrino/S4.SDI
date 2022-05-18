package ro.lab10.service;

import java.util.concurrent.CompletableFuture;

public interface AppService {
    String LINE_SEPARATOR = "#";
    String ARGUMENTS_SEPARATOR = ",";


    String GET_AGENCIES = "getAgencies";
    String ADD_AGENCY = "addAgency";
    String UPDATE_AGENCY = "updateAgency";
    String REMOVE_AGENCY = "removeAgency";
    
    String GET_CUSTOMERS = "getCustomers";
    String ADD_CUSTOMER = "addCustomer";
    String UPDATE_CUSTOMER = "updateCustomer";
    String REMOVE_CUSTOMER = "removeCustomer";

    String GET_ESTATES = "getEstates";
    String ADD_ESTATE = "addEstate";
    String UPDATE_ESTATE = "updateEstate";
    String REMOVE_ESTATE = "removeEstate";

    String GET_OFFERS = "getOffers";
    String ADD_OFFER = "addOffer";
    String UPDATE_OFFER = "updateOffer";
    String REMOVE_OFFER = "removeOffer";

    String GET_ESTATE_OFFERS = "getEstateOffers";

    
    CompletableFuture<String> getAgencies();
    CompletableFuture<String> addAgency(String name, String address);
    CompletableFuture<String> updateAgency(Long id, String name, String address);
    CompletableFuture<String> removeAgency(Long id);

    CompletableFuture<String> getCustomers();
    CompletableFuture<String> addCustomer(String name, String email);
    CompletableFuture<String> updateCustomer(Long id, String name, String email);
    CompletableFuture<String> removeCustomer(Long id);

    CompletableFuture<String> getEstates();
    CompletableFuture<String> addEstate(String address, double surface);
    CompletableFuture<String> updateEstate(Long id, String address, double surface);
    CompletableFuture<String> removeEstate(Long id);

    CompletableFuture<String> getOffers();
    CompletableFuture<String> addOffer(Long agencyId, Long estateId, double price);
    CompletableFuture<String> updateOffer(Long agencyId, Long estateId, double price);
    CompletableFuture<String> removeOffer(Long agencyId, Long estateId);

    CompletableFuture<String> getEstateOffers(Long id);
}
