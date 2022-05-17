package ro.lab10.service;

import ro.lab10.domain.convertors.AgencyConvertor;
import ro.lab10.tcp.Message;
import ro.lab10.tcp.TcpClient;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;


public class ClientService implements AppService {
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public ClientService(ExecutorService executorService, TcpClient tcpClient) { // TODO: return strings
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<String> getAgencies() {
        return CompletableFuture.supplyAsync(() -> {
            var request = new Message(GET_AGENCIES);
            var response = tcpClient.sendAndReceive(request);
            var convertor = new AgencyConvertor();

            return response.getBody();
//            return Arrays.stream(response.getBody().split(Message.LINE_SEPARATOR))
//                    .filter(s -> !s.isEmpty())
//                    .map(convertor::fromMessage)
//                    .map(agency -> agency.toString() + '\n')
//                    .reduce("", (s, s2) -> s + s2);
        }, executorService);
    }

    @Override
    public CompletableFuture<String> addAgency(String name, String address) {
//        return CompletableFuture.supplyAsync(() -> tcpClient.sendAndReceiveBody(ADD_AGENCY, name + ',' + address), executorService);
        return sendAndReceive(ADD_AGENCY, name + AppService.ARGUMENTS_SEPARATOR + address); // TODO: see if you can replace ',' with a private final field like LINE_SEPARATOR
    }

    private CompletableFuture<String> sendAndReceive(String header, String body) {
        return CompletableFuture.supplyAsync(() -> tcpClient.sendAndReceiveBody(header, body), executorService);
    }
}
