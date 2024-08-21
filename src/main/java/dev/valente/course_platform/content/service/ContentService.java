package dev.valente.course_platform.content.service;

import dev.valente.course_platform.content.Content;
import dev.valente.course_platform.content.dtos.BootcampResponseDTO;
import dev.valente.course_platform.content.dtos.ContentCreationRequestDTO;
import dev.valente.course_platform.content.dtos.ContentResponseDTO;
import dev.valente.course_platform.content.dtos.CreateBootcampDTO;
import dev.valente.course_platform.content.concreteContent.Bootcamp;
import dev.valente.course_platform.content.exceptions.ContentAlreadyExists;
import dev.valente.course_platform.content.exceptions.ContentNotFound;
import dev.valente.course_platform.content.factory.ContentFactory;
import dev.valente.course_platform.content.repository.ContentRepository;
import dev.valente.course_platform.devs.exceptions.DevNotFound;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        var contentIfExists = this.contentRepository.findByURL(content.url());
        if(contentIfExists.isEmpty()){

            var testeContent = ContentFactory.createContent(content);
            this.contentRepository.save(testeContent);
            return new ContentResponseDTO(testeContent);
        }

        throw new ContentAlreadyExists();
    }

    public BootcampResponseDTO createBootcamp(CreateBootcampDTO createBootcampDTO){

        Set<Content> listOfContents = new HashSet<>();

        int duration = 0;

        for (UUID id : createBootcampDTO.contentList()){
            var content = this.contentRepository.findById(id).orElseThrow(ContentNotFound::new);
            listOfContents.add(content);
        }

        for (Content content : listOfContents) {
            duration += content.getDuration();
        }

        var bootcamp = new Bootcamp(createBootcampDTO.description(),duration, listOfContents);
        this.contentRepository.save(bootcamp);

        return new BootcampResponseDTO(bootcamp);
    }



    public ContentResponseDTO deleteContent(UUID id){

        var contentToDelete = this.contentRepository.findById(id).orElseThrow(ContentNotFound::new);
        var contentResponseDTO = new ContentResponseDTO(contentToDelete);

        if(!contentToDelete.getListOfDevsRegistered().isEmpty()){
            this.contentRepository.deleteContent(id);
        }
        if(!contentToDelete.getListOfDevsWhoWatched().isEmpty()){
            this.contentRepository.deleteContentWatched(id);
        }

        this.contentRepository.delete(contentToDelete);
        return contentResponseDTO;

    }


    public ContentResponseDTO addContentRegistered(UUID idUser, UUID idContent){
        var contentResearched = this.contentRepository.findById(idContent).orElseThrow(ContentNotFound::new);

        var devResearched = this.devsRepository.findById(idUser).orElseThrow(DevNotFound::new);

        contentResearched.getListOfDevsRegistered().add(devResearched);
        devResearched.getListOfContentsRegistered().add(contentResearched);

        this.devsRepository.save(devResearched);
        this.contentRepository.save(contentResearched);

        return new ContentResponseDTO(contentResearched);
    }

    public ContentResponseDTO addContentWatched(UUID idContent, UUID idDev){
        var contentResearched = this.contentRepository.findById(idContent).orElseThrow(ContentNotFound::new);

        var devResearched = this.devsRepository.findById(idDev).orElseThrow(DevNotFound::new);

        contentResearched.getListOfDevsWhoWatched().add(devResearched);
        devResearched.getListOfWatchedContents().add(contentResearched);
        devResearched.setXp(devResearched.getXp() + 50);

        this.devsRepository.save(devResearched);
        this.contentRepository.save(contentResearched);

        return new ContentResponseDTO(contentResearched);
    }


}