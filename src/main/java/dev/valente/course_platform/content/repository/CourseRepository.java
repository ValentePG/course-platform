package dev.valente.course_platform.content.repository;

import dev.valente.course_platform.content.concreteContent.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
