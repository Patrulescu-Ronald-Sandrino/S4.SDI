package ro.lab10.repository;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import ro.lab10.domain.BaseEntity;

import java.util.List;

public abstract class JDBCRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    protected final JdbcOperations jdbcOperations;

    protected final String tableName;

    protected JDBCRepository(JdbcOperations jdbcOperations, String tableName) {
        this.jdbcOperations = jdbcOperations;
        this.tableName = tableName;
//        executeDDLStatement("""
//                CREATE IF NOT EXISTS """ + tableName + " "
//                + getDDLColumns());
        executeDDLStatement("CREATE TABLE IF NOT EXISTS %s %s".formatted(tableName, getDDLColumns()));
    }

    protected void executeDDLStatement(String sqlDDLStatement) {
        jdbcOperations.execute(sqlDDLStatement);
    }

    abstract String getDDLColumns();

    abstract RowMapper<T> getRowMapper();

    /**
     * @return "SELECT * FROM <tableName>"
     */
    protected String getSelectAllFromTableStatement() {
        return String.format("SELECT * FROM %s", tableName);
    }

    @Override
    public List<T> findAll() {
        return jdbcOperations.query(getSelectAllFromTableStatement(), getRowMapper());
    }

    // TODO: IDEA - use an abstract methods to get prepared statements and
    //  use them to write the operations only once - in the abstract class
    //  (see https://www.educba.com/jdbc-preparedstatement/)
    //      - the WHERE clause for the id repeats in findOne(), update(), delete()
    //      - every excepting parts of the sql string and the arguments passed to the jdbcOperations methods, methods
    //          findOne(), save(), update(), delete() are the same


    // TODO: How to call super constructor when using field injection?
}
