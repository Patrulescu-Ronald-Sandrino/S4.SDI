package ro.lab11.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.lab11.core.domain.Customer;
import ro.lab11.core.domain.Estate;
import ro.lab11.core.domain.exceptions.AppException;
import ro.lab11.core.repository.CustomerRepository;
import ro.lab11.core.repository.EstateRepository;
import ro.lab11.core.tools.Logger;

import java.util.List;
import java.util.Set;


@Service
public class EstateServiceImplementation implements EstateService {
    public static final Logger logger = new Logger(EstateServiceImplementation.class);

    @Autowired
    private EstateRepository estateRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Estate> getEstates() {
        logger.traceStartArgs();
        var all = estateRepository.findAll();
        logger.traceEndResult(all.toString());
        return all;
    }

    @Override
    public void addEstate(String address, double surface, Long customerId) {
        logger.traceStartArgs(address, surface);
        logger.traceEndResult(entityWithIdAdded(estateRepository.save(new Estate(address, surface, customerRepository.getOne(customerId), null)).getId()));
    }

    @Override
    public void updateEstate(Long id, String address, double surface, Long customerId) {
        logger.traceStartArgs(id, address, surface);

        estateRepository.findById(id).ifPresentOrElse(estate -> {
            var customer = new Customer();
            customer.setId(customerId);
            estate.setCustomer(customer);
            estate.setAddress(address);
            estate.setSurface(surface);
            estateRepository.save(estate);
            logger.traceEndResult(entityWithIdUpdated(id));
        }, () -> {
            logger.traceEndResult(entityWithIdNotFound(id));
            throw new AppException(entityWithIdNotFound(id));
        });
    }

    @Override
    public void removeEstate(Long id) {
        logger.traceStartArgs(id);

        estateRepository.findById(id).ifPresentOrElse(estate -> {
            estateRepository.deleteById(id);
            logger.traceEndResult(entityWithIdRemoved(id));
        }, () -> {
            logger.traceEndResult(entityWithIdNotFound(id));
            throw new AppException(entityWithIdNotFound(id));
        });
    }

    public Set<Estate> findByAddressContainingOrderBySurface(String addressSubstring) {
        logger.traceStartArgs(addressSubstring);
        var all = estateRepository.findByAddressContainingOrderBySurface(addressSubstring);
        logger.traceEndResult(all.toString());
        return all;
    }
}
