package ro.lab10.service;

import ro.lab10.domain.Agency;
import ro.lab10.domain.convertors.AgencyConvertor;
import ro.lab10.tcp.Message;
import ro.lab10.tcp.TcpClient;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;


public class ClientService implements AppService {
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public ClientService(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Iterable<Agency>> getAgencies() {
        return CompletableFuture.supplyAsync(() -> {
            var request = new Message(GET_AGENCIES);
            var response = tcpClient.sendAndReceive(request);
            var convertor = new AgencyConvertor();

            return Arrays.stream(response.getBody().split(Message.LINE_SEPARATOR))
                    .filter(s -> !s.isEmpty())
                    .map(convertor::fromMessage)
                    .collect(Collectors.toSet());
        }, executorService);
    }

    @Override
    public CompletableFuture<String> addAgency(String name, String address) {
        return null;
    }
}
