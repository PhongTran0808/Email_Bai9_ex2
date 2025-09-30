package murach.sqlgateway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String JDBC_URL = 
        "jdbc:postgresql://dpg-d36bqaripnbc7391p3pg-a.singapore-postgres.render.com:5432/test_lggn";
    private static final String JDBC_USER = "test_lggn_user";
    private static final String JDBC_PASS = "3SEkPix9Y7SXwTv9BXaJggyRMowUyU9w";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }
}
