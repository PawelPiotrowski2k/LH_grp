package Task;

import java.util.List;

public class Task {
    private final String email;
    private final String description;
    private final String place;
    private final List<Integer> listOfDevicesId;
    private final int id;
    private final String status;


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
    public String getEmail(){
        return email;
    }
}
