package Authentication;

import DBconnection.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import User.User;


public class Authentication {
    User user;
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
    public boolean correctLoging(String email, String password) throws AuthenticationException {
        try {
            DbConnection dbConnection = new DbConnection();
            Connection connection = dbConnection.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'");
            return rs.next();
        }catch (SQLException e){
            throw new AuthenticationException("SQL exception " + e);
        }
    }

}
