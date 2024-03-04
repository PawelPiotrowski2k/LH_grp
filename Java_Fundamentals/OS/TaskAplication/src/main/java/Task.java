import java.util.List;

public class Task {
    private final User user;
    private final String description;
    private final String place;
    private final List<Device> listOfDevices;
    private Status status;


    public Task(User user, String description, String place, List<Device> listOfDevices) {
        this.user = user;
        this.description = description;
        this.place = place;
        this.status = Status.SENT;
        this.listOfDevices = listOfDevices;
    }

    public void changeStatus(Status status){
        this.status = status;
    }
    public void addDevice(Device device){
        listOfDevices.add(device);
    }
    public void deleteDevice(Device device){
        if(listOfDevices.contains(device)){
            listOfDevices.remove(device);
        }
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

    public List<Device> getListOfDevices() {
        return listOfDevices;
    }
}
