package Task;

import DBconnection.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    DbConnection dbConnection = new DbConnection();
    public Map<Integer,Task> getUsersTaskMap(String mail) throws TaskManagerException {
        Map<Integer,Task> taskMap = new HashMap<>();
        try {
            String query = "SELECT distinct id FROM task where mail = '" + mail + "'";
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                taskMap.put(resultSet.getInt("id"),getTaskFromId(resultSet.getInt("id")));
            }
        }catch (SQLException e){
            throw new TaskManagerException("sql exception " + e);
        }
        return taskMap;
    }
    public Map<Integer,Task> getAllTaskMap() throws TaskManagerException {
        Map<Integer,Task> taskMap = new HashMap<>();
        try {
            String query = "SELECT distinct id FROM task";
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                taskMap.put(resultSet.getInt("id"),getTaskFromId(resultSet.getInt("id")));
            }
        }catch (SQLException e){
            throw new TaskManagerException("sql exception " + e);
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
    private Task getTaskFromId(int id) throws TaskManagerException {
        String email = "";
        String description = "";
        String status = "";
        String place = "";
        List<Integer> deviceList = new ArrayList<>();
        try {
            String query = "SELECT * FROM task WHERE id = " + id;
            Statement statement1 = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement1.executeQuery(query);
            while (resultSet.next()){
                email = resultSet.getString("mail");
                description = resultSet.getString("description");
                status = resultSet.getString("status");
                place = resultSet.getString("place");
                deviceList.add(resultSet.getInt("id_device"));
            }
            return new Task(id,email,description,place,deviceList,status);
        }catch (SQLException e){
            throw new TaskManagerException("SQL exception " + e);
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
    private int calculateId(String mail) throws TaskManagerException {
        try {
            String query = "SELECT id FROM task WHERE email = '" + mail + "' ORDER BY id DESC LIMIT 1;";
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("id") + 1;
        }catch (SQLException e){
            throw new TaskManagerException("SQL exception " + e);
        }
    }


}
