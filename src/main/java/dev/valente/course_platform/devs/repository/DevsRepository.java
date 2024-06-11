package dev.valente.course_platform.devs.repository;

import dev.valente.course_platform.devs.Devs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevsRepository extends JpaRepository<Devs, Long> {
}
