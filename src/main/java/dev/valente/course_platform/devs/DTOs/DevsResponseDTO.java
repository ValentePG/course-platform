package dev.valente.course_platform.devs.DTOs;

import dev.valente.course_platform.devs.Devs;

import java.util.UUID;

public record DevsResponseDTO(UUID id, String name, String password) {

    public DevsResponseDTO(Devs devs){
        this(devs.getId(), devs.getName(), devs.getPassword());
    }
}
