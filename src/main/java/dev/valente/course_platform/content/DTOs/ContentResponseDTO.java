package dev.valente.course_platform.content.DTOs;

import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.content.concreteContent.course.Course;
import dev.valente.course_platform.content.concreteContent.mentoring.Mentoring;
import dev.valente.course_platform.devs.Devs;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record ContentResponseDTO(UUID id, String description, Integer duration,
                                 Date date, Set<Devs> listOfDevs, String url) {

    public ContentResponseDTO(Course course){
        this(course.getId(), course.getDescription(), course.getDuration(),
                course.getDataOfCriation(), course.getListOfDevs(), course.getUrl());
    }

    public ContentResponseDTO(Mentoring mentoring){
        this(mentoring.getId(), mentoring.getDescription(), mentoring.getDuration(),
                mentoring.getDataOfCriation(), mentoring.getListOfDevs(), mentoring.getUrl());
    }
}
