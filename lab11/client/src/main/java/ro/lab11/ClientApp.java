package ro.lab11;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.lab11.ui.UI;

public class ClientApp {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext("ro.lab11.config");
        var ui = context.getBean(UI.class);
        ui.run();
    }
}
