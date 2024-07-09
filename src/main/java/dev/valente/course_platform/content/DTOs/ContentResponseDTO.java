package dev.valente.course_platform.content.DTOs;

import dev.valente.course_platform.content.Content;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record ContentResponseDTO(UUID id, String description, Integer duration,
                                 Date date, List<DevsIntoContentDTO> listOfDevs, String url) {

    public ContentResponseDTO(Content content){
        this(content.getId(), content.getDescription(), content.getDuration(),
                content.getDataOfCriation(), content.getListOfDevs()
                        .stream().map(t -> new DevsIntoContentDTO(t.getId(),
                                t.getUserName())).toList(), content.getUrl());


    }

}
