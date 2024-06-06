package dev.valente.course_platform.DTOs;

import dev.valente.course_platform.Devs;

public record DevsResponseDTO(Long id, String name, String password) {

    public DevsResponseDTO(Devs devs){
        this(devs.getId(), devs.getName(), devs.getPassword());
    }
}
