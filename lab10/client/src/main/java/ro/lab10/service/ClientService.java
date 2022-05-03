package ro.lab10.service;

import ro.lab10.tcp.TcpClient;

import java.util.concurrent.ExecutorService;

public class ClientService implements AppService {
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public ClientService(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    public void closeExecutor() {
        executorService.shutdown();
    }
}
