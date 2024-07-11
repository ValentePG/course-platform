package dev.valente.course_platform.devs;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.valente.course_platform.content.Content;
import jakarta.persistence.*;

import java.math.BigDecimal;
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

    @Column
    private Double XP = 0.0;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_WATCHED_CONTENT",
            joinColumns = @JoinColumn(name = "devs_id"),
            inverseJoinColumns = @JoinColumn(name = "watched_content_id"))
    private Set<Content> watchedContent = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "listOfDevs", fetch = FetchType.LAZY)
    private Set<Content> listOfContents = new HashSet<>();

    public Devs(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public Devs(){

    }

    public Set<Content> getListOfContents() {
        return listOfContents;
    }

    public Double getXP() {
        return XP;
    }

    public Set<Content> getWatchedContent() {
        return watchedContent;
    }

    public void setWatchedContent(Set<Content> watchedContent) {
        this.watchedContent = watchedContent;
    }

    public void setXP(Double XP) {
        this.XP = XP;
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

}
