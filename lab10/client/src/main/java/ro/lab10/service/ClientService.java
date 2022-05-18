package ro.lab10.service;

import ro.lab10.tcp.TcpClient;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;


public class ClientService implements AppService {
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public ClientService(ExecutorService executorService, TcpClient tcpClient) { // TODO: return strings
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<String> getAgencies() {
        return sendAndReceiveBody(GET_AGENCIES);
//        return CompletableFuture.supplyAsync(() -> {
//            var request = new Message(GET_AGENCIES);
//            var response = tcpClient.sendAndReceive(request);
//
//            return Arrays.stream(response.getBody().split(LINE_SEPARATOR)).reduce("", (s, s2) -> s + s2 + '\n');
//        }, executorService);
    }

    @Override
    public CompletableFuture<String> addAgency(String name, String address) {
        return sendAndReceiveBody(ADD_AGENCY, name + AppService.ARGUMENTS_SEPARATOR + address);
    }

    private CompletableFuture<String> sendAndReceiveBody(String header, String body) {
//        return CompletableFuture.supplyAsync(() -> tcpClient.sendAndReceiveBody(header, body), executorService);
        return CompletableFuture.supplyAsync(() -> Arrays.stream(tcpClient.sendAndReceiveBody(header, body).split(LINE_SEPARATOR)).reduce("", (s, s2) -> s + s2 + '\n'), executorService);
    }

    private CompletableFuture<String> sendAndReceiveBody(String header) {
        return sendAndReceiveBody(header, "");
//        return CompletableFuture.supplyAsync(() -> tcpClient.sendAndReceiveBody(header, ""), executorService);
    }
}
