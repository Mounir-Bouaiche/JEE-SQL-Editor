package estm.dsic.sqleditor.dao;

import estm.dsic.sqleditor.model.Query;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import static estm.dsic.sqleditor.model.Query.QueryType.LDD;
import static estm.dsic.sqleditor.model.Query.QueryType.LMD_WITH_RESULT;

public final class QueryDao implements IQueryDao {
    @Override
    public String execute(Query query) throws SQLException {
        Statement stm = JBossConnection.getConnection().createStatement();
        StringBuffer result = new StringBuffer();

        if (query.getType() == LMD_WITH_RESULT) {
            ResultSet resultSet = stm.executeQuery(query.getQuery());

            if (!resultSet.next()) {
                result.append("<div class='w3-container pale-blue'><h1><i class='ic-success'></i>No result founded</h1></div>");
            } else {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int colCount = metaData.getColumnCount();
                int i;

                // table header
                result.append("<thead><tr>");
                for (i = 1; i <= colCount; i++)
                    result.append("<th>").append(metaData.getColumnLabel(i)).append("</th>");
                result.append("</tr></thead>");

                // table data
                result.append("<tbody>");
                do {
                    result.append("<tr>");
                    for (i = 1; i <= colCount; i++)
                        result.append("<td>").append(resultSet.getObject(i)).append("</td>");
                    result.append("</tr>");
                } while (resultSet.next());
                result.append("</tbody>");

                // wrap in table
                result.insert(0, "<table class='w3-table-all'>");
                result.append("</table>");
            }
            resultSet.close(); // prevent open cursors limit exception
        } else {
            int count = stm.executeUpdate(query.getQuery());
            String cls = "pale-";

            if (query.getType() == LDD) {
                result.append("L'opération a réussi");
                cls += "green";
            } else {
                result.append(count).append(" ligne(s) affectée(s)");
                cls += "blue";
            }

            result.insert(0, "<div class='w3-container " + cls + "'><h1><i class='ic-success'></i>");
            result.append("</h1></div>");
        }

        stm.close();
        return result.toString();
    }
}
