package dev.valente.course_platform.devs.DTOs;

import dev.valente.course_platform.devs.Devs;

import java.util.List;
import java.util.UUID;

public record DevsResponseDTO(UUID id, String userName, String password, List<ContentIntoDevsDTO> listOfContent) {

    public DevsResponseDTO(Devs devs){
        this(devs.getId(), devs.getUserName(), devs.getPassword(),
                devs.getContents().stream().map(t -> new ContentIntoDevsDTO(t.getId(), t.getDescription())).toList());
    }


}
