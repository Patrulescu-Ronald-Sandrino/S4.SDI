package ro.lab10.service;

import ro.lab10.tcp.TcpClient;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;


public class ClientService extends ExecutorAppService {
    private final TcpClient tcpClient;

    public ClientService(ExecutorService executorService, TcpClient tcpClient) {
        super(executorService);
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<String> getAgencies() {
        return sendAndReceiveBody(GET_AGENCIES);
    }

    @Override
    public CompletableFuture<String> addAgency(String name, String address) {
        return sendAndReceiveBody(ADD_AGENCY, convertArgumentsToMessageBody(Stream.of(name, address)));
    }

    @Override
    public CompletableFuture<String> updateAgency(Long id, String name, String address) {
        return sendAndReceiveBody(UPDATE_AGENCY, convertArgumentsToMessageBody(Stream.of(id, name, address)));
    }

    @Override
    public CompletableFuture<String> removeAgency(Long id) {
        return sendAndReceiveBody(REMOVE_AGENCY, id.toString());
    }

    @Override
    public CompletableFuture<String> getCustomers() {
        return sendAndReceiveBody(GET_CUSTOMERS);
    }

    @Override
    public CompletableFuture<String> addCustomer(String name, String email) {
        return sendAndReceiveBody(ADD_CUSTOMER, convertArgumentsToMessageBody(Stream.of(name, email)));
    }

    @Override
    public CompletableFuture<String> updateCustomer(Long id, String name, String email) {
        return sendAndReceiveBody(UPDATE_CUSTOMER, convertArgumentsToMessageBody(Stream.of(id, name, email)));
    }

    @Override
    public CompletableFuture<String> removeCustomer(Long id) {
        return sendAndReceiveBody(REMOVE_CUSTOMER, id.toString());
    }

    @Override
    public CompletableFuture<String> getEstates() {
        return sendAndReceiveBody(GET_ESTATES);
    }

    @Override
    public CompletableFuture<String> addEstate(String address, double surface) {
        return sendAndReceiveBody(ADD_ESTATE, convertArgumentsToMessageBody(Stream.of(address, surface)));
    }

    @Override
    public CompletableFuture<String> updateEstate(Long id, String address, double surface) {
        return sendAndReceiveBody(UPDATE_ESTATE, convertArgumentsToMessageBody(Stream.of(id, address, surface)));
    }

    @Override
    public CompletableFuture<String> removeEstate(Long id) {
        return sendAndReceiveBody(REMOVE_ESTATE, id.toString());
    }

    @Override
    public CompletableFuture<String> getOffers() {
        return sendAndReceiveBody(GET_OFFERS);
    }

    @Override
    public CompletableFuture<String> addOffer(Long agencyId, Long estateId, double price) {
        return sendAndReceiveBody(ADD_OFFER, convertArgumentsToMessageBody(Stream.of(agencyId, estateId, price)));
    }

    @Override
    public CompletableFuture<String> updateOffer(Long agencyId, Long estateId, double price) {
        return sendAndReceiveBody(UPDATE_OFFER, convertArgumentsToMessageBody(Stream.of(agencyId, estateId, price)));
    }

    @Override
    public CompletableFuture<String> removeOffer(Long agencyId, Long estateId) {
        return sendAndReceiveBody(REMOVE_OFFER, convertArgumentsToMessageBody(Stream.of(agencyId, estateId)));
    }

    // L2

    private CompletableFuture<String> sendAndReceiveBody(String header, String body) {
        return CompletableFuture.supplyAsync(() -> Arrays.stream(tcpClient.sendAndReceiveBody(header, body).split(LINE_SEPARATOR)).reduce("", (s, s2) -> s + s2 + '\n'), executorService);
    }

    private CompletableFuture<String> sendAndReceiveBody(String header) {
        return sendAndReceiveBody(header, "");
    }

    private String convertArgumentsToMessageBody(Stream<Object> arguments) {
        return arguments
                .map(Object::toString)
                .reduce((s, s2) -> s + AppService.ARGUMENTS_SEPARATOR + s2)
                .orElse("");
    }
}
