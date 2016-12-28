package week09;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MySQLHelper {
    private Connection conn;

    public MySQLHelper(String dbName) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T extends Mappable> boolean insertInto(String table, T o) {
        try {
            Statement st = conn.createStatement();
            String fields = "(";
            String values = "(";
            for (String entry : o.toMap().keySet()) {
                fields += entry + ", ";
            }

            for (String value : o.toMap().values()) {
                values += String.format("\"%s\", ", value);
            }

            fields = fields.substring(0, fields.length() - 2);
            fields += ")";
            values = values.substring(0, values.length() - 2);
            values += ")";

            String req = String.format("insert into %s %s values %s;", table, fields, values);
            System.out.println("Request sent: " + req);
            st.execute(req);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteFrom(String table, int id) {
        try {
            Statement st = conn.createStatement();
            String req = String.format("delete from %s where id = %d;", table, id);
            st.execute(req);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean deleteFrom(String table, String whereClause) {
        try {
            Statement st = conn.createStatement();
            String req = String.format("delete from %s where %s;", table, whereClause);
            st.execute(req);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean update(String table, Object o) {
        Mappable mappable = (Mappable) o;
        StringBuilder valsBuilder = new StringBuilder();

        String id = "";
        for (Map.Entry<String, String> entry : mappable.toMap().entrySet()) {
            valsBuilder.append(String.format("%s = %s, ", entry.getKey(), entry.getValue()));
            if (entry.getKey().equals("id"))
                id = entry.getKey();
        }

        String vals = valsBuilder.toString();
        vals = vals.substring(0, vals.length() - 2);

        try {
            Statement st = conn.createStatement();
            String req = String.format("update from %s set %s where id = %s;", table, vals, id);
            st.execute(req);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }


    public List<List<String>> selectFrom(String table, String colSelect, String whereClause, String orderClause) {
        try {
            List<List<String>> list = new LinkedList<>();
            Statement st = conn.createStatement();
            String sql;
            if (colSelect == null) {
                sql = "select * from " + table;
            } else {
                sql = "select " + colSelect + " from " + table;
            }

            if (whereClause != null) {
                sql += " where " + whereClause;
            }

            if (orderClause != null) {
                sql += " order by " + orderClause;
            }
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                List<String> row = new LinkedList<>();
                int cols = rsmd.getColumnCount();
                for (int i = 1; i <= cols; i++) {
                    row.add(rs.getString(i));
                }

                list.add(row);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
