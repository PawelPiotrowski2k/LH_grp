package DBconnection;

import java.sql.*;

public class DbUserConnection {
    private final static String url = "jdbc:mysql://localhost:3306/my_schema";
    private final String username = "user";
    private final String pasword = "taskManager";

    public static void main(String[] args) {
        DbUserConnection dbUserConnection = new DbUserConnection();
        dbUserConnection.connectToDB();
    }

    public void connectToDB() {
        try (Connection connection = DriverManager.getConnection(url, username, pasword)) {
            System.out.println("Połączono z bazą danych MySQL!");
            String sql = "SELECT * FROM device";

            // Utwórz obiekt Statement
            try (Statement statement = connection.createStatement()) {
                // Wykonaj zapytanie SQL
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    // Przetwórz wyniki zapytania
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nazwaDevice = resultSet.getString("name");
                        System.out.println("ID: " + id + ", Nazwa urządzenia: " + nazwaDevice);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Błąd połączenia z bazą danych MySQL: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
