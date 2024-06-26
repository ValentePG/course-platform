package dev.valente.course_platform.content.service;

import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.content.DTOs.ContentRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentResponseDTO;
import dev.valente.course_platform.content.concreteContent.course.Course;
import dev.valente.course_platform.content.concreteContent.mentoring.Mentoring;
import dev.valente.course_platform.content.repository.ContentRepository;
import dev.valente.course_platform.content.repository.CourseRepository;
import dev.valente.course_platform.content.repository.MentoringRepository;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.exceptions.UserNotCreated;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentService {

    ContentRepository contentRepository;
    DevsRepository devsRepository;


    public ContentService(ContentRepository contentRepository, DevsRepository devsRepository){

        this.contentRepository = contentRepository;
        this.devsRepository = devsRepository;
    }

    public ContentResponseDTO createContent(ContentRequestDTO content){


        if(content.typeContent() == 1){
            Course contentToCreate = new Course(content.description(),
                    content.duration(),
                    new Date(),
                    content.url());

            this.contentRepository.save(contentToCreate);
            return new ContentResponseDTO(contentToCreate);

        } else if (content.typeContent() == 2) {

            Mentoring contentToCreate = new Mentoring(content.description(),
                    content.duration(),
                    new Date(),
                    content.url());

            this.contentRepository.save(contentToCreate);
            return new ContentResponseDTO(contentToCreate);
        }

        throw new UserNotCreated();
    }
}
