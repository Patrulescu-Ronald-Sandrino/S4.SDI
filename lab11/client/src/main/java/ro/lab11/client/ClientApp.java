package ro.lab11.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.lab11.client.ui.UI;

public class ClientApp {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext("ro.lab11.client.config");
        var ui = context.getBean(UI.class);
        ui.run();
    }
}
