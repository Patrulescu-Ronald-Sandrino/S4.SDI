package ro.lab10;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.lab10.service.AppService;
import ro.lab10.service.ServerService;
import ro.lab10.tcp.Message;
import ro.lab10.tcp.TcpServer;

import java.util.concurrent.ExecutionException;

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

    private static AnnotationConfigApplicationContext getContext() {
        return new AnnotationConfigApplicationContext("ro.lab10.config");
    }

    private static void addServiceHandlers(TcpServer tcpServer, ServerService serverService) {
        tcpServer.addHandler(AppService.GET_AGENCIES, request -> {
            try {
                return Message.ok(serverService.getAgencies().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return Message.error(e.getMessage());
            }
        });
        
        tcpServer.addHandler(AppService.ADD_AGENCY, request -> {
            try {
                String[] arguments = request.getBody().split(AppService.ARGUMENTS_SEPARATOR);
                return Message.ok(serverService.addAgency(arguments[0], arguments[1]).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return Message.error(e.getMessage());
            }
        });

        // TODO: add handlers
    }
}
