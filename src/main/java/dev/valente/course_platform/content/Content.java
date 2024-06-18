package dev.valente.course_platform.content;

import dev.valente.course_platform.devs.Devs;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "TB_CONTENT")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Content {

    @Id
    @GeneratedValue
    protected UUID id;

    @Column(nullable = false)
    protected String description;

    @Column(nullable = false)
    protected int Duration;

    @Column(nullable = false)
    protected Date dataOfCriation;

    @ManyToMany
    @JoinTable(name = "TB_CONTENT_DEVS",
               joinColumns = @JoinColumn(name = "content_id"),
               inverseJoinColumns = @JoinColumn(name = "devs_id"))
    protected Set<Devs> listOfDevs = new HashSet<>();
}
