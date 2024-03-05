package User;
import DBconnection.DbConnection;
import EncryptionDecryption.Encryption;
import EncryptionDecryption.EncryptionException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class UserManager {
    DbConnection dbConnection = new DbConnection();

    public Set<User> getUsers() throws SQLException {
        Set<User> userSet = new HashSet<>();
        String query = "SELECT * FROM users";
        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String vorname = resultSet.getString("vorname");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            userSet.add(new User(name, vorname, email, password, role));
        }

        return userSet;
    }

    public int removeUser(String email) throws SQLException {
        String query = "DELETE FROM user WHERE email = ?";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
        statement.setString(1,email);
        return statement.executeUpdate();
    }

    public void createUser(String name, String vorname, String email, String password, String role) throws SQLException, EncryptionException {
        Encryption encryption = new Encryption();
        String query = "INSERT INTO user (name, vorname, email, password, role) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, vorname);
        statement.setString(3, email);
        statement.setString(4, encryption.encrypt(password));
        statement.setString(5, role);
        statement.executeUpdate();
    }
}
