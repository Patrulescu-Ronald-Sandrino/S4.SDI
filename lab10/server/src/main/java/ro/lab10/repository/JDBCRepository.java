package ro.lab10.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import ro.lab10.domain.BaseEntity;
import ro.lab10.exceptions.AppException;

public abstract class JDBCRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    protected final JdbcOperations jdbcOperations;

    protected JDBCRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        createTableIfNotExists();
    }

    protected void executeDDLStatement(String sqlDDLStatement) throws AppException { // throws vs no throws; RuntimeException vs Exception
        try {
            jdbcOperations.execute(sqlDDLStatement);
        }
        catch (DataAccessException e) {
            throw new AppException("DataAccessException: " + e);
        }
    }

    abstract void createTableIfNotExists() throws AppException; // throws vs no throws; RuntimeException vs Exception
}
