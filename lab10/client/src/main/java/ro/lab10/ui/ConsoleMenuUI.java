package ro.lab10.ui;

import ro.lab10.service.AppService;
import ro.lab10.tools.IO;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

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
                    IO.writeLine("Bad input value for option!");
//                    printOptions();
                }
                else {
                    optionHandler.run();
                    continue;
                }
            }
            catch (NumberFormatException e) {
                IO.writeLine("Bad input type for option!");
            }
            catch (Exception e) {
                IO.writeLine(e.getMessage());
            }
            printOptions();
        }
        executorService.shutdown();
    }

    // L2

    private void prepareOptions() {
//        BiFunction<String, Runnable, Map.Entry<String, Runnable>> createEntry = AbstractMap.SimpleEntry::new;
        Runnable notImplemented = () -> System.out.println("Not implemented");

        addOption("Exit", null);
        addOption("Show agencies", this::showAgencies);
        addOption("Add agency", this::addAgency);
        addOption("Update agency", this::updateAgency);
        addOption("Remove agency", this::removeAgency);

        // TODO: options for customer

        // TODO: options for estate

        // TODO: options for offer

        // TODO: show by <entity> ex: showPropertiesByUser
        // TODO: top most <adj> <entity> ex: showTopMostInterestingProperties

        addOption("NOT IMPLEMENTED", notImplemented);
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
//        result.join(); // fixes: the menu being shown before a task is completed due to it being asynchronous
    }
}
