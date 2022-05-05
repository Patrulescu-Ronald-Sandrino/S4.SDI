package ro.lab10.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.lab10.domain.Offer;
import ro.lab10.domain.Pair;
import ro.lab10.domain.validators.OfferValidator;
import ro.lab10.domain.validators.ValidatorException;
import ro.lab10.exceptions.AppException;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class OfferRepository extends JDBCRepository<Pair<Long, Long>, Offer> {
    private final OfferValidator validator;

    @Autowired
    public OfferRepository(JdbcOperations jdbcOperations, OfferValidator validator) {
        super(jdbcOperations);
        this.validator = validator;
    }

    @Override
    void createTableIfNotExists() throws AppException {
        executeDDLStatement("""
                CREATE TABLE IF NOT EXISTS Agencies (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(50),
                    address VARCHAR(50)
                )""");
    }

    @Override
    public Optional<Offer> findOne(Pair<Long, Long> id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Offer> findAll() {
        return null;
    }

    @Override
    public Optional<Offer> save(Offer entity) throws ValidatorException {
        return Optional.empty();
    }

    @Override
    public Optional<Offer> delete(Pair<Long, Long> id) {
        return Optional.empty();
    }

    @Override
    public Optional<Offer> update(Offer entity) throws ValidatorException {
        return Optional.empty();
    }
}
