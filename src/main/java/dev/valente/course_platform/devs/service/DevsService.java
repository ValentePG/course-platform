package dev.valente.course_platform.devs.service;

import dev.valente.course_platform.devs.dtos.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.dtos.DevsRenameDTO;
import dev.valente.course_platform.devs.dtos.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.exceptions.DevNotFound;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


@Service
@CacheConfig(cacheNames = "devs")
public class DevsService {

    private static final Logger log = LogManager.getLogger(DevsService.class);
    private final DevsRepository devsRepository;
    private final CacheService cacheService;

    public DevsService(DevsRepository devsRepository, CacheService cacheService) {
        this.devsRepository = devsRepository;
        this.cacheService = cacheService;
    }

    public List<DevsResponseDTO> getAllDevs() {

        return this.devsRepository.findAll().stream().map(
                DevsResponseDTO::new).toList();
    }

//    @Cacheable(key = "#id")
    public DevsResponseDTO findDevById(UUID id) {

        Devs devResearched = this.devsRepository.findById(id).orElseThrow(
                DevNotFound::new);
        return new DevsResponseDTO(devResearched);
    }

//    @Cacheable(key = "#userName")
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

        // Abstrair para uma classe Util
        log.info(String.format("Usuário %s salvo com sucesso " +
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        .format(LocalDateTime.now()), requestDevData.getUserName()));

        return new DevsResponseDTO(requestDevData);

    }

    public DevsResponseDTO deleteDev(UUID id) {

        Devs devResearched = this.devsRepository.findById(id).orElseThrow(DevNotFound::new);
        var devsResponseDTO = new DevsResponseDTO(devResearched);

        // Cache
//        cacheService.evictCache(devResearched.getClass().getSimpleName().toLowerCase(), devResearched.getId());

        if(!devResearched.getListOfContentsRegistered().isEmpty()){
            this.devsRepository.deleteContentRegistered(id);
        }
        if(!devResearched.getListOfWatchedContents().isEmpty()){
            this.devsRepository.deleteContentWatched(id);
        }

        this.devsRepository.deleteById(devResearched.getId());
        return devsResponseDTO;

    }

    public DevsResponseDTO renameDev(UUID id, DevsRenameDTO newName){


        Devs devResearched = this.devsRepository.findById(id).orElseThrow(DevNotFound::new);


        this.devsRepository.findDevsByUserName(newName.userName().toUpperCase())
                .ifPresent(alreadyExists -> {
                    throw new UserNameAlreadyExists();
                });

        devResearched.setUserName(newName.userName().toUpperCase());
        this.devsRepository.save(devResearched);

        var devsResponseDTO = new DevsResponseDTO(devResearched);


        // Cache
        cacheService.putCache(devResearched.getClass().getSimpleName().toLowerCase(),
                devResearched.getId(), devsResponseDTO);

        return devsResponseDTO;
    }

}

