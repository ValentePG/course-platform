package dev.valente.course_platform.devs.controller;

import dev.valente.course_platform.devs.DTOs.DevsRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("devs")
public class DevsController {

    DevsRepository devsRepository;

    public DevsController(DevsRepository devsRepository){
        this.devsRepository = devsRepository;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<DevsResponseDTO> getAllDevs(){

        List<DevsResponseDTO> devsList = this.devsRepository.findAll().stream().map(DevsResponseDTO::new).toList();

        return devsList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveDev(@RequestBody DevsRequestDTO dev){

        Devs devsData = new Devs(dev);

        this.devsRepository.save(devsData);
    }
}
