package dev.valente.course_platform.content.controller;

import dev.valente.course_platform.content.DTOs.ContentCreationRequestDTO;
import dev.valente.course_platform.content.DTOs.ContentResponseDTO;
import dev.valente.course_platform.content.service.ContentService;
import dev.valente.course_platform.infra.RestErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Buscar lista de conteúdo")
    @ApiResponse(responseCode = "200",
                description = "Retorna uma lista vazia ou com os conteúdos registrados")
    @GetMapping
    public ResponseEntity<List<ContentResponseDTO>> listAllContents(){

        return ResponseEntity.ok(this.contentService.getAllContents());
    }

    @Operation(summary = "Criar conteúdo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",
                    description = "Retorna o conteúdo criado"),
        @ApiResponse(responseCode = "400",
                    description = "Quando o conteúdo ja existe, retorna uma BAD REQUEST CODE 400",
                    content = @Content(schema = @Schema(implementation = RestErrorMessage.class)))})
    @PostMapping
    public ResponseEntity<ContentResponseDTO> createContent(@RequestBody @Valid ContentCreationRequestDTO contentCreationRequestDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(this.contentService.createContent(contentCreationRequestDTO));
    }

    @Operation(summary = "Deletar Conteúdo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                        description = "Retorna o conteúdo excluído"),
            @ApiResponse(responseCode = "404",
                        description = "Quando o conteúdo a ser deletado não é encontrado, retorna um NOT FOUND CODE 404",
                        content = @Content(schema = @Schema(implementation = RestErrorMessage.class)))})
    @DeleteMapping("/id")
    public ResponseEntity<ContentResponseDTO> deleteContent(@RequestParam UUID id){

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.contentService.deleteContent(id));
    }

    @Operation(summary = "Registra um conteúdo à um desenvolvedor e vice-versa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna o conteúdo que foi registrado"),
            @ApiResponse(responseCode = "404",
                    description = "Se não encontrar algum dos recursos necessários, retorna um NOT FOUND CODE 404",
                    content = @Content(schema = @Schema(implementation = RestErrorMessage.class)))})
    @PutMapping("/{idContent}/devs")
    public ResponseEntity<ContentResponseDTO> addContentRegistered(@PathVariable("idContent") UUID idContent,
                                                                   @RequestParam UUID idDev){
        return ResponseEntity.ok(this.contentService.addContentRegistered(idDev, idContent));
    }

    @Operation(summary = "Registra um conteúdo que ja foi assistido pelo desenvolvedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna o conteúdo que foi assistido"),
            @ApiResponse(responseCode = "404",
                    description = "Se não encontrar algum dos recursos necessários, retorna um NOT FOUND CODE 404",
            content = @Content(schema = @Schema(implementation = RestErrorMessage.class)))})
    @PutMapping("/{idDev}/contents")
    public ResponseEntity<ContentResponseDTO> addContentWatched(@PathVariable("idDev") UUID idDev,
                                                                @RequestParam UUID idContent){
        return ResponseEntity.ok(this.contentService.addContentWatched(idContent, idDev));
    }
}
