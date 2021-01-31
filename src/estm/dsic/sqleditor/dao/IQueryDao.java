package estm.dsic.sqleditor.dao;

import estm.dsic.sqleditor.model.Query;

import java.sql.SQLException;

public interface IQueryDao {
    default String execute(Query query) throws SQLException {
        throw new UnsupportedOperationException("");
    }
}
