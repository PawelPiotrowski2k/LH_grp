package Task;

import DBconnection.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskManager {


    DbConnection dbConnection = new DbConnection();
//    public Set<Task> getTasks() throws SQLException {
//        Set<Task> taskSet = new HashSet<>();
//        String query = "SELECT * FROM task";
//        Statement statement = dbConnection.getConnection().createStatement();
//        ResultSet resultSet = statement.executeQuery(query);
//
//        while (resultSet.next()){
//            int id = resultSet.getInt("id");
//            String mail = resultSet.getString("mail");
//            String status = resultSet.getString("status");
//            String description = resultSet.getString("description");
//            String place = resultSet.getString("place");
//        }
//    }
    public void createTask(String email, String description,String status, String place, List<Integer> listOfDevices) throws SQLException {
        for (int device : listOfDevices){
            String query = "INSERT INTO task (id, email, status, description, place, id_device) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setInt(1, calculateId());
            statement.setString(2, email);
            statement.setString(3, status);
            statement.setString(4, description);
            statement.setString(5, place);
            statement.setInt(6, device);
            statement.executeUpdate();
        }
    }
    private int calculateId() throws SQLException {
        String query = "SELECT id FROM task ORDER BY id DESC LIMIT 1;";
        Statement statement = dbConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        return resultSet.getInt("id") + 1;
    }
}
