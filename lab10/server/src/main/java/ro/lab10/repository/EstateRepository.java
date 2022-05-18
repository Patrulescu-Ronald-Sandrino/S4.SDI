package ro.lab10.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import ro.lab10.domain.Estate;
import ro.lab10.domain.validators.EstateValidator;
import ro.lab10.domain.validators.ValidatorException;
import ro.lab10.tools.Checks;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class EstateRepository extends JDBCRepository<Long, Estate> {
    private final EstateValidator validator;

    @Autowired
    public EstateRepository(JdbcOperations jdbcOperations, EstateValidator validator) {
        super(jdbcOperations, "Estates");
        this.validator = validator;
    }

    String getDDLColumns() {
        return """
                (
                    id SERIAL PRIMARY KEY,
                    address VARCHAR(50) UNIQUE,
                    surface REAL
                )""";
    }

    @Override
    RowMapper<Estate> getRowMapper() {
        return (rs, rowNum) -> {
            var id = rs.getLong("id");
            var name = rs.getString("name");
            var surface = rs.getDouble("surface");
            return new Estate(id, name, surface);
        };
    }

    @Override
    public Optional<Estate> findOne(Long id) {
        Checks.ThrowIfNull(id, "id must not be null");
        var sql = "SELECT * FROM %s WHERE id = ?".formatted(tableName);

        return jdbcOperations.query(sql, getRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<Estate> save(Estate entity) throws ValidatorException {
        validator.validate(entity);
        var sql = "INSERT INTO %s (address, surface) VALUES (?, ?)".formatted(tableName);

        jdbcOperations.update(sql, entity.getAddress(), entity.getSurface());
        return Optional.empty();
    }

    @Override
    public Optional<Estate> delete(Long id) {
        var entity = findOne(id);

        entity.ifPresent(agency -> {
            var sql = "DELETE FROM %s WHERE id = ?".formatted(tableName);
            jdbcOperations.update(sql, id);
        });

        return entity;
    }

    @Override
    public Optional<Estate> update(Estate entity) throws ValidatorException {
        validator.validate(entity);
        var sql = "UPDATE %s SET address = ?, surface = ? WHERE id = ?".formatted(tableName);

        return jdbcOperations.update(sql, entity.getAddress(), entity.getSurface(), entity.getId()) == 1
                ? Optional.empty()
                : Optional.of(entity);
    }
}
