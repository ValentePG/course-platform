package dev.valente.course_platform.content.service;

import dev.valente.course_platform.content.DTOs.ContentCreationRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentResponseDTO;
import dev.valente.course_platform.content.concreteContent.course.Course;
import dev.valente.course_platform.content.concreteContent.mentoring.Mentoring;
import dev.valente.course_platform.content.repository.ContentRepository;
import dev.valente.course_platform.content.repository.CourseRepository;
import dev.valente.course_platform.content.repository.MentoringRepository;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContentService {

    ContentRepository contentRepository;
    DevsRepository devsRepository;
    CourseRepository courseRepository;
    MentoringRepository mentoringRepository;

    public ContentService(ContentRepository contentRepository,
                          DevsRepository devsRepository,
                          CourseRepository courseRepository,
                          MentoringRepository mentoringRepository){

        this.contentRepository = contentRepository;
        this.devsRepository = devsRepository;
        this.courseRepository = courseRepository;
        this.mentoringRepository = mentoringRepository;
    }

    public ContentResponseDTO createContent(ContentCreationRequestDTO content){

        //Abstract Factory ou Method Factory encaixa aqui!
        try {
            if (content.duration() <= 30) {
                Course contentToCreate = new Course(content.description(),
                        content.duration(),
                        new Date(),
                        content.url());

                this.contentRepository.save(contentToCreate);
                return new ContentResponseDTO(contentToCreate);

            } else {
                Mentoring contentToCreate = new Mentoring(content.description(),
                        content.duration(),
                        new Date(),
                        content.url());

                this.contentRepository.save(contentToCreate);
                return new ContentResponseDTO(contentToCreate);
            }
        } catch (Exception error) {
            error.getMessage();
            return null;
        }


    }

    public List<ContentResponseDTO> getAllCourses(){

        return this.courseRepository.findAll().stream().map(ContentResponseDTO::new).toList();
    }

    public void addContentIntoDev(ContentRequestDTO idUser, UUID idContent){
        Optional<Course> teste = this.courseRepository.findById(idContent);

        Course teste1 = teste.get();

        Optional<Devs> testeDev = this.devsRepository.findById(idUser.id());

        Devs testeDev1 = testeDev.get();

        teste1.getListOfDevs().add(testeDev1);
        testeDev1.getContents().add(teste1);

        this.devsRepository.save(testeDev1);
        this.courseRepository.save(teste1);

    }
}
