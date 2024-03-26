package User;

import DBconnection.DbConnection;
import EncryptionDecryption.SaltEncrypting;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    DbConnection dbConnection = new DbConnection();

    public Map<String, User> getUsers() throws UserManagerException {
        try {
            Map<String, User> userMap = new HashMap<>();
            String query = "SELECT name, vorname, email, role FROM users";
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String vorname = resultSet.getString("vorname");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                userMap.put(email, new User(name, vorname, email, role));
            }
            return userMap;
        }catch (SQLException e){
            throw new UserManagerException("sql exception " + e);
        }
    }


    public int removeUser(String email) throws UserManagerException {
        try {
            String query = "DELETE FROM user WHERE email = ?";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1, email);
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new UserManagerException("sql exception " + e);
        }
    }

    public void createUser(String name, String vorname, String email, String password, String role) throws UserManagerException {
        try {
            SaltEncrypting saltEncrypting = new SaltEncrypting();
            String query = "INSERT INTO user (name, vorname, email, password, role,salt) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, vorname);
            statement.setString(3, email);
            statement.setString(4, saltEncrypting.encrypt(password));
            statement.setString(5, role);
            statement.setString(6, saltEncrypting.getSalt());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new UserManagerException("sql exception " + e);
        }
    }
}
