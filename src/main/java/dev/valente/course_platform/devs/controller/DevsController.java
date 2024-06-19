package dev.valente.course_platform.devs.controller;

import dev.valente.course_platform.devs.DTOs.DevsRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.repository.DevsRepository;
import dev.valente.course_platform.devs.service.DevsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("/{id}")
    public DevsResponseDTO findDevById(@PathVariable(name = "id") UUID id) throws Exception {
        return this.devsService.findDevById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveDev(@RequestBody DevsRequestDTO dev){

        this.devsService.saveDev(dev);
    }
}
