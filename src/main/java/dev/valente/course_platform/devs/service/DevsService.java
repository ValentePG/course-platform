package dev.valente.course_platform.devs.service;

import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsRenameDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.exceptions.UserNotFound;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

        Devs devResearched = this.devsRepository.findById(id).orElseThrow(
                UserNotFound::new);

        return new DevsResponseDTO(devResearched);
    }

    public DevsResponseDTO findDevByUserName(String userName) {

        Devs devResearched = this.devsRepository.findDevsByUserName(
                userName.toUpperCase()).orElseThrow(
                UserNotFound::new);

        return new DevsResponseDTO(devResearched);
    }

    public DevsResponseDTO saveDev(DevsCreationRequestDTO dev) {

        this.devsRepository.findDevsByUserName(
                dev.userName().toUpperCase())
                .ifPresent(alreadyExist -> {
                    throw new UserNameAlreadyExists("Usuário " + dev.userName() + " já cadastrado!");
                });

        var requestDevData = new Devs(dev.userName().toUpperCase(), dev.password());
        this.devsRepository.save(requestDevData);
        return new DevsResponseDTO(requestDevData);

    }

    public DevsResponseDTO deleteDev(UUID id) {

        Devs devResearched = this.devsRepository.findById(id).orElseThrow(UserNotFound::new);

        var devsResponseDTO = new DevsResponseDTO(devResearched);

        this.devsRepository.delete(devResearched);
        return devsResponseDTO;

    }

    public DevsResponseDTO renameDev(String devToRename, DevsRenameDTO newName){

        // Posso diminuir este método

        Devs devResearched = this.devsRepository.findDevsByUserName(
                devToRename.toUpperCase()).orElseThrow(UserNotFound::new);


        this.devsRepository.findDevsByUserName(newName.userName().toUpperCase())
                .ifPresent(alreadyExists -> {
                    throw new UserNameAlreadyExists("Username: " + newName.userName() + "Já existe!");
                });

        devResearched.setUserName(newName.userName().toUpperCase());
        this.devsRepository.save(devResearched);

        return new DevsResponseDTO(devResearched);
    }

}

