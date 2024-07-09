package dev.valente.course_platform.content.DTOs;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ContentRequestDTO(@NotNull UUID id) {
}
