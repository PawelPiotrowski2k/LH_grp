import Authentication.Authentication;
import Authentication.AuthenticationException;
import Device.Device;
import Device.DeviceManager;
import Device.DeviceManagerException;
import EncryptionDecryption.EncryptionException;
import Task.Task;
import Task.TaskManager;
import Task.TaskManagerException;
import User.User;
import User.UserManager;
import User.UserManagerException;

import java.util.List;
import java.util.Map;

public class TaskAplication {
    private Authentication authentication;
    private DeviceManager deviceManager;
    private TaskManager taskManager;
    private UserManager userManager;
    private MailSender mailSender;

    public TaskAplication(Authentication authentication, DeviceManager deviceManager, TaskManager taskManager, UserManager userManager, MailSender mailSender) {
        this.authentication = authentication;
        this.deviceManager = deviceManager;
        this.taskManager = taskManager;
        this.userManager = userManager;
        this.mailSender = mailSender;
    }
    public void addUser(String name, String vorname, String email, String password, String role) throws EncryptionException, UserManagerException {
            userManager.createUser(name,vorname,email,password,role);
    }
    public void removeUser(String mail) throws UserManagerException {
        userManager.removeUser(mail);
    }
    public Map<String,User> getUsers() throws UserManagerException {
       return userManager.getUsers();
    }
    public Map<Integer,Task> getTasks() throws TaskManagerException {
       return taskManager.getTaskMap();
    }
    public void changeTaskStatus(String status, int id) throws TaskManagerException {
        taskManager.changeStatus(status, id);
    }
    public void createTask(String mail,String desription,String status,String place, List<Integer> listOfDevicesId) throws TaskManagerException {
        taskManager.createTask(mail,desription,status,place,listOfDevicesId);
    }
    public void addDevice(String device) throws DeviceManagerException {
        deviceManager.addDevice(device);
    }
    public void removeDevice(int id) throws DeviceManagerException {
        deviceManager.removeDevice(id);
    }
    public Map<Integer,Device> getDeviceMap() throws DeviceManagerException {
        return deviceManager.getMapOfDevices();
    }
    public User loginUser(String mail,String password) throws AuthenticationException {
        return authentication.LogInUser(mail,password);
    }


}
