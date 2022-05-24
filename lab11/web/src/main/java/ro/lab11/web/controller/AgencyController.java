package ro.lab11.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.lab11.core.tools.Logger;

@RestController
@RequestMapping(value = "/agencies")
public class AgencyController {
    public static final Logger logger = new Logger(AgencyController.class);
    // autowire the IAgencyService and the AgencyConvertor

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String test() {
        logger.info("start");
        return "Agency controller is working!";
    }
}
