package dev.valente.course_platform.common;

import org.springframework.stereotype.Component;

import java.util.UUID;

public class HolderData {
    private UUID id;

    private String userName;

    public HolderData(){
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
