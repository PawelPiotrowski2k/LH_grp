package Models;

import java.util.UUID;

public class User {
    private final String name;
    private final String surName;
    private final String email;
    private final String id;
    private boolean isSuspended;

    public User(String name, String surName, String email, boolean isSuspended) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.isSuspended = isSuspended;
        this.id = UUID.randomUUID().toString();
    }

    public void setSuspended(boolean suspended){
        isSuspended = suspended;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public String getId() {
        return id;
    }
}
