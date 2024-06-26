package dev.valente.course_platform.content.repository;

import dev.valente.course_platform.content.concreteContent.mentoring.Mentoring;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MentoringRepository extends JpaRepository<Mentoring, UUID> {
}
