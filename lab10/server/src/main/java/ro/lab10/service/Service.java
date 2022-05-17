package ro.lab10.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.lab10.domain.Agency;
import ro.lab10.exceptions.AppException;
import ro.lab10.repository.AgencyRepository;
import ro.lab10.repository.CustomerRepository;
import ro.lab10.repository.EstateRepository;
import ro.lab10.repository.OfferRepository;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;


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

    public List<Agency> getAgencies() {
        return agencyRepository.findAll();
    }

    public void addAgency(String name, String address) {
        agencyRepository.save(new Agency(name, address));
    }

    public Agency findAgency(Long id) {
        return agencyRepository.findOne(id).orElseThrow(getEntityWithIdNotFoundExceptionSupplier(id));
    }

    public void updateAgency(Long id, String name, String address) {
        agencyRepository.update(new Agency(id, name, address))
                .ifPresent((agency) -> {throw getEntityWithIdNotFoundExceptionSupplier(id).get();});
    }

    public void removeAgency(Long id) {
        agencyRepository.delete(id).orElseThrow(getEntityWithIdNotFoundExceptionSupplier(id));
    }

    private static Supplier<AppException> getEntityWithIdNotFoundExceptionSupplier(Long id) {
        return () -> new AppException("entity with id %d not found".formatted(id));
    }

//    public vo
}
