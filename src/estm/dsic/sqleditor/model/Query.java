package estm.dsic.sqleditor.model;

import java.io.Serializable;

public class Query implements Serializable {
    private QueryType type;
    private String query;

    public Query() {
    }

    public Query(String query) {
        this.query = query;
        this.type = switch (query.split("\\s")[0].toLowerCase()) {
            case "select" -> QueryType.LMD_WITH_RESULT;
            case "update", "delete", "insert" -> QueryType.LMD_WITHOUT_RESULT;
            default -> QueryType.LDD;
        };
    }

    public QueryType getType() {
        return type;
    }

    public void setType(QueryType type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public enum QueryType {
        LMD_WITH_RESULT, LMD_WITHOUT_RESULT, LDD
    }
}
