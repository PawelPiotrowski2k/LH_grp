package Authentication;

import DBconnection.DbConnection;
import EncryptionDecryption.SaltEncrypting;
import User.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;


public class Authentication {
    DbConnection dbConnection = new DbConnection();

    public User logInUser(String email,String password) throws AuthenticationException {
        try {
            if(correctLoging(email,password)){
                Statement statement = dbConnection.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE email = '" + email + "'");
                resultSet.next();
                String name = resultSet.getString("name");
                String vorname = resultSet.getString("vorname");
                String emailUser = resultSet.getString("email");
                String role = resultSet.getString("role");
                return new User(name,vorname,emailUser,role);
            }else {
                return null;
            }
        }catch (SQLException e){
            throw new AuthenticationException("exception " + e);
        }
    }
    private boolean correctLoging(String email, String password) throws  AuthenticationException {
        SaltEncrypting saltEncrypting = new SaltEncrypting();
        try {
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT password FROM user WHERE email = '" + email + "'");
            resultSet.next();
            return saltEncrypting.encrypt(password, getSalt(email)).equals(resultSet.getString("password"));
        }catch (SQLException e){
            throw new AuthenticationException("sql exception " + e);
        }
    }
    private byte[] getSalt(String mail) throws AuthenticationException {
        try {
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT salt FROM user WHERE email = '" + mail + "'");
            resultSet.next();
            return Base64.getDecoder().decode(resultSet.getBytes("salt"));
        }catch (SQLException e){
            throw new AuthenticationException("sql exception " + e);
        }
    }

}
