package ro.lab10.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.lab10.domain.Estate;
import ro.lab10.domain.validators.EstateValidator;
import ro.lab10.domain.validators.ValidatorException;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class EstateRepository implements Repository<Long, Estate> {
    private final JdbcOperations jdbcOperations;
    private final EstateValidator validator;

    @Autowired
    public EstateRepository(JdbcOperations jdbcOperations, EstateValidator validator) {
        this.jdbcOperations = jdbcOperations;
        this.validator = validator;
    }

    @Override
    public Optional<Estate> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Estate> findAll() {
        return null;
    }

    @Override
    public Optional<Estate> save(Estate entity) throws ValidatorException {
        return Optional.empty();
    }

    @Override
    public Optional<Estate> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Estate> update(Estate entity) throws ValidatorException {
        return Optional.empty();
    }
}
