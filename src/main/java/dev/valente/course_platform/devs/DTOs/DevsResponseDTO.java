package dev.valente.course_platform.devs.DTOs;

import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.devs.Devs;
import java.util.Set;
import java.util.UUID;

public record DevsResponseDTO(UUID id, String userName, String password, Set<Content> listOfContent) {

    public DevsResponseDTO(Devs devs){
        this(devs.getId(), devs.getUserName(), devs.getPassword(), devs.getContents());
    }
}
