package dev.valente.course_platform.devs.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record DevsCreationRequestDTO(@NotBlank @NotNull String userName,
                                     @NotBlank @NotNull String password) implements Serializable {
}
