package dev.valente.course_platform.devs.repository;

import dev.valente.course_platform.devs.Devs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DevsRepository extends JpaRepository<Devs, UUID> {

    Optional<Devs> findDevsByUserName(String userName);
}