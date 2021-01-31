package estm.dsic.sqleditor.service;

import estm.dsic.sqleditor.dao.IQueryDao;
import estm.dsic.sqleditor.dao.QueryDao;
import estm.dsic.sqleditor.model.Query;

import java.sql.SQLException;

public final class EditorService {
    private static final IQueryDao queryDao = new QueryDao();

    public static String executeQuery(String query) throws SQLException {
        return queryDao.execute(new Query(query));
    }
}
