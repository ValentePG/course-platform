package dev.valente.course_platform.content.concrete_content;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.valente.course_platform.content.Content;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Mentoring extends Content {

    @Column
    private String url;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Bootcamp> listOfBootcamp = new HashSet<>();

    public Mentoring(){}
    public Mentoring(String description, Integer duration, String url) {
        super();
        this.description = description;
        this.duration = duration;
        this.dataOfCreation = LocalDate.now();
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

