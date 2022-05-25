package ro.lab11.core.service;


import ro.lab11.core.domain.Estate;

import java.util.List;

public interface EstateService extends Service {
    List<Estate> getEstates();

    void addEstate(String address, double surface);

    void updateEstate(Long id, String address, double surface);

    void removeEstate(Long id);
}
