package estm.dsic.sqleditor.dao;

import estm.dsic.sqleditor.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionDao {
    protected static Connection connection;
    protected static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) throw new LoggedOutUserException();
        return connection;
    }

    protected abstract boolean open(User user) throws SQLException;
    protected abstract void close() throws SQLException;
}
