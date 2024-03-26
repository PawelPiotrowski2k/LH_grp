package DBconnection;

import Config.ConfigManager;
import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private String url;
    private String username;
    private String pasword;

    public DbConnection() {
        ConfigManager configManager = new ConfigManager();
        this.url = configManager.getDbUrl();
        this.username = configManager.getDbUsername();
        this.pasword = configManager.getDbPassword();
    }

    public Connection getConnection() {
        try {
           return DriverManager.getConnection(url, username, pasword);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
