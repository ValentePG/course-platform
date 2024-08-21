package dev.valente.course_platform.content.repository;

import dev.valente.course_platform.content.concrete_content.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BootcampRepository extends JpaRepository<Bootcamp, UUID> {
}
