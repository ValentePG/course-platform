package dev.valente.course_platform.content.concreteContent.mentoring;

import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.content.concreteContent.bootcamp.Bootcamp;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;


@Entity
public class Mentoring extends Content {

    private String url;


    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Bootcamp> bootcamp;

    public Mentoring(){

    }

    public Mentoring(String description, Integer duration, Date date, String url) {
        super();
        this.description = description;
        this.duration = duration;
        this.dataOfCriation = date;
        this.url = url;
    }

    public Set<Bootcamp> getBootcamp() {
        return bootcamp;
    }

    public void setBootcamp(Set<Bootcamp> bootcamp) {
        this.bootcamp = bootcamp;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

