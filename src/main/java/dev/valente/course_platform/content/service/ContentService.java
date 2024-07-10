package dev.valente.course_platform.content.service;

import dev.valente.course_platform.content.DTOs.ContentCreationRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentResponseDTO;
import dev.valente.course_platform.content.exceptions.ContentNotFound;
import dev.valente.course_platform.content.factory.ContentFactory;
import dev.valente.course_platform.content.repository.ContentRepository;
import dev.valente.course_platform.devs.exceptions.DevNotFound;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

                                           // Method Factory
        var testeContent = ContentFactory.createContent(content);
        this.contentRepository.save(testeContent);
        return new ContentResponseDTO(testeContent);

    }

    public ContentResponseDTO deleteContent(UUID id){

        var contentToDelete = this.contentRepository.findById(id).orElseThrow(ContentNotFound::new);
        var contentResponseDTO = new ContentResponseDTO(contentToDelete);
        this.contentRepository.delete(contentToDelete);
        return contentResponseDTO;

    }


    public void addContentIntoDev(UUID idUser, UUID idContent){
        var contentResearched = this.contentRepository.findById(idContent).orElseThrow(ContentNotFound::new);

        var devResearched = this.devsRepository.findById(idUser).orElseThrow(DevNotFound::new);

        contentResearched.getListOfDevs().add(devResearched);
        devResearched.getListOfContents().add(contentResearched);

        this.devsRepository.save(devResearched);
        this.contentRepository.save(contentResearched);
    }
}