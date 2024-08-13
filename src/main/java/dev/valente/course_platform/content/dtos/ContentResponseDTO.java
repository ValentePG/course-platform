package dev.valente.course_platform.content.dtos;

import dev.valente.course_platform.content.Content;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ContentResponseDTO(UUID id, String description, Integer duration,
                                 LocalDate date, List<DevsIntoContentDTO> listOfDevs,
                                 List<DevsIntoContentDTO> listOfDevsWhoWatched, String url) {

    public ContentResponseDTO(Content content){
        this(content.getId(), content.getDescription(), content.getDuration(),
                content.getDataOfCreation(), content.getListOfDevsRegistered()
                        .stream().map(t -> new DevsIntoContentDTO(t.getId(),
                                t.getUserName())).toList(),content.getListOfDevsWhoWatched()
                        .stream().map(t -> new DevsIntoContentDTO(t.getId(),
                                t.getUserName())).toList() ,content.getUrl());


    }

}
