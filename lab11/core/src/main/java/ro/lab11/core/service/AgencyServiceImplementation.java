package ro.lab11.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.lab11.core.domain.Agency;
import ro.lab11.core.domain.exceptions.AppException;
import ro.lab11.core.repository.AgencyRepository;
import ro.lab11.core.tools.Logger;

import java.util.List;

@Service
public class AgencyServiceImplementation implements AgencyService{
    public static final Logger logger = new Logger(AgencyServiceImplementation.class);

    @Autowired
    private AgencyRepository agencyRepository;

    @Override
    public List<Agency> getAgencies() {
        logger.traceStartArgs();
        var all = agencyRepository.findAll();
        logger.traceEndResult(all.toString());
        return all;
    }

    @Override
    public void addAgency(String name, String address) {
        logger.traceStartArgs(name, address);

        logger.traceEndResult(entityWithIdAdded(agencyRepository.save(new Agency(name, address)).getId()));
    }

    @Override
    public void updateAgency(Long id, String name, String address) {
        logger.traceStartArgs(id, name, address);

        agencyRepository.findById(id).ifPresentOrElse(agency -> {
            agency.setName(name);
            agency.setAddress(address);
            logger.traceEndResult(entityWithIdUpdated(id));
        }, () -> {
            logger.traceEndResult(entityWithIdNotFound(id));
            throw new AppException(entityWithIdNotFound(id));
        });
    }

    @Override
    public void removeAgency(Long id) {
        logger.traceStartArgs(id);

        agencyRepository.findById(id).ifPresentOrElse(agency -> {
            agencyRepository.deleteById(id);
            logger.traceEndResult(entityWithIdRemoved(id));
        }, () -> {
            logger.traceEndResult(entityWithIdNotFound(id));
            throw new AppException(entityWithIdNotFound(id));
        });
    }
}
