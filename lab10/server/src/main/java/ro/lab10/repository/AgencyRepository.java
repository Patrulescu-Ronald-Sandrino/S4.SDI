package ro.lab10.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.lab10.domain.Agency;
import ro.lab10.domain.validators.AgencyValidator;
import ro.lab10.domain.validators.ValidatorException;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class AgencyRepository implements Repository<Long, Agency> {
    private final JdbcOperations jdbcOperations;
    private final AgencyValidator validator;

    @Autowired
    public AgencyRepository(JdbcOperations jdbcOperations, AgencyValidator validator) {
        this.jdbcOperations = jdbcOperations;
        this.validator = validator;

        String sqlQueryCreateTableAgency = "";
    }

    @Override
    public Optional<Agency> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Agency> findAll() {
        return null;
    }

    @Override
    public Optional<Agency> save(Agency entity) throws ValidatorException {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> update(Agency entity) throws ValidatorException {
        return Optional.empty();
    }
}
