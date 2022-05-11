package ro.lab10;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.lab10.service.ServerService;
import ro.lab10.tcp.Message;
import ro.lab10.tcp.TcpServer;

public class ServerApp {
    public static void main(String[] args) {
//        try (var context = new AnnotationConfigApplicationContext("ro.lab10.config")) {
        try (var context = getContext()) {
            var tcpServer = context.getBean(TcpServer.class);
            var serverService = context.getBean(ServerService.class);

            addServiceHandlers(tcpServer, serverService);
            tcpServer.startServer();
        }
    }

    private static void addServiceHandlers(TcpServer tcpServer, ServerService serverService) {
        tcpServer.addHandler("hello", message -> new Message(Message.OK, "hello"));
    }

    private static AnnotationConfigApplicationContext getContext() {
        return new AnnotationConfigApplicationContext("ro.lab10.config");
    }
}
