package dev.valente.course_platform.devs.service;

import dev.valente.course_platform.devs.DTOs.DevsCreationDTO;
import dev.valente.course_platform.devs.DTOs.DevsRenameDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.exceptions.UserNotFound;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DevsService {

    DevsRepository devsRepository;

    public DevsService(DevsRepository devsRepository) {
        this.devsRepository = devsRepository;
    }

    public List<DevsResponseDTO> getAllDevs() {

        return this.devsRepository.findAll().stream().map(
                DevsResponseDTO::new).toList();
    }


    public DevsResponseDTO findDevById(UUID id) {

        Devs devsSearched = this.devsRepository.findById(id).orElseThrow(
                UserNotFound::new);

        return new DevsResponseDTO(devsSearched);
    }

    public DevsResponseDTO findDevByUserName(String userName) {

        Devs devsSearched = this.devsRepository.findDevsByUserName(userName.toUpperCase()).orElseThrow(
                UserNotFound::new);

        return new DevsResponseDTO(devsSearched);
    }

    public DevsResponseDTO saveDev(DevsCreationDTO dev) {

        Optional<Devs> devCreated = this.devsRepository.findDevsByUserName(dev.userName().toUpperCase());

        if (devCreated.isEmpty()) {
            var requestDevData = new Devs(dev.userName().toUpperCase(), dev.password());
            this.devsRepository.save(requestDevData);
            return new DevsResponseDTO(requestDevData);
        }

        throw new UserNameAlreadyExists();

    }

    public String deleteDev(UUID id) {

        Optional<Devs> devsSearched = this.devsRepository.findById(id);

        if (devsSearched.isEmpty()) {

            throw new UserNotFound();
        }

        String userRemoved = "Usuário " + devsSearched.get().getUserName() + " removido";
        this.devsRepository.delete(devsSearched.get());
        return userRemoved;

    }

    public DevsResponseDTO renameDev(UUID id, DevsRenameDTO devsRenameDTO){

        Optional<Devs> devsSearched = this.devsRepository.findById(id);
        if (devsSearched.isEmpty()) {
            throw new UserNotFound();
        }

        var devsSearched2 = devsSearched.get();
        devsSearched2.setUserName(devsRenameDTO.userName().toUpperCase());

        this.devsRepository.save(devsSearched2);
        return new DevsResponseDTO(devsSearched2);
    }
}

