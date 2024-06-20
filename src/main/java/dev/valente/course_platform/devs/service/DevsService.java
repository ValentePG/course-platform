package dev.valente.course_platform.devs.service;

import dev.valente.course_platform.devs.DTOs.DevsRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.exceptions.UserNotFound;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.apache.catalina.User;
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


    public DevsResponseDTO findDevById(UUID id){

        Devs devsSearched = this.devsRepository.findById(id).orElseThrow(
                UserNotFound::new);

        return new DevsResponseDTO(devsSearched);
    }

    public DevsResponseDTO findDevsByUserName(String userName){

        Devs devsSearched = this.devsRepository.findDevsByUserName(userName).orElseThrow(UserNotFound::new);

        return new DevsResponseDTO(devsSearched);
    }

    public void saveDev(DevsRequestDTO dev){

        Devs requestDevData = new Devs(dev);

        Optional<Devs> devCreated = this.devsRepository.findDevsByUserName(requestDevData.getUserName());

        if(devCreated.isEmpty()){
            this.devsRepository.save(requestDevData);
        } else {
            throw new UserNameAlreadyExists();
        }
    }

}
