package estm.dsic.sqleditor.dao;

import estm.dsic.sqleditor.model.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

public class JBossConnection extends ConnectionDao {
    protected static DataSource dataSource;

    protected JBossConnection() {
        if (dataSource == null) {
            try { dataSource = InitialContext.doLookup(JNDI()); }
            catch (NamingException ignored) { }
        }
    }

    protected String JNDI() {
        return "java:/jspOracleAdmin";
    }

    @Override
    protected boolean open(User user) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            try {
                connection.close();
            } catch (SQLException e) {
                connection = null;
            }
        }
        try {
            connection = dataSource.getConnection(user.getUsername(), user.getPassword());
        } catch (Exception ex) {
            throw new SQLException("Nom d'utilisateur ou mot de passe incorrect");
        }

        return connection != null;
    }

    @Override
    protected void close() {
        try { if (connection != null && !connection.isClosed()) connection.close(); }
        catch (SQLException ignored) { }
        finally { connection = null; }
    }
}
