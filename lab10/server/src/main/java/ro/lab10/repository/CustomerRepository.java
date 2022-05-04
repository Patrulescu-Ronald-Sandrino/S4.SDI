package ro.lab10.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.lab10.domain.Customer;
import ro.lab10.domain.validators.CustomerValidator;
import ro.lab10.domain.validators.ValidatorException;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class CustomerRepository implements Repository<Long, Customer> {
    private final JdbcOperations jdbcOperations;
    private final CustomerValidator validator;

    @Autowired
    public CustomerRepository(JdbcOperations jdbcOperations, CustomerValidator validator) {
        this.jdbcOperations = jdbcOperations;
        this.validator = validator;

        String sqlQueryCreateTableCustomer = """
                CREATE TABLE IF NOT EXISTS Customers (
                    id SERIAL PRIMARY KEY
                    """;
    }

    @Override
    public Optional<Customer> findOne(Long aLong) {
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
    public Optional<Customer> delete(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Customer> update(Customer entity) throws ValidatorException {
        return Optional.empty();
    }
}
