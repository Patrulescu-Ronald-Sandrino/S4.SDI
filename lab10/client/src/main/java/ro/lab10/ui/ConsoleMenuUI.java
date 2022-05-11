package ro.lab10.ui;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.lab10.service.AppService;
import ro.lab10.service.ClientService;

public class ConsoleMenuUI {
    private final AppService clientService;

    public ConsoleMenuUI(ClientService clientService) {
        this.clientService = clientService;
    }

    public void run() {
    }
}
