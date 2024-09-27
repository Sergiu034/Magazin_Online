package Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DataBase_Connection {

    private static final String URL = "jdbc:mysql://localhost:3306/online_store";
    private static final String USER = "root";
    private static final String PASSWORD = "toor";

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
