package estm.dsic.sqleditor.dao;

import estm.dsic.sqleditor.model.User;

import java.sql.SQLException;

public interface IUserDao {
    void login(User user) throws SQLException;
    void logout() throws SQLException;
}
