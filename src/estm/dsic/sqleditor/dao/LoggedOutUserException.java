package estm.dsic.sqleditor.dao;

import estm.dsic.sqleditor.service.AuthService;

import java.sql.SQLException;

public class LoggedOutUserException extends SQLException {
    private static final String REASON = "Logged out user";
    private static final String SQL_STATE = "Must log in again";
    private static final int SQL_CODE = 1513;

    public LoggedOutUserException() throws SQLException {
        super(REASON, SQL_STATE, SQL_CODE);
        AuthService.logout();
    }
}
