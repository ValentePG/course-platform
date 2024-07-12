package dev.valente.course_platform.content.concreteContent;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.valente.course_platform.content.Content;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course extends Content {

    @Column
    private String url;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Bootcamp> listOfBootcamp = new HashSet<>();

    public Course(){}
    public Course(String description, Integer duration, Date date, String url) {
        super();
        this.description = description;
        this.duration = duration;
        this.dataOfCreation = date;
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

