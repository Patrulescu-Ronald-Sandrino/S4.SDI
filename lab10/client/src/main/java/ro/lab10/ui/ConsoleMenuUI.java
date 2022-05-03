package ro.lab10.ui;

import ro.lab10.service.ClientService;

public class ConsoleMenuUI {
    private final ClientService clientService;

    public ConsoleMenuUI(ClientService clientService) {
        this.clientService = clientService;
    }

    public void run() {
        clientService.closeExecutor(); // TODO: call when quiting
    }
}
