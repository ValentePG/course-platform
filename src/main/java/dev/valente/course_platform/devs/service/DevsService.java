package dev.valente.course_platform.devs.service;

import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsRenameDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.exceptions.DevNotFound;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "devs")
    public DevsResponseDTO findDevById(UUID id) {

        Devs devResearched = this.devsRepository.findById(id).orElseThrow(
                DevNotFound::new);

        return new DevsResponseDTO(devResearched);
    }

    @Cacheable(value = "devs")
    public DevsResponseDTO findDevByUserName(String userName) {

        Devs devResearched = this.devsRepository.findDevsByUserName(
                userName.toUpperCase()).orElseThrow(
                DevNotFound::new);

        return new DevsResponseDTO(devResearched);
    }

    public DevsResponseDTO saveDev(DevsCreationRequestDTO dev) {

        this.devsRepository.findDevsByUserName(
                dev.userName().toUpperCase())
                .ifPresent(alreadyExist -> {
                    throw new UserNameAlreadyExists();
                });

        var requestDevData = new Devs(dev.userName().toUpperCase(), dev.password());
        this.devsRepository.save(requestDevData);
        return new DevsResponseDTO(requestDevData);

    }

    @CacheEvict(value = "devs")
    public DevsResponseDTO deleteDev(UUID id) {

        Devs devResearched = this.devsRepository.findById(id).orElseThrow(DevNotFound::new);
        var devsResponseDTO = new DevsResponseDTO(devResearched);

        if(!devResearched.getListOfContentsRegistered().isEmpty()){
            this.devsRepository.deleteContentRegistered(id);
        }
        if(!devResearched.getListOfWatchedContents().isEmpty()){
            this.devsRepository.deleteContentWatched(id);
        }

        this.devsRepository.delete(devResearched);
        return devsResponseDTO;

    }

    @CachePut(value = "devs")
    public DevsResponseDTO renameDev(String devToRename, DevsRenameDTO newName){

        // Posso diminuir este método

        Devs devResearched = this.devsRepository.findDevsByUserName(
                devToRename.toUpperCase()).orElseThrow(DevNotFound::new);


        this.devsRepository.findDevsByUserName(newName.userName().toUpperCase())
                .ifPresent(alreadyExists -> {
                    throw new UserNameAlreadyExists();
                });

        devResearched.setUserName(newName.userName().toUpperCase());
        this.devsRepository.save(devResearched);

        // Cache.cacheConfig(id); Criar pasta para configurar o Cache Customizado e retirar o CachePut daqui!
        return new DevsResponseDTO(devResearched);
    }

}

