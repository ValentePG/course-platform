package dev.valente.course_platform.content.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record CreateBootcampDTO(@NotBlank @NotNull String description,
                                @NotNull @Size(min = 2,
                                        message = "Um bootcamp só pode ser registrado com 2 ou mais conteúdos!")
                                List<UUID> contentList) {
}
