package dev.valente.course_platform.devs.controller;

import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsRenameDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.service.DevsService;
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

    @GetMapping
    public ResponseEntity<List<DevsResponseDTO>> getAllDevs(){
        return ResponseEntity.ok(this.devsService.getAllDevs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevsResponseDTO> findDevById(@PathVariable(name = "id") UUID id){

        return ResponseEntity.ok(this.devsService.findDevById(id));

    }

    @GetMapping("/{userName}")
    public ResponseEntity<DevsResponseDTO> findDevByUserName(@PathVariable(name = "userName") String userName){

        return ResponseEntity.ok(this.devsService.findDevByUserName(userName.toUpperCase()));

    }

    @PostMapping
    public ResponseEntity<DevsResponseDTO> saveDev(@RequestBody @Valid DevsCreationRequestDTO dev){

        return ResponseEntity.status(HttpStatus.CREATED).body(this.devsService.saveDev(dev));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<DevsResponseDTO> deleteDev(@PathVariable("id") UUID id){


        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.devsService.deleteDev(id));
    }


    @PutMapping("/{userName}")
    public ResponseEntity<DevsResponseDTO> renameDev(@PathVariable("userName") String devToRename,
                                                     @RequestBody @Valid DevsRenameDTO userName){


        return ResponseEntity.ok(this.devsService.renameDev(devToRename, userName));
    }


}
