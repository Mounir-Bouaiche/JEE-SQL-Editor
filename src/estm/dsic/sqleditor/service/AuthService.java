package estm.dsic.sqleditor.service;

import estm.dsic.sqleditor.dao.IUserDao;
import estm.dsic.sqleditor.dao.UserDao;
import estm.dsic.sqleditor.model.User;

import java.sql.SQLException;

public final class AuthService {
    private final static IUserDao userDao = new UserDao();

    public static void authenticate(User user) throws SQLException {
        userDao.login(user);
    }

    public static void logout() throws SQLException {
        userDao.logout();
    }
}
