package dev.valente.course_platform.content.concreteContent.mentoring;

import dev.valente.course_platform.content.Content;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "TB_MENTORING")
@Entity
public class Mentoring extends Content {

    private String url;

    public Mentoring(){

    }

}

