package dev.valente.course_platform.devs.controller;

import dev.valente.course_platform.devs.DTOs.DevsRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.service.DevsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @GetMapping("/byid/{id}")
    public ResponseEntity<DevsResponseDTO> findDevById(@PathVariable(name = "id") UUID id){

        return ResponseEntity.ok(this.devsService.findDevById(id));

    }

    @GetMapping("/byusername/{userName}")
    public ResponseEntity<DevsResponseDTO> findDevByUserName(@PathVariable(name = "userName") String userName){

        return ResponseEntity.ok(this.devsService.findDevByUserName(userName));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity<DevsResponseDTO> saveDev(@RequestBody @Valid DevsRequestDTO dev){

        return ResponseEntity.status(HttpStatus.CREATED).body(this.devsService.saveDev(dev));

    }
}
