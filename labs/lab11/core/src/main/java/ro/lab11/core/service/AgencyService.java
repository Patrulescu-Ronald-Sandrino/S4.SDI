package ro.lab11.core.service;


import ro.lab11.core.domain.Agency;

import java.util.List;

public interface AgencyService extends Service {
    List<Agency> getAgencies();

    void addAgency(String name, String address);

    void updateAgency(Long id, String name, String address);

    void removeAgency(Long id);
}
