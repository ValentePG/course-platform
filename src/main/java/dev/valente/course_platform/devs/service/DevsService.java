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

        Optional<Devs> devResearched = this.devsRepository.findDevsByUserName(
                dev.userName().toUpperCase());

        if (devResearched.isEmpty()) {
            var requestDevData = new Devs(dev.userName().toUpperCase(), dev.password());
            this.devsRepository.save(requestDevData);
            return new DevsResponseDTO(requestDevData);
        }

        throw new UserNameAlreadyExists();

    }

    public String deleteDev(UUID id) {

        Optional<Devs> devResearched = this.devsRepository.findById(id);

        if (devResearched.isEmpty()) {

            throw new UserNotFound();
        }

        String userRemoved = "Usuário " + devResearched.get().getUserName() + " removido";
        this.devsRepository.delete(devResearched.get());
        return userRemoved;

    }

    public DevsResponseDTO renameDev(String devToRename, DevsRenameDTO userName){

        // Posso diminuir este método

        Optional<Devs> devResearchedForRename = this.devsRepository.findDevsByUserName(
                devToRename.toUpperCase());

        if(devResearchedForRename.isEmpty()){
            throw new UserNotFound();
        }

        Optional<Devs> devResearchedToCheckIfExists = this.devsRepository.findDevsByUserName(
                userName.userName().toUpperCase());

        if(devResearchedToCheckIfExists.isPresent()){
            throw new UserNameAlreadyExists();
        }

        var devResearched = devResearchedForRename.get();
        devResearched.setUserName(userName.userName().toUpperCase());
        this.devsRepository.save(devResearched);
        return new DevsResponseDTO(devResearched);
    }

}

