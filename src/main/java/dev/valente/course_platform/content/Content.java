package dev.valente.course_platform.content;

import dev.valente.course_platform.devs.Devs;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "TB_CONTENT")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Content {

    @Id
    @GeneratedValue
    protected UUID id;

    @Column
    protected String description;

    @Column
    protected Integer duration;

    @Column
    protected Date dataOfCriation;

    @ManyToMany
    @JoinTable(name = "TB_CONTENT_DEVS",
               joinColumns = @JoinColumn(name = "content_id"),
               inverseJoinColumns = @JoinColumn(name = "devs_id"))
    protected Set<Devs> listOfDevs = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Date getDataOfCriation() {
        return dataOfCriation;
    }

    public void setDataOfCriation(Date dataOfCriation) {
        this.dataOfCriation = dataOfCriation;
    }

    public Set<Devs> getListOfDevs() {
        return listOfDevs;
    }

    public void setListOfDevs(Set<Devs> listOfDevs) {
        this.listOfDevs = listOfDevs;
    }
}
