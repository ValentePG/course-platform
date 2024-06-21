package dev.valente.course_platform.devs.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DevsRequestDTO(@NotBlank @NotNull String userName, @NotBlank @NotNull String password) {
}
