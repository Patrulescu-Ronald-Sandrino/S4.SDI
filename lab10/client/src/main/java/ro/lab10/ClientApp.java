package ro.lab10;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.lab10.ui.ConsoleMenuUI;

import java.util.concurrent.ExecutorService;

public class ClientApp {
    public static void main(String[] args) {
//        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ro.lab10.config")) {
        try (var context = getContext()) {
            var console = context.getBean(ConsoleMenuUI.class);
            console.run();
            context.getBean(ExecutorService.class).shutdown();
        }
    }

    private static AnnotationConfigApplicationContext getContext() {
        return new AnnotationConfigApplicationContext("ro.lab10.config");
    }
}
