package dev.valente.course_platform.devs.service;

import dev.valente.course_platform.devs.DTOs.DevsRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DevsService {

    DevsRepository devsRepository;

    public DevsService(DevsRepository devsRepository){
        this.devsRepository = devsRepository;
    }

    public List<DevsResponseDTO> getAllDevs(){

        return this.devsRepository.findAll().stream().map(DevsResponseDTO::new).toList();
    }

    public void saveDev(DevsRequestDTO dev){
        Devs devsData = new Devs(dev);

        this.devsRepository.save(devsData);
    }

    public DevsResponseDTO findDevById(UUID id) throws Exception{

        Optional<Devs> devsSearched = this.devsRepository.findById(id);


        if(devsSearched.isEmpty()){
            throw new NullPointerException("Deu erro ai");
        }
        return new DevsResponseDTO(devsSearched.get());
    }



}
