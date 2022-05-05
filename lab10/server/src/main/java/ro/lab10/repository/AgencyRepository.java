package ro.lab10.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.lab10.domain.Agency;
import ro.lab10.domain.validators.AgencyValidator;
import ro.lab10.domain.validators.ValidatorException;
import ro.lab10.exceptions.AppException;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class AgencyRepository extends JDBCRepository<Long, Agency> {
    private final AgencyValidator validator;

    @Autowired
    public AgencyRepository(JdbcOperations jdbcOperations, AgencyValidator validator) {
        super(jdbcOperations);
        this.validator = validator;
    }

     void createTableIfNotExists() throws AppException {
        executeDDLStatement("""
                CREATE TABLE IF NOT EXISTS Agencies (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(50),
                    address VARCHAR(50),
                    UNIQUE(name, address)
                )""");
    }

    @Override
    public Optional<Agency> findOne(Long id) {
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
    public Optional<Agency> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Agency> update(Agency entity) throws ValidatorException {
        return Optional.empty();
    }
}
