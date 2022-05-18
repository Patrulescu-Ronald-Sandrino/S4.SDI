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
//            addArgumentsBasedServiceHandler.accept(methodName, arguments -> handler);
//        };
//        addWrappedHandler.accept(AppService.GET_AGENCIES, serverService.getAgencies()); // this does DOESN'T WORK, returns the same values that were present at the start of the server

        addArgumentsBasedServiceHandler.accept(AppService.GET_AGENCIES, arguments -> serverService.getAgencies());
        addArgumentsBasedServiceHandler.accept(AppService.ADD_AGENCY, arguments -> serverService.addAgency(arguments[0], arguments[1]));
        addArgumentsBasedServiceHandler.accept(AppService.UPDATE_AGENCY, arguments ->
                serverService.updateAgency(Long.valueOf(arguments[0]), arguments[1], arguments[2]));
        addArgumentsBasedServiceHandler.accept(AppService.REMOVE_AGENCY, arguments -> serverService.removeAgency(Long.valueOf(arguments[0])));

        addArgumentsBasedServiceHandler.accept(AppService.GET_CUSTOMERS, arguments -> serverService.getCustomers());
        addArgumentsBasedServiceHandler.accept(AppService.ADD_CUSTOMER, arguments -> serverService.addCustomer(arguments[0], arguments[1]));
        addArgumentsBasedServiceHandler.accept(AppService.UPDATE_CUSTOMER, arguments ->
                serverService.updateCustomer(Long.valueOf(arguments[0]), arguments[1], arguments[2]));
        addArgumentsBasedServiceHandler.accept(AppService.REMOVE_CUSTOMER, arguments -> serverService.removeCustomer(Long.valueOf(arguments[0])));

        addArgumentsBasedServiceHandler.accept(AppService.GET_ESTATES, arguments -> serverService.getEstates());
        addArgumentsBasedServiceHandler.accept(AppService.ADD_ESTATE, arguments -> serverService.addEstate(arguments[0], Double.parseDouble(arguments[1])));
        addArgumentsBasedServiceHandler.accept(AppService.UPDATE_ESTATE, arguments ->
                serverService.updateEstate(Long.valueOf(arguments[0]), arguments[1], Double.parseDouble(arguments[2])));
        addArgumentsBasedServiceHandler.accept(AppService.REMOVE_ESTATE, arguments -> serverService.removeEstate(Long.valueOf(arguments[0])));

        addArgumentsBasedServiceHandler.accept(AppService.GET_OFFERS, arguments -> serverService.getOffers());
        addArgumentsBasedServiceHandler.accept(AppService.ADD_OFFER, arguments -> serverService.addOffer(Long.valueOf(arguments[0]), Long.valueOf(arguments[1]), Double.parseDouble(arguments[2])));
        addArgumentsBasedServiceHandler.accept(AppService.UPDATE_OFFER, arguments ->
                serverService.updateOffer(Long.valueOf(arguments[0]), Long.valueOf(arguments[1]), Double.parseDouble(arguments[2])));
        addArgumentsBasedServiceHandler.accept(AppService.REMOVE_OFFER, arguments -> serverService.removeOffer(Long.valueOf(arguments[0]), Long.valueOf(arguments[1])));
    }

    // L2/3

    private static String[] getMessageArguments(Message request) {
        return request.getBody().split(AppService.ARGUMENTS_SEPARATOR);
    }

    private static UnaryOperator<Message> wrapHandlerInTryCatchBlock(CompletableFuture<String> handler) {
        return wrapHandlerInTryCatchBlock(arguments -> handler);
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
