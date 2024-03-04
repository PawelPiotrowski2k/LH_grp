package DBconnection;

import java.sql.*;

public class DbAdminConnection {
    private final static String url = "jdbc:mysql://localhost:3306/my_schema";
    private final String username = "admin";
    private final String pasword = "admin";

    public static void main(String[] args) {
        DbAdminConnection dbAdminConnection = new DbAdminConnection();
        dbAdminConnection.connectToDB();
    }
    public void connectToDB() {
        try (Connection connection = DriverManager.getConnection(url, username, pasword)) {
            System.out.println("Połączono z bazą danych MySQL!");
            String sql = "DELETE FROM user WHERE id > 3";

            // Utwórz obiekt Statement
            try (Statement statement = connection.createStatement()) {
                int rowsAffected = statement.executeUpdate(sql);
                System.out.println("Usunięto " + rowsAffected + " wiersz(y) z tabeli device.");

            } catch (SQLException e) {
                System.out.println("Błąd połączenia z bazą danych MySQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
