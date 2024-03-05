package Device;

import DBconnection.DbConnection;

import java.sql.Connection;
import java.util.Set;

public class DeviceManager {
    Set<String> setOfDevices;

    public DeviceManager() {
    }
    public void addDevice(String device){
        setOfDevices.add(device);
    }

    public void removeDevice(String device){
        setOfDevices.remove(device);
    }
}
