package DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private final static String url = "jdbc:mysql://localhost:3306/my_schema";
    private final String username = "admin";
    private final String pasword = "admin";

    public Connection getConnection() {
        try {
           return DriverManager.getConnection(url, username, pasword);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
