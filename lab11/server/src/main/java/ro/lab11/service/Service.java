package ro.lab11.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.lab11.domain.*;
import ro.lab11.exceptions.AppException;
import ro.lab11.repository.AgencyRepository;
import ro.lab11.repository.CustomerRepository;
import ro.lab11.repository.EstateRepository;
import ro.lab11.repository.OfferRepository;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;


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

    public void updateAgency(Long id, String name, String address) {
        agencyRepository.update(new Agency(id, name, address))
                .ifPresent(agency -> {
                    throw getEntityWithIdNotFoundExceptionSupplier(id).get();
                });
    }

    public void removeAgency(Long id) {
        agencyRepository.delete(id).orElseThrow(getEntityWithIdNotFoundExceptionSupplier(id));
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(String name, String email) {
        customerRepository.save(new Customer(name, email));
    }

    public void updateCustomer(Long id, String name, String email) {
        customerRepository.update(new Customer(id, name, email))
                .ifPresent(customer -> {
                    throw getEntityWithIdNotFoundExceptionSupplier(id).get();
                });
    }

    public void removeCustomer(Long id) {
        customerRepository.delete(id).orElseThrow(getEntityWithIdNotFoundExceptionSupplier(id));
    }

    public List<Estate> getEstates() {
        return estateRepository.findAll();
    }

    public void addEstate(String address, double surface) {
        estateRepository.save(new Estate(address, surface));
    }

    public void updateEstate(Long id, String address, double surface) {
        estateRepository.update(new Estate(id, address, surface))
                .ifPresent(estate -> {
                    throw getEntityWithIdNotFoundExceptionSupplier(id).get();
                });
    }

    public void removeEstate(Long id) {
        estateRepository.delete(id).orElseThrow(getEntityWithIdNotFoundExceptionSupplier(id));
    }

    public List<Offer> getOffers() {
        return offerRepository.findAll();
    }

    public void addOffer(Long agencyId, Long estateId, double price) {
        offerRepository.save(new Offer(agencyId, estateId, price))
                .ifPresent(offer -> {
                    throw getExceptionSupplier("entity with id (%d, %d) already exists".formatted(agencyId, estateId)).get();
                });
    }

    public void updateOffer(Long agencyId, Long estateId, double price) {
        offerRepository.update(new Offer(agencyId, estateId, price))
                .ifPresent(offer -> {
                    throw getExceptionSupplier("entity with id (%d, %d) not found".formatted(agencyId, estateId)).get();
                });
    }

    public void removeOffer(Long agencyId, Long estateId) {
        var id = new Pair<>(agencyId, estateId);
        offerRepository.delete(id).orElseThrow(getExceptionSupplier("entity with id (%d, %d) not found".formatted(agencyId, estateId)));
    }

    public List<Offer> getEstateOffers(Long id) {
        var offers = getOffers();

        estateRepository.findOne(id).orElseThrow(getEntityWithIdNotFoundExceptionSupplier(id));
        return offers.stream().filter(offer -> Objects.equals(offer.getEstateId(), id)).collect(Collectors.toList());
    }

    public List<Estate> getMostInterestingEstates() {
        Map<Long, Estate> estates = new HashMap<>();
        Map<Estate, Integer> estatesInterest = new HashMap<>();
        estateRepository.findAll().forEach(estate -> {
            estates.put(estate.getId(), estate);
            estatesInterest.put(estate, 0);
        });

        offerRepository.findAll().forEach(offer -> {
            var estate = estates.get(offer.getEstateId());
            var currentInterest = estatesInterest.getOrDefault(estate, 0);
            estatesInterest.put(estate, currentInterest + 1);
        });

        return estatesInterest.entrySet().stream()
                .sorted(Comparator.comparingInt(value -> - value.getValue()))
                .limit(5).map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // L2

    private static Supplier<AppException> getExceptionSupplier(String message) {
        return () -> new AppException(message);
    }

    // L3

    private static Supplier<AppException> getEntityWithIdNotFoundExceptionSupplier(Long id) {
//        return () -> new AppException("entity with id %d not found".formatted(id));
        return getExceptionSupplier("entity with id %d not found".formatted(id));
    }
}
