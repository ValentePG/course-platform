package dev.valente.course_platform.devs.DTOs;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

public record DevsCreationRequestDTO(@NotEmpty String userName,
                                     @NotEmpty String password) implements Serializable {
}
