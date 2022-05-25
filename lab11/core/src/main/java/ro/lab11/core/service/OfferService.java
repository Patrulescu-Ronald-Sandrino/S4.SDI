package ro.lab11.core.service;


import ro.lab11.core.domain.Offer;

import java.util.List;

public interface OfferService extends Service {
    List<Offer> getOffers();

    void addOffer(Long agencyId, Long estateId, double price);

    void updateOffer(Long agencyId, Long estateId, double price);

    void removeOffer(Long agencyId, Long estateId);
}
