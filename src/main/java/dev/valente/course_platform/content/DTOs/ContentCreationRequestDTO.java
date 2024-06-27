package dev.valente.course_platform.content.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContentCreationRequestDTO(@NotBlank @NotNull String description,
                                        @NotNull Integer duration,
                                        @NotBlank @NotNull String url,
                                        @NotNull Integer typeContent) {
}
