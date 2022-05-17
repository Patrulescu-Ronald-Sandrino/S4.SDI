package ro.lab10.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import ro.lab10.domain.Customer;
import ro.lab10.domain.validators.CustomerValidator;
import ro.lab10.domain.validators.ValidatorException;
import ro.lab10.tools.Checks;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class CustomerRepository extends JDBCRepository<Long, Customer> {
    private final CustomerValidator validator;

    @Autowired
    public CustomerRepository(JdbcOperations jdbcOperations, CustomerValidator validator) {
        super(jdbcOperations, "Customers");
        this.validator = validator;
    }

    String getDDLColumns() {
        return """
                (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(50) UNIQUE,
                    email VARCHAR(50) UNIQUE
                )""";
    }

    @Override
    RowMapper<Customer> getRowMapper() {
        return (rs, rowNum) -> {
            var id = rs.getLong("id");
            var name = rs.getString("name");
            var email = rs.getString("email");
            return new Customer(id, name, email);
        };
    }

    @Override
    public Optional<Customer> findOne(Long id) {
        Checks.ThrowIfNull(id, "id must not be null");
        var sql = "SELECT * FROM %s WHERE id = ?".formatted(tableName);

        return jdbcOperations.query(sql, getRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<Customer> save(Customer entity) throws ValidatorException {
        validator.validate(entity);
        var sql = "INSERT INTO %s (name, email) VALUES (?, ?)".formatted(tableName);

        jdbcOperations.update(sql, entity.getName(), entity.getEmail());
        return Optional.empty();
    }

    @Override
    public Optional<Customer> delete(Long id) {
        var entity = findOne(id);

        entity.ifPresent(agency -> {
            var sql = "DELETE FROM %s WHERE id = ?".formatted(tableName);
            jdbcOperations.update(sql, id);
        });

        return entity;
    }

    @Override
    public Optional<Customer> update(Customer entity) throws ValidatorException {
        validator.validate(entity);
        var sql = "UPDATE %s SET name = ?, email = ? WHERE id = ?".formatted(tableName);

        return jdbcOperations.update(sql, entity.getName(), entity.getEmail()) == 1
                ? Optional.empty()
                : Optional.of(entity);
    }
}
