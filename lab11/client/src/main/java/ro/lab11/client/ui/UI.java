package ro.lab11.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.lab11.client.controller.AsyncAgencyController;
import ro.lab11.client.controller.AsyncCustomerController;
import ro.lab11.client.controller.AsyncEstateController;
import ro.lab11.client.controller.AsyncOfferController;
import ro.lab11.core.tools.Collections;
import ro.lab11.core.tools.IO;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.StreamSupport;

@Component
public class UI {
    private final Map<Integer, String> optionsDescriptions = new HashMap<>();
    private final Map<Integer, Runnable> optionsHandlers = new HashMap<>();
    private int optionsCount = 0;

    @Autowired
    private AsyncAgencyController agencyController;
    @Autowired
    private AsyncCustomerController customerController;
    @Autowired
    private AsyncEstateController estateController;
    @Autowired
    private AsyncOfferController offerController;

    public void run() {
        printOptions();
        while (true) {
            try {
                int option = IO.readInteger();

                if (option == 0) {
                    break;
                }

                var optionHandler = optionsHandlers.get(option);
                if (optionHandler == null) {
                    IO.writeLine("Bad option number!");
                }
                else {
                    optionHandler.run();
                    continue;
                }
            }
            catch (NumberFormatException e) {
                IO.writeLine("Bad input!");
            }
            catch (Exception e) {
                IO.writeLine(e.getMessage());
            }
            printOptions();
        }
    }

    // https://www.geeksforgeeks.org/spring-postconstruct-and-predestroy-annotation-with-example/
    // https://www.geeksforgeeks.org/spring-init-and-destroy-methods-with-example/#:~:text=Note%3A%20Adding%20%40PostConstruct%20and%20%40PreDestroy%20Annotation%20in%20the%20Project
    @PostConstruct
    private void prepareOptions() {
        BiConsumer<String, Runnable> addOptionAndAppendOptionsPrinting = (s, runnable) -> addOption(s, () -> {
            runnable.run();
//            printOptions();
        });
        addOption("Exit", null);
        addOptionAndAppendOptionsPrinting.accept("test", this::test);
        addOptionAndAppendOptionsPrinting.accept("Show agencies", this::showAgencies);
        addOptionAndAppendOptionsPrinting.accept("Add agency", this::addAgency);
        addOptionAndAppendOptionsPrinting.accept("Update agency", this::updateAgency);
        addOptionAndAppendOptionsPrinting.accept("Remove agency\n", this::removeAgency);

        addOptionAndAppendOptionsPrinting.accept("Show customers", this::showCustomers);
        addOptionAndAppendOptionsPrinting.accept("Add customer", this::addCustomer);
        addOptionAndAppendOptionsPrinting.accept("Update customer", this::updateCustomer);
        addOptionAndAppendOptionsPrinting.accept("Remove customer\n", this::removeCustomer);

        addOptionAndAppendOptionsPrinting.accept("Show estates", this::showEstates);
        addOptionAndAppendOptionsPrinting.accept("Add estate", this::addEstate);
        addOptionAndAppendOptionsPrinting.accept("Update estate", this::updateEstate);
        addOptionAndAppendOptionsPrinting.accept("Remove estate\n", this::removeEstate);

        addOptionAndAppendOptionsPrinting.accept("Show offers", this::showOffers);
        addOptionAndAppendOptionsPrinting.accept("Add offer", this::addOffer);
        addOptionAndAppendOptionsPrinting.accept("Update offer", this::updateOffer);
        addOptionAndAppendOptionsPrinting.accept("Remove offer\n", this::removeOffer);
    }

    private void printOptions() {
        IO.writeLine(optionsDescriptions.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(entry -> String.format("\t%d. %s", entry.getKey(), entry.getValue()))
                .reduce("", (s, s2) -> s + '\n' + s2));
        IO.write("Your option: ");
    }

    private void addOption(String description, Runnable handler) {
        optionsDescriptions.put(optionsCount, description);
        optionsHandlers.put(optionsCount++, handler);
    }

    private void showAgencies() {
        handleCompletedControllerGetAllCall(agencyController.getAgencies());
    }

    private void addAgency() {
        var name = IO.readString("Agency name: ");
        var address = IO.readString("Agency address: ");

        handleCompletedControllerCall(agencyController.addAgency(name, address));
    }

    private void updateAgency() {
        var id = IO.readLong("Agency id: ");
        var name = IO.readString("Agency name: ");
        var address = IO.readString("Agency address: ");

        handleCompletedControllerCall(agencyController.update(id, name, address));
    }

    private void removeAgency() {
        var id = IO.readLong("Agency id: ");
        handleCompletedControllerCall(agencyController.remove(id));
    }

    private void showCustomers() {
//        handleCompletedControllerCall(service.getCustomers());
    }

    private void addCustomer() {
        var name = IO.readString("Customer name: ");
        var email = IO.readString("Customer email: ");

//        handleCompletedControllerCall(service.addCustomer(name, email));
    }

    private void updateCustomer() {
        var id = IO.readLong("Customer id: ");
        var name = IO.readString("Customer name: ");
        var email = IO.readString("Customer email: ");

//        handleCompletedControllerCall(service.updateCustomer(id, name, email));
    }

    private void removeCustomer() {
        var id = IO.readLong("Customer id: ");
//        handleCompletedControllerCall(service.removeCustomer(id));
    }

    private void showEstates() {
//        handleCompletedControllerCall(service.getEstates());
    }

    private void addEstate() {
        var name = IO.readString("Estate address: ");
        double surface = IO.readDouble("Estate surface: ");

//        handleCompletedControllerCall(service.addEstate(name, surface));
    }

    private void updateEstate() {
        var id = IO.readLong("Estate id: ");
        var name = IO.readString("Estate address: ");
        var surface = IO.readDouble("Estate surface: ");

//        handleCompletedControllerCall(service.updateEstate(id, name, surface));
    }

    private void removeEstate() {
        var id = IO.readLong("Estate id: ");
//        handleCompletedControllerCall(service.removeEstate(id));
    }

    private void showOffers() {
//        handleCompletedControllerCall(service.getOffers());
    }

    private void addOffer() {
        var agencyId = IO.readLong("Offer agency id: ");
        var estateId = IO.readLong("Offer estate id: ");
        double price = IO.readDouble("Offer price: ");

//        handleCompletedControllerCall(service.addOffer(agencyId, estateId, price));
    }

    private void updateOffer() {
        var agencyId = IO.readLong("Offer agency id: ");
        var estateId = IO.readLong("Offer estate id: ");
        double price = IO.readDouble("Offer price: ");

//        handleCompletedControllerCall(service.updateOffer(agencyId, estateId, price));
    }

    private void removeOffer() {
        var agencyId = IO.readLong("Offer agency id: ");
        var estateId = IO.readLong("Offer estate id: ");
//        handleCompletedControllerCall(service.removeOffer(agencyId, estateId));
    }

    private void test() { // TODO: calling multiple times .whenComplete(), as a workaround to fix the menu printing
/*        agencyController.test().whenComplete((s, throwable) ->
                IO.writeLine(throwable == null ? s : throwable.getMessage()));*/
        handleCompletedControllerCall(agencyController.test());
    }

//    public void handleCompletedControllerCall(CompletableFuture<String> result) {
//        result.whenComplete((s, throwable) -> {
//            IO.writeLine(throwable == null ? s : throwable.getMessage());
//            printOptions();
//        });
//    }

    public <T> void handleCompletedControllerGetAllCall(CompletableFuture<Iterable<T>> result) {
        handleCompletedControllerCall(result, iterable -> Collections.convertIterableToString(iterable, "\n", "Empty"));
//        result.whenComplete((iterable, throwable) -> {
////            IO.writeLine(throwable == null ? convertIterableToString.apply(iterable) : throwable.getMessage());
//            IO.writeLine(throwable == null ? Collections.convertIterableToString(iterable, "\n", "Empty") : throwable.getMessage());
//            printOptions();
//        });
    }

    public <T> void handleCompletedControllerCall(CompletableFuture<T> result) {
        handleCompletedControllerCall(result, Objects::toString);
    }

    public <T> void handleCompletedControllerCall(CompletableFuture<T> result, Function<T, String> tToStringFunction) {
        result.whenComplete((t, throwable) -> {
            IO.writeLine(throwable == null ? tToStringFunction.apply(t) : throwable.getMessage());
            if (throwable != null) {
                throwable.printStackTrace();
            }
            printOptions();
        });
    }
}