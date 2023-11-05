import java.util.ArrayList;
import java.util.List;


public class User {
    private final String name;
    private final String surName;
    private final String email;
    boolean isSuspended;

    public User(String name, String surName, String email) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.isSuspended = false;
    }

    public void setSuspended(boolean suspended){
        isSuspended = suspended;
    }

}
