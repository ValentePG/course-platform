package dev.valente.course_platform.content.DTOs;

import dev.valente.course_platform.content.Content;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateBootcampDTO(@NotBlank @NotNull String description,
                                @NotNull Integer duration,
                                @NotBlank @NotNull List<Content> contentList,
                                @NotNull Integer typeContent) {
}
