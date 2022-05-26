package ro.lab11.core.service;


import ro.lab11.core.domain.Estate;

import java.util.List;
import java.util.Set;

public interface EstateService extends Service {
    List<Estate> getEstates();

    void addEstate(String address, double surface, Long customerId);

    void updateEstate(Long id, String address, double surface, Long customerId);

    void removeEstate(Long id);

    public Set<Estate> findByAddressContainingOrderBySurface(String addressSubstring);
}
