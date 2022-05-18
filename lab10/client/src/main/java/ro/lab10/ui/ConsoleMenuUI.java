package ro.lab10.ui;

import ro.lab10.service.AppService;
import ro.lab10.tools.IO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;


public class ConsoleMenuUI {
    private final Map<Integer, String> optionsDescriptions = new HashMap<>();
    private final Map<Integer, Runnable> optionsHandlers = new HashMap<>();
    private int optionsCount = 0;
    private final AppService service;
    private final ExecutorService executorService;

    public ConsoleMenuUI(AppService clientService, ExecutorService executorService) {
        this.service = clientService;
        this.executorService = executorService;
        prepareOptions();
    }

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
        executorService.shutdown();
    }

    // L2

    /**
     * @implNote YOU HAVE TO CALL printOptions() in every option handler
     */
    private void prepareOptions() {
//        BiFunction<String, Runnable, Map.Entry<String, Runnable>> createEntry = AbstractMap.SimpleEntry::new;
        Runnable notImplemented = () -> System.out.println("Not implemented");
        BiConsumer<String, Runnable> addOptionAndAppendOptionsPrinting = (s, runnable) -> addOption(s, () -> {
            runnable.run();
            printOptions();
        });

        addOption("Exit\n", null);
        addOption("Show agencies", this::showAgencies);
        addOption("Add agency", this::addAgency);
        addOption("Update agency", this::updateAgency);
        addOption("Remove agency\n", this::removeAgency);

        addOption("Show customers", this::showCustomers);
        addOption("Add customer", this::addCustomer);
        addOption("Update customer", this::updateCustomer);
        addOption("Remove customer\n", this::removeCustomer);

        addOption("Show estates", this::showEstates);
        addOption("Add estate", this::addEstate);
        addOption("Update estate", this::updateEstate);
        addOption("Remove estate\n", this::removeEstate);

        addOption("Show offers", this::showOffers);
        addOption("Add offer", this::addOffer);
        addOption("Update offer", this::updateOffer);
        addOption("Remove offer\n", this::removeOffer);

        // TODO: show by <entity> ex: showPropertiesByUser
        // TODO: top most <adj> <entity> ex: showTopMostInterestingProperties

//        addOption("NOT IMPLEMENTED", notImplemented);
        addOptionAndAppendOptionsPrinting.accept("NOT IMPLEMENTED", notImplemented);
    }

    private void printOptions() {
        IO.writeLine(optionsDescriptions.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(entry -> String.format("\t%d. %s", entry.getKey(), entry.getValue()))
                .reduce("", (s, s2) -> s + '\n' + s2));
        IO.write("Your option: ");
    }

    // L3

    private void addOption(String description, Runnable handler) {
        optionsDescriptions.put(optionsCount, description);
        optionsHandlers.put(optionsCount++, handler);
    }

    private void showAgencies() {
        performAndHandleServiceCall(service.getAgencies());
    }

    private void addAgency() {
        var name = IO.readString("Agency name: ");
        var address = IO.readString("Agency address: ");

        performAndHandleServiceCall(service.addAgency(name, address));
    }

    private void updateAgency() {
        var id = IO.readLong("Agency id: ");
        var name = IO.readString("Agency name: ");
        var address = IO.readString("Agency address: ");

        performAndHandleServiceCall(service.updateAgency(id, name, address));
    }

    private void removeAgency() {
        var id = IO.readLong("Agency id: ");
        performAndHandleServiceCall(service.removeAgency(id));
    }

    private void showCustomers() {
        performAndHandleServiceCall(service.getCustomers());
    }

    private void addCustomer() {
        var name = IO.readString("Customer name: ");
        var email = IO.readString("Customer email: ");

        performAndHandleServiceCall(service.addCustomer(name, email));
    }

    private void updateCustomer() {
        var id = IO.readLong("Customer id: ");
        var name = IO.readString("Customer name: ");
        var email = IO.readString("Customer email: ");

        performAndHandleServiceCall(service.updateCustomer(id, name, email));
    }

    private void removeCustomer() {
        var id = IO.readLong("Customer id: ");
        performAndHandleServiceCall(service.removeCustomer(id));
    }

    private void showEstates() {
        performAndHandleServiceCall(service.getEstates());
    }

    private void addEstate() {
        var name = IO.readString("Estate address: ");
        double surface = IO.readDouble("Estate surface: ");

        performAndHandleServiceCall(service.addEstate(name, surface));
    }

    private void updateEstate() {
        var id = IO.readLong("Estate id: ");
        var name = IO.readString("Estate address: ");
        var surface = IO.readDouble("Estate surface: ");

        performAndHandleServiceCall(service.updateEstate(id, name, surface));
    }

    private void removeEstate() {
        var id = IO.readLong("Estate id: ");
        performAndHandleServiceCall(service.removeEstate(id));
    }

    private void showOffers() {
        performAndHandleServiceCall(service.getOffers());
    }

    private void addOffer() {
        var agencyId = IO.readLong("Offer agency id: ");
        var estateId = IO.readLong("Offer estate id: ");
        double price = IO.readDouble("Offer price: ");

        performAndHandleServiceCall(service.addOffer(agencyId, estateId, price));
    }

    private void updateOffer() {
        var agencyId = IO.readLong("Offer agency id: ");
        var estateId = IO.readLong("Offer estate id: ");
        double price = IO.readDouble("Offer price: ");

        performAndHandleServiceCall(service.updateOffer(agencyId, estateId, price));
    }

    private void removeOffer() {
        var agencyId = IO.readLong("Offer agency id: ");
        var estateId = IO.readLong("Offer estate id: ");
        performAndHandleServiceCall(service.removeOffer(agencyId, estateId));
    }

    // L4

    public void performAndHandleServiceCall(CompletableFuture<String> result) {
        result.whenComplete((s, throwable) -> {
           if (throwable == null) {
               IO.writeLine(s);
           }
           else {
               IO.writeLine(throwable.getMessage());
           }
           printOptions();
        });
    }
}
