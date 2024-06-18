package dev.valente.course_platform.content.concreteContent.course;

import dev.valente.course_platform.content.Content;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "TB_COURSE")
@Entity
public class Course extends Content {

    private String url;

    public Course(){

    }
}
