package dev.valente.course_platform.content.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateBootcampDTO(@NotBlank @NotNull String description,
                                @NotNull Integer duration,
                                @NotBlank @NotNull List<UUID> contentList,
                                @NotNull Integer typeContent) {
}
