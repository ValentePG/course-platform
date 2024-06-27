package dev.valente.course_platform.content.DTOs;


import dev.valente.course_platform.content.concreteContent.course.Course;
import dev.valente.course_platform.content.concreteContent.mentoring.Mentoring;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record ContentResponseDTO(UUID id, String description, Integer duration,
                                 Date date, List<DevsIntoContentDTO> listOfDevs, String url) {

    public ContentResponseDTO(Course course){
        this(course.getId(), course.getDescription(), course.getDuration(),
                course.getDataOfCriation(), course.getListOfDevs()
                        .stream().map(t -> new DevsIntoContentDTO(t.getId(),
                                t.getUserName())).toList(), course.getUrl());
    }

    public ContentResponseDTO(Mentoring mentoring){
        this(mentoring.getId(), mentoring.getDescription(), mentoring.getDuration(),
                mentoring.getDataOfCriation(), mentoring.getListOfDevs()
                        .stream().map(t -> new DevsIntoContentDTO(t.getId(),
                                t.getUserName())).toList(), mentoring.getUrl());
    }



}
