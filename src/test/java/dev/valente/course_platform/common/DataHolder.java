package dev.valente.course_platform.common;

import java.util.UUID;

public class DataHolder {
    private UUID id;

    private String userName;

    public DataHolder(){
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
