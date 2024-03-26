package Device;

import DBconnection.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DeviceManager {
    Map<Integer,Device> deviceMap;
    DbConnection dbConnection = new DbConnection();


    public void addDevice(String device) throws DeviceManagerException {
        try {
            String query = "INSERT INTO device (name) VALUES(?)";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setString(1,device);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DeviceManagerException("sql exception " + e);
        }
    }

    public void removeDevice(int id) throws DeviceManagerException {
        try {
            String query = "DELETE FROM device WHERE id = ?";
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DeviceManagerException("sql exception " + e);
        }
    }
    public Map<Integer,Device> getMapOfDevices() throws DeviceManagerException {
        try {
            deviceMap = new HashMap<>();
            String query = "SELECT * FROM devices";
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                deviceMap.put(resultSet.getInt("id"), new Device(resultSet.getInt("id"), resultSet.getString("name") ));
            }
            return deviceMap;
        }catch (SQLException e){
            throw new DeviceManagerException("sql exception " + e);
        }
    }
}
