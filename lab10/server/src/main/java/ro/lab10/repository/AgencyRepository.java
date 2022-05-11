package ro.lab10.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import ro.lab10.domain.Agency;
import ro.lab10.domain.validators.AgencyValidator;
import ro.lab10.domain.validators.ValidatorException;
import ro.lab10.tools.Checks;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class AgencyRepository extends JDBCRepository<Long, Agency> {
    private final AgencyValidator validator;

    @Autowired
    public AgencyRepository(JdbcOperations jdbcOperations, AgencyValidator validator) {
        super(jdbcOperations, "Agencies");
        this.validator = validator;
    }

     String getDDLColumns() {
        return """
                (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(50) UNIQUE,
                    address VARCHAR(50) UNIQUE,
                )""";
    }

    @Override
    RowMapper<Agency> getRowMapper() {
        return (rs, rowNum) -> {
            var id = rs.getLong("id");
            var name = rs.getString("name");
            var address = rs.getString("address");
            return new Agency(id, name, address);
        };
    }

    @Override
    public Optional<Agency> findOne(Long id) {
        Checks.ThrowIfNull(id, "id must not be null");
        var sql = "SELECT * FROM %s WHERE id = ?".formatted(tableName);

        return jdbcOperations.query(sql, getRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<Agency> save(Agency entity) throws ValidatorException {
        validator.validate(entity);
        var sql = "INSERT INTO %s VALUES (?, ?)".formatted(tableName);

        jdbcOperations.update(sql, entity.getName(), entity.getAddress());
        return Optional.empty();
    }

    @Override
    public Optional<Agency> delete(Long id) {
        var entity = findOne(id);

        entity.ifPresent(agency -> {
            var sql = "DELETE FROM %s WHERE id = ?".formatted(tableName);
            jdbcOperations.update(sql, id);
        });

        return entity;
    }

    @Override
    public Optional<Agency> update(Agency entity) throws ValidatorException {
        validator.validate(entity);
        var sql = "UPDATE %s SET name = ?, address = ? WHERE id = ?".formatted(tableName);

        return jdbcOperations.update(sql, entity.getName(), entity.getAddress()) == 1
                ? Optional.empty()
                : Optional.of(entity);
    }
}
