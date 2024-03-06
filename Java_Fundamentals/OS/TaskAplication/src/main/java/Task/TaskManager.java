package Task;

import DBconnection.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class TaskManager {
    DbConnection dbConnection = new DbConnection();
    public Map<Integer,Task> getTaskMap() throws TaskManagerException {
        int lastId = calculateId() - 1;
        int currentid = 1;
        Map<Integer,Task> taskMap = new HashMap<>();
        try {
            for (int i = currentid; i <= lastId; i++) {
                int id = 0;
                String email = "";
                String description = "";
                String status = "";
                String place = "";
                List<Integer> deviceList = new ArrayList<>();

                String query2 = "SELECT * FROM task WHERE id = " + i;
                Statement statement1 = dbConnection.getConnection().createStatement();
                ResultSet resultSet1 = statement1.executeQuery(query2);
                while (resultSet1.next()){
                    id = resultSet1.getInt("id");
                    email = resultSet1.getString("mail");
                    description = resultSet1.getString("description");
                    status = resultSet1.getString("status");
                    place = resultSet1.getString("place");
                    deviceList.add(resultSet1.getInt("id_device"));
                }
                taskMap.put(id,new Task(id,email,description,place,deviceList,status));
            }
        }catch (SQLException e){
            throw new TaskManagerException("SQL exception " + e);
        }
        return taskMap;
    }
    public void changeStatus(String status, int id) throws TaskManagerException {
        try {
            String query = "UPDATE task SET status = ? WHERE id = ?";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1,status);
            statement.setInt(2,id);
        }catch (SQLException e){
            throw new TaskManagerException("SQL exception " + e);
        }
    }

    public void createTask(String email, String description,String status, String place, List<Integer> listOfDevices) throws TaskManagerException {
        int id = calculateId();
        for (int device : listOfDevices){
            String query = "INSERT INTO task (id, mail, status, description, place, id_device) VALUES (?, ?, ?, ?, ?, ?)";
            try(PreparedStatement statement = dbConnection.getConnection().prepareStatement(query)){
                statement.setInt(1, id);
                statement.setString(2, email);
                statement.setString(3, status);
                statement.setString(4, description);
                statement.setString(5, place);
                statement.setInt(6, device);
                statement.executeUpdate();
            }catch (SQLException e){
                throw new TaskManagerException("SQL exception " + e);
            }
        }
    }
    private int calculateId() throws TaskManagerException {
        try {
            String query = "SELECT id FROM task ORDER BY id DESC LIMIT 1;";
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("id") + 1;
        }catch (SQLException e){
            throw new TaskManagerException("SQL exception " + e);
        }
    }
}
