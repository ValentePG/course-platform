package dev.valente.course_platform.content.concreteContent.mentoring;

import dev.valente.course_platform.content.Content;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

//@Table(name = "TB_MENTORING")
@Entity
public class Mentoring extends Content {

    private String url;

    public Mentoring(){

    }

    public Mentoring(String description, Integer duration, Date date, String url) {
        super();
        this.description = description;
        this.duration = duration;
        this.dataOfCriation = date;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

