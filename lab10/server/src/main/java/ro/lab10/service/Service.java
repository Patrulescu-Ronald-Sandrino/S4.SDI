package ro.lab10.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.lab10.repository.AgencyRepository;
import ro.lab10.repository.CustomerRepository;
import ro.lab10.repository.EstateRepository;
import ro.lab10.repository.OfferRepository;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EstateRepository estateRepository;
    @Autowired
    private OfferRepository offerRepository;
}
