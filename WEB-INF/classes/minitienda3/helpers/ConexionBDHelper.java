package minitienda3.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/minitienda";
    private static final String USERNAME = "av";
    private static final String PASSWORD = "abcABC123@";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
