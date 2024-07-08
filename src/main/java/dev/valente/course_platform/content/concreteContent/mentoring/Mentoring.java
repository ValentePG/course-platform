package dev.valente.course_platform.content.concreteContent.mentoring;

import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.content.concreteContent.bootcamp.Bootcamp;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Mentoring extends Content {

    private String url;


    @ManyToMany(fetch = FetchType.LAZY)
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

