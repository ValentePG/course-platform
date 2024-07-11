package dev.valente.course_platform.content.repository;

import dev.valente.course_platform.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_CONTENT_DEVS WHERE DEVS_ID = :id", nativeQuery = true)
    void deleteContent(@Param("id") UUID id);

    @Query(value = "SELECT * FROM TB_CONTENT WHERE URL = :url", nativeQuery = true)
    Optional<Content> findByURL(@Param("url") String url);
}
