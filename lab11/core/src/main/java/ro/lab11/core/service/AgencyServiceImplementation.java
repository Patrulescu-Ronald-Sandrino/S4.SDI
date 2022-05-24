package ro.lab11.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.lab11.core.domain.Agency;
import ro.lab11.core.repository.AgencyRepository;
import ro.lab11.core.tools.Logger;

import java.util.List;

@Service
public class AgencyServiceImplementation implements AgencyService{
//    public static final Logger logger = LoggerFactory.getLogger(AgencyServiceImplementation.class);
    public static final Logger logger = new Logger(AgencyServiceImplementation.class);

    @Autowired
    private AgencyRepository agencyRepository;

    @Override
    public List<Agency> getAgencies() {
        logger.info("start");
        var all = agencyRepository.findAll();
        logger.info(" result: %s".formatted(all));
        return all;
    }

    @Override
    public void addAgency(String name, String address) {

    }

    @Override
    public void updateAgency(Long id, String name, String address) {

    }

    @Override
    public void removeAgency(Long id) {

    }
}
