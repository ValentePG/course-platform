package dev.valente.course_platform.devs.controller;

import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsRenameDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.service.DevsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/devs")
public class DevsController {

    DevsService devsService;

    public DevsController(DevsService devsService){
        this.devsService = devsService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<DevsResponseDTO>> getAllDevs(){
        return ResponseEntity.ok(this.devsService.getAllDevs());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<DevsResponseDTO> findDevById(@PathVariable(name = "id") UUID id){

        return ResponseEntity.ok(this.devsService.findDevById(id));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/findbyusername/{userName}")
    public ResponseEntity<DevsResponseDTO> findDevByUserName(@PathVariable(name = "userName") String userName){

        return ResponseEntity.ok(this.devsService.findDevByUserName(userName.toUpperCase()));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity<DevsResponseDTO> saveDev(@RequestBody @Valid DevsCreationRequestDTO dev){

        return ResponseEntity.status(HttpStatus.CREATED).body(this.devsService.saveDev(dev));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/deleteuserbyid/{id}")
    public ResponseEntity<DevsResponseDTO> deleteDev(@PathVariable("id") UUID id){


        return ResponseEntity.ok(this.devsService.deleteDev(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/renameuserbyname/{userName}")
    public ResponseEntity<DevsResponseDTO> renameDev(@PathVariable("userName") String devToRename,
                                                     @RequestBody @Valid DevsRenameDTO userName){


        return ResponseEntity.ok(this.devsService.renameDev(devToRename, userName));
    }


}
