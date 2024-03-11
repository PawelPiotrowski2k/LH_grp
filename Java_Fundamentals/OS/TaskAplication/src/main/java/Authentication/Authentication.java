package Authentication;

import DBconnection.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import EncryptionDecryption.Decryption;
import EncryptionDecryption.DecryptionException;
import EncryptionDecryption.Encryption;
import EncryptionDecryption.EncryptionException;
import User.User;


public class Authentication {
    User user;
    Decryption decryption = new Decryption();
    DbConnection dbConnection = new DbConnection();
    public User LogInUser(String email,String password) throws AuthenticationException {
        try {
            if(correctLoging(email,password)){
                Statement statement = dbConnection.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'");
                String name = resultSet.getString("name");
                String vorname = resultSet.getString("vorname");
                String emailUser = resultSet.getString("email");
                String passwordUser = resultSet.getString("password");
                String role = resultSet.getString("role");
                return new User(name,vorname,emailUser,passwordUser,role);
            }else {
                return null;
            }
        }catch (SQLException e){
            throw new AuthenticationException("SQL exception " + e);
        }
    }
    private boolean correctLoging(String email, String password) throws AuthenticationException {
        return getUsersDecrptedPassword(email).equals(password);
    }
    private String getUsersDecrptedPassword(String mail) throws AuthenticationException {
        try {
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT password FROM user WHERE email = '" + mail + "'");
            resultSet.next();
            return decryption.decrypt(resultSet.getString("password"));
        } catch (SQLException e) {
            throw new AuthenticationException("SQL exception " + e);
        } catch (DecryptionException e) {
            throw new AuthenticationException("decryption exception " + e);
        }
    }

}
