package dev.valente.course_platform.content.controller;

import dev.valente.course_platform.content.DTOs.ContentCreationRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentResponseDTO;
import dev.valente.course_platform.content.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/contents")
public class ContentController {

    ContentService contentService;

    public ContentController(ContentService contentService){
        this.contentService = contentService;
    }


    @GetMapping
    public ResponseEntity<List<ContentResponseDTO>> listAllContents(){

        return ResponseEntity.ok(this.contentService.getAllContents());
    }

    @PostMapping
    public ResponseEntity<ContentResponseDTO> createContent(@RequestBody @Valid ContentCreationRequestDTO contentCreationRequestDTO){

        return ResponseEntity.ok(this.contentService.createContent(contentCreationRequestDTO));
    }

    @DeleteMapping("/id")
    public ResponseEntity<ContentResponseDTO> createContent(@RequestParam UUID id){

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.contentService.deleteContent(id));
    }

    @PutMapping("/{id}/devs")
    public ResponseEntity<ContentResponseDTO> registerDevIntoContent(@PathVariable("id") UUID idContent,
                                       @RequestParam UUID idDev){
        return ResponseEntity.ok(this.contentService.addContentIntoDev(idDev, idContent));
    }
}
