package dev.valente.course_platform.devs;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.valente.course_platform.content.Content;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "TB_DEVS")
@Entity
public class Devs {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String userName;

    @Column
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "listOfDevs", fetch = FetchType.LAZY)
    private Set<Content> contents = new HashSet<>();

    public Set<Content> getContents() {
        return contents;
    }

    public void setContents(Set<Content> contents) {
        this.contents = contents;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public Devs(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public Devs(){

    }
}
