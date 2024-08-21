package dev.valente.course_platform.devs;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.valente.course_platform.content.Content;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "TB_DEVS")
@Entity
public class Devs implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String userName;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @Column
    private Double xp = 0.0;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_WATCHED_CONTENT",
            joinColumns = @JoinColumn(name = "devs_id"),
            inverseJoinColumns = @JoinColumn(name = "watched_content_id"))
    private Set<Content> listOfWatchedContents = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "listOfDevsRegistered", fetch = FetchType.LAZY)
    private Set<Content> listOfContentsRegistered = new HashSet<>();

    public Devs(){

    }
    public Devs(String userName,String password){
        this.userName = userName;
        this.password = password;
    }
    public Devs(UUID id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Set<Content> getListOfContentsRegistered() {
        return listOfContentsRegistered;
    }

    public Double getXp() {
        return xp;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Content> getListOfWatchedContents() {
        return listOfWatchedContents;
    }

    public void setListOfWatchedContents(Set<Content> listOfWatchedContents) {
        this.listOfWatchedContents = listOfWatchedContents;
    }

    public void setXp(Double xp) {
        this.xp = xp;
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

    @Override
    public String toString() {
        return "Devs{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", xp=" + xp +
                ", listOfWatchedContents=" + listOfWatchedContents +
                ", listOfContentsRegistered=" + listOfContentsRegistered +
                '}';
    }

}
