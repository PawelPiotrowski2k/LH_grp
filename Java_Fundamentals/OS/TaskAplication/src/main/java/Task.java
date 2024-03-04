import java.util.List;

public class Task {
    private final int id;
    private final User user;
    private final String description;
    private final String place;
    private final List<String> listOfDevices;
    private Status status;


    public Task(int id, User user, String description, String place, List<String> listOfDevices) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.place = place;
        this.status = Status.SENT;
        this.listOfDevices = listOfDevices;
    }

    public void changeStatus(Status status){
        this.status = status;
    }
    public void addDevice(){
    }
    public void deleteDevice(){
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public Status getStatus() {
        return status;
    }

    public List<String> getListOfDevices() {
        return listOfDevices;
    }
}
