package Task;

import User.User;

import java.util.List;

public class Task {
    private final String email;
    private final String description;
    private final String place;
    private final List<String> listOfDevices;
    private final int id;
    private Status status;


    public Task(int id, String email, String description, String place, List<String> listOfDevices,Status status) {
        this.id = id;
        this.email = email;
        this.description = description;
        this.place = place;
        this.status = status;
        this.listOfDevices = listOfDevices;
    }

    public void changeStatus(Status status){
        this.status = status;
    }
    public int getId(){
        return id;
    }
}
