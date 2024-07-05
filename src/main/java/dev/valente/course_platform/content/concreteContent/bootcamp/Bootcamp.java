package dev.valente.course_platform.content.concreteContent.bootcamp;

import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.content.concreteContent.course.Course;
import dev.valente.course_platform.content.concreteContent.mentoring.Mentoring;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Bootcamp extends Content{

    @ManyToMany
    private Set<Course> listOfCourses;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Mentoring> listOfMentoring;

    public Bootcamp(){}
    public Bootcamp(String description, Integer duration, Date date) {
        super();
        this.description = description;
        this.duration = duration;
        this.dataOfCriation = date;

    }
    public Set<Course> getListOfCourses() {
        return listOfCourses;
    }

    public Set<Mentoring> getListOfMentoring() {
        return listOfMentoring;
    }


}
