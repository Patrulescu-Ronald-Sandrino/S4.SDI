package ro.lab10;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.lab10.domain.convertors.AgencyConvertor;
import ro.lab10.service.AppService;
import ro.lab10.service.ServerService;
import ro.lab10.tcp.Message;
import ro.lab10.tcp.TcpServer;

import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
                var convertor= new AgencyConvertor();

                return new Message(Message.OK,
                        StreamSupport.stream(serverService.getAgencies().get().spliterator(), false)
                                .map(convertor::toMessage)
                                .collect(Collectors.joining(Message.LINE_SEPARATOR)));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });
        // TODO: add handlers
    }
}
