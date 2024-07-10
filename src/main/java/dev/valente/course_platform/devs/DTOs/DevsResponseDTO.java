package dev.valente.course_platform.devs.DTOs;

import dev.valente.course_platform.devs.Devs;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
                                                        // Poderia ser uma lista só de UUID
public record DevsResponseDTO(UUID id, String userName, List<ContentIntoDevsDTO> listOfContent, BigDecimal XP) {

    public DevsResponseDTO(Devs devs){
        this(devs.getId(), devs.getUserName(),
                devs.getListOfContents().stream().map(t -> new ContentIntoDevsDTO(t.getId())).toList(), devs.getXP());
    }


}
