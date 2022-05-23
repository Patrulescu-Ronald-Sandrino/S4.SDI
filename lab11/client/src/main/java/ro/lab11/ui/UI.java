package ro.lab11.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.lab11.controller.AsyncAgencyController;
import ro.lab11.tools.IO;
//import ro.lab11.tools.IO;

@Component
public class UI {
    @Autowired
    AsyncAgencyController agencyController;
    public void run() {
        IO.writeLine("UI is running");
    }
}