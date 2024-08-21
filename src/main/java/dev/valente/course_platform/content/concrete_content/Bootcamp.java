package dev.valente.course_platform.content.concrete_content;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.valente.course_platform.content.Content;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Bootcamp extends Content{

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Content> listOfContents = new HashSet<>();

    public Bootcamp(){}
    public Bootcamp(String description, Integer duration, Set<Content> listOfContents) {
        super();
        this.description = description;
        this.duration = duration;
        this.dataOfCreation = LocalDate.now();
        this.listOfContents = listOfContents;
    }

    public Set<Content> getListOfContents() {
        return listOfContents;
    }
}
