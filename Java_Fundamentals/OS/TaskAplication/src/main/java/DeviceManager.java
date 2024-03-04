import java.util.Set;

public class DeviceManager {
    Set<String> setOfDevices;

    public void addDevice(String device){
        setOfDevices.add(device);
    }
    public void removeDevice(String device){
        setOfDevices.remove(device);
    }

    public DeviceManager(Set<String> setOfDevices) {
        this.setOfDevices = setOfDevices;
    }
}
