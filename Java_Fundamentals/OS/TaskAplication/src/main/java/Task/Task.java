package Task;

import DBconnection.DbConnection;
import User.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Task {
    private final String email;
    private final String description;
    private final String place;
    private final List<Integer> listOfDevicesId;
    private final int id;
    private final String status;
    DbConnection dbConnection = new DbConnection();


    public Task(int id, String email, String description, String place, List<Integer> listOfDevicesId,String status) {
        this.id = id;
        this.email = email;
        this.description = description;
        this.place = place;
        this.status = status;
        this.listOfDevicesId = listOfDevicesId;
    }



    public List<Integer> getListOfDevicesId() {
        return listOfDevicesId;
    }

    public int getId(){
        return id;
    }
}
