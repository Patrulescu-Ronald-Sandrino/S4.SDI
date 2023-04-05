package ro.lab11.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.lab11.core.domain.Customer;
import ro.lab11.core.domain.Offer;
import ro.lab11.core.domain.OfferPK;
import ro.lab11.core.domain.exceptions.AppException;
import ro.lab11.core.repository.AgencyRepository;
import ro.lab11.core.repository.EstateRepository;
import ro.lab11.core.repository.OfferRepository;
import ro.lab11.core.tools.Logger;

import java.util.List;

@Service
public class OfferServiceImplementation implements OfferService {
    public static final Logger logger = new Logger(OfferServiceImplementation.class);

    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private EstateRepository estateRepository;
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<Offer> getOffers() {
        logger.traceStartArgs();
        var all = offerRepository.findAll();
        logger.traceEndResult(all.toString());
        return all;
    }

    @Override
    public void addOffer(Long agencyId, Long estateId, double price) {
        logger.traceStartArgs(agencyId, estateId, price);

        agencyRepository.findById(agencyId).ifPresentOrElse((agency) -> {}, () -> {
            logger.traceEndResult(entityWithIdNotFound(agencyId));
            throw new AppException(entityWithIdNotFound(agencyId));
        });
        
        estateRepository.findById(estateId).ifPresentOrElse((estate) -> {}, () -> {
            logger.traceEndResult(entityWithIdNotFound(estateId)); // TODO: fix adding a good value throws error
            throw new AppException(entityWithIdNotFound(estateId));
        });
        
        var id = new OfferPK(agencyId, estateId);

        if (offerRepository.existsById(id)) {
            logger.traceEndResult(entityWithIdExists(id));
            throw new AppException(entityWithIdExists(id));
        }

        logger.traceEndResult(entityWithIdAdded(offerRepository.save(new Offer(id, price)).getId()));
    }

    @Override
    public void updateOffer(Long agencyId, Long estateId, double price) {
        var id = new OfferPK(agencyId, estateId);

        offerRepository.findById(id).ifPresentOrElse((offer) -> {
            offer.setPrice(price);
            offerRepository.save(offer);
            logger.traceEndResult(entityWithIdUpdated(id));
        }, () -> {
            logger.traceEndResult(entityWithIdNotFound(id));
            throw new AppException(entityWithIdNotFound(id));
        });
    }

    @Override
    public void removeOffer(Long agencyId, Long estateId) {
        var id = new OfferPK(agencyId, estateId);

        offerRepository.findById(id).ifPresentOrElse(offer -> {
            offerRepository.deleteById(id);
            logger.traceEndResult(entityWithIdRemoved(id));
        }, () -> {
            logger.traceEndResult(entityWithIdNotFound(id));
            throw new AppException(entityWithIdNotFound(id));
        });
    }
}
