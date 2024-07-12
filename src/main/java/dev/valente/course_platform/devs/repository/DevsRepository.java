package dev.valente.course_platform.devs.repository;

import dev.valente.course_platform.devs.Devs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface DevsRepository extends JpaRepository<Devs, UUID> {

    Optional<Devs> findDevsByUserName(String userName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_CONTENT_DEVS WHERE DEVS_ID = :id", nativeQuery = true)
    void deleteContentRegistered(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_WATCHED_CONTENT WHERE DEVS_ID = :id", nativeQuery = true)
    void deleteContentWatched(@Param("id") UUID id);
}