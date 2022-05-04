package ro.lab10;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.lab10.service.ServerService;
import ro.lab10.tcp.TcpServer;

public class ServerApp {
    public static void main(String[] args) {
//        try (var context = new AnnotationConfigApplicationContext("ro.lab10.config")) {
        try (var context = getContext()) {
            var tcpServer = context.getBean(TcpServer.class);
            var serverService = context.getBean(ServerService.class);

            serverService.addHandlers(tcpServer);
            tcpServer.startServer();
        }
    }

    private static AnnotationConfigApplicationContext getContext() {
        return new AnnotationConfigApplicationContext("ro.lab10.config");
    }
}
