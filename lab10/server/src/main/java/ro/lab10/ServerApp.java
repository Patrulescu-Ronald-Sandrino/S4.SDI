package ro.lab10;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.lab10.service.AppService;
import ro.lab10.service.ServerService;
import ro.lab10.tcp.Message;
import ro.lab10.tcp.TcpServer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;


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
        BiConsumer<String, Function<String[], CompletableFuture<String>>> addArgumentsBasedServiceHandler =
                (s, completableFutureFunction) -> tcpServer.addHandler(s, wrapHandlerInTryCatchBlock(completableFutureFunction));

//        BiConsumer<String, CompletableFuture<String>> addWrappedHandler = (methodName, handler) -> {
////            tcpServer.addHandler(methodName, wrapHandlerInTryCatchBlock(handler));
//            addArgumentsBasedServiceHandler.accept(methodName, strings -> handler);
//        };
//        addWrappedHandler.accept(AppService.GET_AGENCIES, serverService.getAgencies()); // this does DOESN'T WORK, returns the same values that were present at the start of the server

        addArgumentsBasedServiceHandler.accept(AppService.GET_AGENCIES, strings -> serverService.getAgencies());
        addArgumentsBasedServiceHandler.accept(AppService.ADD_AGENCY, strings -> serverService.addAgency(strings[0], strings[1]));
        addArgumentsBasedServiceHandler.accept(AppService.REMOVE_AGENCY, arguments -> serverService.removeAgency(Long.valueOf(arguments[0])));
        addArgumentsBasedServiceHandler.accept(AppService.UPDATE_AGENCY, arguments ->
                serverService.updateAgency(Long.valueOf(arguments[0]), arguments[1], arguments[2]));

//        addArgumentsBasedServiceHandler.accept(AppService.GET_CUSTOMERS, strings -> serverService.getCustomers());


        // TODO: add handlers
    }

    // L2/3

    private static String[] getMessageArguments(Message request) {
        return request.getBody().split(AppService.ARGUMENTS_SEPARATOR);
    }

    private static UnaryOperator<Message> wrapHandlerInTryCatchBlock(CompletableFuture<String> handler) {
        return wrapHandlerInTryCatchBlock(strings -> handler);
    }

    private static UnaryOperator<Message> wrapHandlerInTryCatchBlock(Function<String[], CompletableFuture<String>> handler) {
        return request -> {
            try {
                return Message.ok(handler.apply(getMessageArguments(request)).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return Message.error(e.getMessage());
            }
        };
    }
}
