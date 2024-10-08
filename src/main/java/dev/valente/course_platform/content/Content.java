package dev.valente.course_platform.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.valente.course_platform.devs.Devs;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "TB_CONTENT")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Content implements Serializable {

    @Id
    @GeneratedValue
    protected UUID id;

    @Column
    protected String description;

    @Column
    protected Integer duration;

    @Column
    protected LocalDate dataOfCreation;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_CONTENT_DEVS",
               joinColumns = @JoinColumn(name = "content_id"),
               inverseJoinColumns = @JoinColumn(name = "devs_id"))
    protected Set<Devs> listOfDevsRegistered = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "listOfWatchedContents", fetch = FetchType.LAZY)
    protected Set<Devs> listOfDevsWhoWatched = new HashSet<>();

    public Set<Devs> getListOfDevsWhoWatched() {
        return listOfDevsWhoWatched;
    }

    public void setListOfDevsWhoWatched(Set<Devs> listOfDevsWhoWatched) {
        this.listOfDevsWhoWatched = listOfDevsWhoWatched;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getDataOfCreation() {
        return dataOfCreation;
    }

    public Set<Devs> getListOfDevsRegistered() {
        return listOfDevsRegistered;
    }


    // Não é para existir
    public String getUrl() {
        return null;
    }
}
