package dev.valente.course_platform.content.controller;

import dev.valente.course_platform.content.DTOs.ContentCreationRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentResponseDTO;
import dev.valente.course_platform.content.service.ContentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/content")
public class ContentController {

    ContentService contentService;

    public ContentController(ContentService contentService){
        this.contentService = contentService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ContentResponseDTO> listAllCourses(){
        return this.contentService.getAllCourses();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("registercontent")
    public ResponseEntity<ContentResponseDTO> createContent(@RequestBody @Valid ContentCreationRequestDTO contentCreationRequestDTO){

        return ResponseEntity.ok(this.contentService.createContent(contentCreationRequestDTO));
    }


    //Talvez seja melhor criar isto em outro local
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("registerdevintocontent/{id}")
    public void registerDevIntoContent(@PathVariable("id") UUID idContent,
                                       @RequestBody @Valid ContentRequestDTO idUser){
        this.contentService.addContentIntoDev(idUser, idContent);
    }
}
