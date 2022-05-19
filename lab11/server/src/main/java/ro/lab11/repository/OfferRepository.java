package ro.lab11.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import ro.lab11.domain.Offer;
import ro.lab11.domain.Pair;
import ro.lab11.domain.validators.OfferValidator;
import ro.lab11.domain.validators.ValidatorException;
import ro.lab11.exceptions.AppException;
import ro.lab11.tools.Checks;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class OfferRepository extends JDBCRepository<Pair<Long, Long>, Offer> {
    private final OfferValidator validator;

    @Autowired
    public OfferRepository(JdbcOperations jdbcOperations, OfferValidator validator) {
        super(jdbcOperations, "Offers");
        this.validator = validator;
    }

    @Override
    String getDDLColumns() {
        return """
                (
                    agencyId INT,
                    estateId INT,
                    price REAL,
                    PRIMARY KEY (agencyId, estateId),
                    FOREIGN KEY (agencyId) REFERENCES Agencies (id),
                    FOREIGN KEY (estateId) REFERENCES Estates (id)
                )""";
    }

    @Override
    RowMapper<Offer> getRowMapper() {
        return (rs, rowNum) -> {
            var agencyId = rs.getLong("agencyId");
            var estateId = rs.getLong("estateId");
            var price = rs.getDouble("price");
            return new Offer(agencyId, estateId, price);
        };
    }

    @Override
    public Optional<Offer> findOne(Pair<Long, Long> id) {
        Checks.ThrowIfNull(id, "id must not be null");
        var sql = "SELECT * FROM %s WHERE agencyId = ? AND estateId = ?".formatted(tableName);

        return jdbcOperations.query(sql, getRowMapper(), id.getLeft(), id.getRight()).stream().findFirst();
    }

    @Override
    public Optional<Offer> save(Offer entity) throws ValidatorException {
        validator.validate(entity);
        var sql = "INSERT INTO %s (agencyId, estateId, price) VALUES (?, ?, ?)".formatted(tableName);

        if (findOne(entity.getId()).isPresent()) {
            return Optional.of(entity);
        }
        try {
            jdbcOperations.update(sql, entity.getAgencyId(), entity.getEstateId(), entity.getPrice());
            return Optional.empty();
        }
        catch (DataIntegrityViolationException e) {
            throw new AppException("Offer was NOT added: agency id and estate id must be existent PKs in their corresponding table");
        }
    }

    @Override
    public Optional<Offer> delete(Pair<Long, Long> id) {
        var entity = findOne(id);

        entity.ifPresent(agency -> {
            var sql = "DELETE FROM %s WHERE agencyId = ? AND estateId = ?".formatted(tableName);
            jdbcOperations.update(sql, id.getLeft(), id.getRight());
        });

        return entity;
    }

    @Override
    public Optional<Offer> update(Offer entity) throws ValidatorException {
        validator.validate(entity);
        var sql = "UPDATE %s SET price = ? WHERE agencyId = ? AND estateId = ?".formatted(tableName);

        return jdbcOperations.update(sql, entity.getPrice(), entity.getAgencyId(), entity.getEstateId()) == 1
                ? Optional.empty()
                : Optional.of(entity);
    }
}
