package dev.valente.course_platform.content.repository;

import dev.valente.course_platform.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
}
