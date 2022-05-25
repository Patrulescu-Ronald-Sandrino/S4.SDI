package ro.lab11.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.lab11.core.domain.Customer;
import ro.lab11.core.domain.exceptions.AppException;
import ro.lab11.core.repository.CustomerRepository;
import ro.lab11.core.tools.Logger;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {
    public static final Logger logger = new Logger(CustomerServiceImplementation.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        logger.traceStartArgs();
        var all = customerRepository.findAll();
        logger.traceEndResult(all.toString());
        return all;
    }

    @Override
    public void addCustomer(String name, String email) {
        logger.traceStartArgs(name, email);
        logger.traceEndResult(entityWithIdAdded(customerRepository.save(new Customer(name, email)).getId()));
    }

    @Override
    public void updateCustomer(Long id, String name, String email) {
        logger.traceStartArgs(id, name, email);

        customerRepository.findById(id).ifPresentOrElse(customer -> {
            customer.setName(name);
            customer.setEmail(email);
            customerRepository.save(customer);
            logger.traceEndResult(entityWithIdUpdated(id));
        }, () -> {
            logger.traceEndResult(entityWithIdNotFound(id));
            throw new AppException(entityWithIdNotFound(id));
        });
    }

    @Override
    public void removeCustomer(Long id) {
        logger.traceStartArgs(id);

        customerRepository.findById(id).ifPresentOrElse(customer -> {
            customerRepository.deleteById(id);
            logger.traceEndResult(entityWithIdRemoved(id));
        }, () -> {
            logger.traceEndResult(entityWithIdNotFound(id));
            throw new AppException(entityWithIdNotFound(id));
        });
    }
}
