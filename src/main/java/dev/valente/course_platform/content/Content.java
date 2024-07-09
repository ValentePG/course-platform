package dev.valente.course_platform.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.valente.course_platform.devs.Devs;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "TB_CONTENT")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_CONTENT_DEVS",
               joinColumns = @JoinColumn(name = "content_id"),
               inverseJoinColumns = @JoinColumn(name = "devs_id"))
    protected Set<Devs> listOfDevs = new HashSet<>();

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

    public Date getDataOfCriation() {
        return dataOfCriation;
    }

    public Set<Devs> getListOfDevs() {
        return listOfDevs;
    }

    public String getUrl() {
        return null;
    }
}
