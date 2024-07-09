package dev.valente.course_platform.content.concreteContent.mentoring;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.content.concreteContent.bootcamp.Bootcamp;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
public class Mentoring extends Content {

    @Column(unique = true)
    private String url;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Bootcamp> listOfBootcamp = new HashSet<>();

    public Mentoring(){

    }

    public Mentoring(String description, Integer duration, Date date, String url) {
        super();
        this.description = description;
        this.duration = duration;
        this.dataOfCriation = date;
        this.url = url;
    }

    public Set<Bootcamp> getListOfBootcamp() {
        return listOfBootcamp;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

