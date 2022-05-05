package ro.lab10.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.lab10.domain.Customer;
import ro.lab10.domain.validators.CustomerValidator;
import ro.lab10.domain.validators.ValidatorException;
import ro.lab10.exceptions.AppException;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class CustomerRepository extends JDBCRepository<Long, Customer> {
    private final CustomerValidator validator;

    @Autowired
    public CustomerRepository(JdbcOperations jdbcOperations, CustomerValidator validator) {
        super(jdbcOperations);
        this.validator = validator;
    }

    void createTableIfNotExists() throws AppException {
        executeDDLStatement("""
                CREATE TABLE IF NOT EXISTS Customers (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(50) UNIQUE,
                    email VARCHAR(50) UNIQUE
                )""");
    }

    @Override
    public Optional<Customer> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Customer> findAll() {
        return null;
    }

    @Override
    public Optional<Customer> save(Customer entity) throws ValidatorException {
        return Optional.empty();
    }

    @Override
    public Optional<Customer> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Customer> update(Customer entity) throws ValidatorException {
        return Optional.empty();
    }
}
