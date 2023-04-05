package ro.lab11.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.lab11.core.service.CustomerService;
import ro.lab11.core.tools.Logger;
import ro.lab11.web.converter.CustomerConvertor;
import ro.lab11.web.dto.CustomersDTO;
import ro.lab11.web.dto.CustomerDTO;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    public static final Logger logger = new Logger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerConvertor convertor;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String test() {
        logger.info("start");
        return "Customer controller is working!";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    CustomersDTO getCustomers() {
        return new CustomersDTO(convertor.convertModelsToDTOs(customerService.getCustomers()));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void addCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addCustomer(customerDTO.getName(), customerDTO.getEmail());
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    void updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(id, customerDTO.getName(), customerDTO.getEmail());
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
    ResponseEntity<?> removeCustomer(@PathVariable Long id) {
        customerService.removeCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
