package dev.valente.course_platform.devs.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DevsRenameDTO(@NotBlank @NotNull String userName) {
}
