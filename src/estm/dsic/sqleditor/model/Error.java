package estm.dsic.sqleditor.model;

import java.io.Serializable;
import java.sql.SQLException;

public class Error implements Serializable {
    private ErrorCode code;
    private String message;

    public Error() {
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum ErrorCode {
        AUTH_ERROR, SQL_ERROR
    }

    public static Error fromException(ErrorCode code, SQLException ex) {
        String sqlState = ex.getSQLState();
        int exCode = ex.getErrorCode();
        Error e = new Error();
        e.setMessage((sqlState != null ? sqlState : "")
                + "<br>" + ex.getMessage()
                + (exCode > 0 ? "<br>[code: " + exCode + "]": "")
        );
        e.setCode(code);
        return e;
    }
}
