package ro.lab10.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.lab10.domain.Estate;
import ro.lab10.domain.validators.EstateValidator;
import ro.lab10.domain.validators.ValidatorException;
import ro.lab10.exceptions.AppException;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class EstateRepository extends JDBCRepository<Long, Estate> {
    private final EstateValidator validator;

    @Autowired
    public EstateRepository(JdbcOperations jdbcOperations, EstateValidator validator) {
        super(jdbcOperations);
        this.validator = validator;
    }

    void createTableIfNotExists() throws AppException {
        executeDDLStatement("""
                CREATE TABLE IF NOT EXISTS Estates (
                    id SERIAL PRIMARY KEY,
                    address VARCHAR(50) UNIQUE,
                    surface DOUBLE
                )""");
    }

    @Override
    public Optional<Estate> findOne(Long id) {
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
    public Optional<Estate> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Estate> update(Estate entity) throws ValidatorException {
        return Optional.empty();
    }
}
