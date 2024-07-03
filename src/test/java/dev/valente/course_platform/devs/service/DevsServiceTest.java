package dev.valente.course_platform.devs.service;


import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsRenameDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.exceptions.UserNotFound;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DevsServiceTest {

    @Mock
    DevsRepository devsRepository;

    @Autowired
    @InjectMocks
    private DevsService devsService;

    @Test
    @DisplayName("Should save Dev in DB and return Dev")
    void saveDevSuccessfull(){

        // AAA

        // Arrange
        var devsCreationRequestDTO = new DevsCreationRequestDTO("GABRIEL","303030");
        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findDevsByUserName(devsCreationRequestDTO.userName())).thenReturn(Optional.empty());
        when(devsRepository.save(any(Devs.class))).thenReturn(any(Devs.class));

        //Act
        var result = this.devsService.saveDev(devsCreationRequestDTO);


        //Asserts

        assertThat(result).isInstanceOf(DevsResponseDTO.class);
        Assertions.assertEquals(result.userName(), dev.getUserName());
        verify(devsRepository, times(1))
                .findDevsByUserName(devsCreationRequestDTO.userName());
        verify(devsRepository, times(1)).save(any(Devs.class));
    }

    @Test
    @DisplayName("Should thrown an UserNameAlreadyExist exception")
    void saveDevFail() {

        // AAA

        // Arrange
        var devsCreationRequestDTO = new DevsCreationRequestDTO("GABRIEL","5757");
        when(devsRepository.findDevsByUserName(devsCreationRequestDTO.userName()))
                .thenThrow(UserNameAlreadyExists.class);

        //Act

        Exception result = Assertions.assertThrows(UserNameAlreadyExists.class,
                () -> this.devsService.saveDev(devsCreationRequestDTO));

        //Asserts

        Assertions.assertEquals("Nome de usuário já existe!", result.getMessage());
        verify(devsRepository, times(1))
                .findDevsByUserName(devsCreationRequestDTO.userName());
    }

    @Test
    @DisplayName("Should delete Dev in DB and return Dev deleted")
    void deleteDevSuccess() {
        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findById(dev.getId())).thenReturn(Optional.of(dev));

        var result = this.devsService.deleteDev(dev.getId());

        Assertions.assertEquals(dev.getId(), result.id());
        Assertions.assertInstanceOf(DevsResponseDTO.class, result);
        verify(devsRepository, times(1)).findById(dev.getId());
        verify(devsRepository, times(1)).delete(any(Devs.class));
    }

    @Test
    @DisplayName("Should thrown an UserNotFound Exception")
    void deleteDevFail() {
        UUID id = UUID.randomUUID();
        when(devsRepository.findById(id))
                .thenReturn(Optional.empty())
                .thenThrow(UserNotFound.class);


        Exception result = Assertions.assertThrows(UserNotFound.class,
                () -> this.devsService.deleteDev(id));

        Assertions.assertEquals("Usuário não encontrado!", result.getMessage());
        verify(devsRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should rename Dev in DB and return Dev")
    void renameDevSucess() {
        var devToRename = new Devs("GABRIEL", "505050");
        var newName = new DevsRenameDTO("GUSTAVO");
        when(devsRepository.findDevsByUserName(devToRename.getUserName())).thenReturn(Optional.of(devToRename));

        var result = this.devsService.renameDev(devToRename.getUserName(), newName);

        Assertions.assertInstanceOf(DevsResponseDTO.class, result);
        Assertions.assertEquals(newName.userName(), devToRename.getUserName());
        verify(devsRepository, times(1)).findDevsByUserName(devToRename.getUserName());
        verify(devsRepository, times(1)).findDevsByUserName(newName.userName());
    }

    @Test
    @DisplayName("Should thrown an UserNotFound Exception")
    void renameDevFail() {
        var devToRename = new Devs("GABRIEL", "505050");
        var newName = new DevsRenameDTO("GUSTAVO");
        when(devsRepository.findDevsByUserName(devToRename.getUserName()))
                .thenReturn(Optional.empty())
                .thenThrow(UserNotFound.class);

        Exception result = Assertions.assertThrows(UserNotFound.class,
                () -> this.devsService.renameDev(devToRename.getUserName(), newName));

        Assertions.assertEquals("Usuário não encontrado!", result.getMessage());
        verify(devsRepository, times(1)).findDevsByUserName(devToRename.getUserName());
    }

    @Test
    @DisplayName("Should thrown an UserNameAlreadyExists Exception")
    void renameDevFail2() {
        var devToRename = new Devs("GABRIEL", "505050");
        var newName = new DevsRenameDTO("GUSTAVO");
        when(devsRepository.findDevsByUserName(devToRename.getUserName())).thenReturn(Optional.of(devToRename));
        when(devsRepository.findDevsByUserName(newName.userName())).thenThrow(UserNameAlreadyExists.class);

        Exception result = Assertions.assertThrows(UserNameAlreadyExists.class,
                () -> this.devsService.renameDev(devToRename.getUserName(), newName));

        Assertions.assertEquals("Nome de usuário já existe!", result.getMessage());
        verify(devsRepository, times(1)).findDevsByUserName(devToRename.getUserName());
        verify(devsRepository, times(1)).findDevsByUserName(newName.userName());
    }

    @Test
    @DisplayName("Should return a Dev successfully")
    void findDevByIdSucess() {

        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findById(dev.getId())).thenReturn(Optional.of(dev));

        var result = this.devsService.findDevById(dev.getId());

        Assertions.assertEquals(result.userName(), dev.getUserName());
        Assertions.assertInstanceOf(DevsResponseDTO.class, result);
        verify(devsRepository, times(1)).findById(dev.getId());
    }

    @Test
    @DisplayName("Should thrown an UserNotFound Exception")
    void findDevByIdFail() {

        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findById(dev.getId()))
                .thenReturn(Optional.empty())
                .thenThrow(UserNotFound.class);

        Exception result = Assertions.assertThrows(UserNotFound.class,
                () -> this.devsService.findDevById(dev.getId()));

        Assertions.assertEquals("Usuário não encontrado!", result.getMessage());
        verify(devsRepository, times(1)).findById(dev.getId());
    }

    @Test
    @DisplayName("Should return a Dev successfully")
    void findDevByUserNameSucess() {

        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findDevsByUserName(dev.getUserName())).thenReturn(Optional.of(dev));

        var result = this.devsService.findDevByUserName(dev.getUserName());

        Assertions.assertEquals(result.userName(), dev.getUserName());
        Assertions.assertInstanceOf(DevsResponseDTO.class, result);
        verify(devsRepository, times(1)).findDevsByUserName(dev.getUserName());

    }

    @Test
    @DisplayName("Should thrown an UserNotFound Exception")
    void findDevByUserNameFail() {
        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findDevsByUserName(dev.getUserName()))
                .thenReturn(Optional.empty())
                .thenThrow(UserNotFound.class);

        Exception result = Assertions.assertThrows(UserNotFound.class,
                () -> this.devsService.findDevByUserName(dev.getUserName()));

        Assertions.assertEquals("Usuário não encontrado!", result.getMessage());
        verify(devsRepository, times(1)).findDevsByUserName(dev.getUserName());
    }
}
