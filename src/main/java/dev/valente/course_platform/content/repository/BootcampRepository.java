package dev.valente.course_platform.content.repository;

import dev.valente.course_platform.content.concreteContent.bootcamp.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BootcampRepository extends JpaRepository<Bootcamp, UUID> {
}
