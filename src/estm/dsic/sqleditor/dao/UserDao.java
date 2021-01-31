package estm.dsic.sqleditor.dao;

import estm.dsic.sqleditor.model.User;

import java.sql.SQLException;

public class UserDao implements IUserDao {
    private final ConnectionDao connectionDao;

    public UserDao() {
        connectionDao = new JBossConnection();
    }

    @Override
    public void login(User user) throws SQLException {
        if (! connectionDao.open(user)) throw new SQLException("UNKNOWN", "UNKNOWN", 5566);
    }

    @Override
    public void logout() throws SQLException {
        connectionDao.close();
    }
}
