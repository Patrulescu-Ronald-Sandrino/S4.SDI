package ro.lab11.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.lab11.client.controller.AsyncAgencyController;
import ro.lab11.core.tools.IO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
//import ro.lab11.tools.IO;

import javax.annotation.PostConstruct;

@Component
public class UI {
    private final Map<Integer, String> optionsDescriptions = new HashMap<>();
    private final Map<Integer, Runnable> optionsHandlers = new HashMap<>();
    private int optionsCount = 0;

    @Autowired
    private AsyncAgencyController agencyController;

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

    @PostConstruct
    private void prepareOptions() {
        BiConsumer<String, Runnable> addOptionAndAppendOptionsPrinting = (s, runnable) -> addOption(s, () -> {
            runnable.run();
            printOptions();
        });
        addOption("Exit", null);
        addOptionAndAppendOptionsPrinting.accept("test", this::test);
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

    private void test() {
        agencyController.test().whenComplete((s, throwable) -> {
           IO.writeLine(throwable == null ? s : throwable.getMessage());
        });
    }
}