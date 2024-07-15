package dev.valente.course_platform.content.DTOs;

import dev.valente.course_platform.content.concreteContent.Bootcamp;
import dev.valente.course_platform.devs.DTOs.ContentIntoDevsDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record BootcampResponseDTO(UUID id, String description, Integer duration,
                                  Date date, List<DevsIntoContentDTO> listOfDevs,
                                  List<DevsIntoContentDTO> listOfDevsWhoWatched,
                                  List<ContentIntoDevsDTO> listOfContentRegistered) {

    public BootcampResponseDTO(Bootcamp bootcamp) {
        this(bootcamp.getId(), bootcamp.getDescription(), bootcamp.getDuration(), bootcamp.getDataOfCreation(),
                bootcamp.getListOfDevsRegistered().stream().map(t -> new DevsIntoContentDTO(t.getId(),
                        t.getUserName())).toList(),
                bootcamp.getListOfDevsWhoWatched().stream().map(t -> new DevsIntoContentDTO(t.getId(),
                        t.getUserName())).toList(),
                bootcamp.getListOfContents().stream().map(t -> new ContentIntoDevsDTO(t.getId())).toList());
    }
}
