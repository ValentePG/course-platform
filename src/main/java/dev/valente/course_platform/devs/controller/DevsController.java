package dev.valente.course_platform.devs.controller;

import dev.valente.course_platform.devs.DTOs.DevsRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.service.DevsService;
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
    public List<DevsResponseDTO> getAllDevs(){
        return this.devsService.getAllDevs();
    }

    @GetMapping("/byid/{id}")
    public DevsResponseDTO findDevById(@PathVariable(name = "id") UUID id){

        return this.devsService.findDevById(id);

    }

    @GetMapping("/byusername/{userName}")
    public DevsResponseDTO findDevByUserName(@PathVariable(name = "userName") String userName){

        return this.devsService.findDevsByUserName(userName);

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity<String> saveDev(@RequestBody DevsRequestDTO dev){

        this.devsService.saveDev(dev);
        return ResponseEntity.ok("Usuário Criado");

    }
}
