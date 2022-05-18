package ro.lab10.service;

import ro.lab10.tcp.TcpClient;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;


public class ClientService extends ExecutorAppService { // TODO: return just CompletableFuture<String>
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
