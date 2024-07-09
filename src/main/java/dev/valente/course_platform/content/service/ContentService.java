package dev.valente.course_platform.content.service;

import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.content.DTOs.ContentCreationRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentResponseDTO;
import dev.valente.course_platform.content.concreteContent.course.Course;
import dev.valente.course_platform.content.factory.ContentFactory;
import dev.valente.course_platform.content.repository.ContentRepository;
import dev.valente.course_platform.content.repository.CourseRepository;
import dev.valente.course_platform.content.repository.MentoringRepository;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContentService {

    ContentRepository contentRepository;
    DevsRepository devsRepository;

    public ContentService(ContentRepository contentRepository,
                          DevsRepository devsRepository){

        this.contentRepository = contentRepository;
        this.devsRepository = devsRepository;
    }

    public List<ContentResponseDTO> getAllContents(){

        return this.contentRepository.findAll().stream().map(ContentResponseDTO::new).toList();
    }

    public ContentResponseDTO createContent(ContentCreationRequestDTO content){

        //Abstract Factory ou Method Factory encaixa aqui!
        Content testeContent = ContentFactory.createContent(content);
        this.contentRepository.save(testeContent);
        return new ContentResponseDTO(testeContent);

    }

    public ContentResponseDTO deleteContent(UUID id){

        Content contentToDelete = this.contentRepository.findById(id).orElseThrow(RuntimeException::new);
        var contentResponseDTO = new ContentResponseDTO(contentToDelete);
        this.contentRepository.delete(contentToDelete);
        return contentResponseDTO;

    }


    public void addContentIntoDev(ContentRequestDTO idUser, UUID idContent){
        Optional<Content> teste = this.contentRepository.findById(idContent);

        var teste1 = teste.get();

        Optional<Devs> testeDev = this.devsRepository.findById(idUser.id());

        Devs testeDev1 = testeDev.get();

        UUID testeDev1ID = testeDev1.getId();

        teste1.getListOfDevs().add(testeDev1);
        testeDev1.getListOfContents().add(teste1);

        this.devsRepository.save(testeDev1);
        this.contentRepository.save(teste1);
    }
}