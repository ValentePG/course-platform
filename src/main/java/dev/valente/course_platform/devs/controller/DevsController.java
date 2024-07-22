package dev.valente.course_platform.devs.controller;

import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsRenameDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.service.DevsService;
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
@RequestMapping("api/devs")
public class DevsController {

    DevsService devsService;

    public DevsController(DevsService devsService){
        this.devsService = devsService;
    }

    @Operation(summary = "Buscar lista de desenvolvedores")
    @ApiResponse(responseCode = "200", description = "Retorna uma lista vazia ou com os devs registrados")
    @GetMapping
    public ResponseEntity<List<DevsResponseDTO>> getAllDevs(){
        return ResponseEntity.ok(this.devsService.getAllDevs());
    }

    @Operation(summary = "Buscar um desenvolvedor pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna o desenvolvedor pesquisado"),
            @ApiResponse(responseCode = "404",
                    description = "Quando não encontrar o desenvolvedor, retorna um NOT FOUND 404",
                    content = @Content(schema = @Schema(implementation = RestErrorMessage.class)))})
    @GetMapping("id")
    public ResponseEntity<DevsResponseDTO> findDevById(@RequestParam(name = "id") UUID id){

        return ResponseEntity.ok(this.devsService.findDevById(id));

    }

    @Operation(summary = "Buscar um desenvolvedor pelo nome de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna o desenvolvedor pesquisado"),
            @ApiResponse(responseCode = "404",
                    description = "Quando não encontrar o desenvolvedor, retorna um NOT FOUND 404",
                    content = @Content(schema = @Schema(implementation = RestErrorMessage.class)))})
    @GetMapping("username")
    public ResponseEntity<DevsResponseDTO> findDevByUserName(@RequestParam(name = "userName") String userName){

        return ResponseEntity.ok(this.devsService.findDevByUserName(userName.toUpperCase()));

    }

    @Operation(summary = "Criar um novo desenvolvedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Retorna o desenvolvedor criado"),
            @ApiResponse(responseCode = "400",
                    description = "Quando o usuário já existir, retorna uma BAD REQUEST 400",
                    content = @Content(schema = @Schema(implementation = RestErrorMessage.class)))})
    @PostMapping
    public ResponseEntity<DevsResponseDTO> saveDev(@RequestBody @Valid DevsCreationRequestDTO dev){

        return ResponseEntity.status(HttpStatus.CREATED).body(this.devsService.saveDev(dev));

    }


    @Operation(summary = "Deletar um desenvolvedor pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Retorna o desenvolvedor deletado"),
            @ApiResponse(responseCode = "404",
                    description = "Quando não encontrar o desenvolvedor, retorna um NOT FOUND 404",
                    content = @Content(schema = @Schema(implementation = RestErrorMessage.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<DevsResponseDTO> deleteDev(@PathVariable("id") UUID id){


        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.devsService.deleteDev(id));
    }


    @Operation(summary = "Renomear desenvolvedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Retorna o desenvolvedor com username renomeado"),
            @ApiResponse(responseCode = "404",
                    description = "Quando não encontrar o desenvolvedor, retorna um NOT FOUND 404",
                    content = @Content(schema = @Schema(implementation = RestErrorMessage.class)))})
    @PutMapping("/{userName}")
    public ResponseEntity<DevsResponseDTO> renameDev(@PathVariable("userName") String devToRename,
                                                     @RequestBody @Valid DevsRenameDTO userName){


        return ResponseEntity.ok(this.devsService.renameDev(devToRename, userName));
    }


}
