package dev.valente.course_platform.devs.DTOs;

import dev.valente.course_platform.devs.Devs;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record DevsResponseDTO(UUID id, String userName, List<ContentIntoDevsDTO> listOfContent,
                              List<ContentIntoDevsDTO> listOfContentWatched, Double XP) implements Serializable {

    public DevsResponseDTO(Devs devs){
        this(devs.getId(), devs.getUserName(),
                devs.getListOfContentsRegistered().stream().map(t -> new ContentIntoDevsDTO(t.getId())).toList(),
                devs.getListOfWatchedContents().stream().map(t -> new ContentIntoDevsDTO(t.getId())).toList() ,
                devs.getXP());
    }

}
