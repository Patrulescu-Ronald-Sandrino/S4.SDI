package ro.lab11.core.service;


import ro.lab11.core.domain.Customer;

import java.util.List;

public interface CustomerService extends Service {
    List<Customer> getCustomers();

    void addCustomer(String name, String address);

    void updateCustomer(Long id, String name, String address);

    void removeCustomer(Long id);
}
