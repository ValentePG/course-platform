package dev.valente.course_platform.content.concreteContent.bootcamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.valente.course_platform.content.Content;
import jakarta.persistence.*;


import java.util.*;

@Entity
public class Bootcamp extends Content{

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Content> listOfContent = new HashSet<>();

    public Bootcamp(){}
    public Bootcamp(String description, Integer duration, Date date) {
        super();
        this.description = description;
        this.duration = duration;
        this.dataOfCriation = date;

    }
//    public Set<Course> getListOfCourses() {
//        return listOfCourses;
//    }
//
//    public Set<Mentoring> getListOfMentoring() {
//        return listOfMentoring;
//    }

    public Set<Content> getListOfContent() {
        return listOfContent;
    }
}
