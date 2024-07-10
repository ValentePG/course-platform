package dev.valente.course_platform.content.controller;

import dev.valente.course_platform.content.DTOs.ContentCreationRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentResponseDTO;
import dev.valente.course_platform.content.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<List<ContentResponseDTO>> listAllContents(){

        return ResponseEntity.ok(this.contentService.getAllContents());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<ContentResponseDTO> createContent(@RequestBody @Valid ContentCreationRequestDTO contentCreationRequestDTO){

        return ResponseEntity.ok(this.contentService.createContent(contentCreationRequestDTO));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/id")
    public ResponseEntity<ContentResponseDTO> createContent(@RequestParam UUID id){

        return ResponseEntity.ok(this.contentService.deleteContent(id));
    }




    //Talvez seja melhor criar isto em outro local
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public void registerDevIntoContent(@PathVariable("id") UUID idContent,
                                       @RequestBody @Valid ContentRequestDTO idUser){
        this.contentService.addContentIntoDev(idUser, idContent);
    }
}
