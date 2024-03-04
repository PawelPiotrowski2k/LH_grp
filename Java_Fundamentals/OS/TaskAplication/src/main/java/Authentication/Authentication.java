package Authentication;

import DBconnection.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Authentication {
    public String getRole(String email,String password) throws SQLException {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        if(correctLoging(email,password)){
             ResultSet resultSet = connection.createStatement().executeQuery("SELECT role FROM user WHERE email = '" + email + "' AND password = '" + password +"'");
             resultSet.next();
             return  resultSet.getString("role");
        }else {
             return "wrong username or password";
        }
    }
    public boolean correctLoging(String email, String password) throws SQLException {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'");
        return rs.next();
    }

}
