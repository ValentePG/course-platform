package dev.valente.course_platform.content.controller;


import dev.valente.course_platform.content.DTOs.ContentRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentResponseDTO;
import dev.valente.course_platform.content.service.ContentService;
import dev.valente.course_platform.devs.service.DevsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/content")
public class ContentController {

    ContentService contentService;

    public ContentController(ContentService contentService){
        this.contentService = contentService;
    }

    @PostMapping("registerContent")
    public ResponseEntity<ContentResponseDTO> createContent(@RequestBody @Valid ContentRequestDTO contentRequestDTO){

        return ResponseEntity.ok(this.contentService.createContent(contentRequestDTO));
    }
}
