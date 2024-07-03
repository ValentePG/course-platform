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
    @DisplayName("Should save Dev successfuly")
    void saveDevSuccessfull(){

        // Arrange
        var devsCreationRequestDTO = new DevsCreationRequestDTO("GABRIEL","303030");
        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findDevsByUserName(devsCreationRequestDTO.userName())).thenReturn(Optional.empty());
        when(devsRepository.save(any(Devs.class))).thenReturn(any(Devs.class));

        // Act
        var result = this.devsService.saveDev(devsCreationRequestDTO);


        // Asserts

        assertThat(result).isInstanceOf(DevsResponseDTO.class);
        Assertions.assertEquals(result.userName(), dev.getUserName());
        verify(devsRepository, times(1))
                .findDevsByUserName(devsCreationRequestDTO.userName());
        verify(devsRepository, times(1)).save(any(Devs.class));
    }

    @Test
    @DisplayName("Should throw a UserNameAlreadyExists Exception when trying to save an existing User")
    void saveDevFail() {

        // AAA

        // Arrange
        var mockDevs = mock(Devs.class); // Atenção*
        var devsCreationRequestDTO = new DevsCreationRequestDTO("GABRIEL","5757");
        when(devsRepository.findDevsByUserName(devsCreationRequestDTO.userName())).thenReturn(Optional.of(mockDevs));

        // Act

        UserNameAlreadyExists result = Assertions.assertThrows(UserNameAlreadyExists.class,
                () -> this.devsService.saveDev(devsCreationRequestDTO));

        // Asserts

        Assertions.assertEquals("Nome de usuário já existe!", result.getMessage());
        verify(devsRepository, times(1))
                .findDevsByUserName(devsCreationRequestDTO.userName());
    }

    @Test
    @DisplayName("Should delete Dev successfully")
    void deleteDevSuccess() {
        // Arrange
        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findById(dev.getId())).thenReturn(Optional.of(dev));

        // Act
        var result = this.devsService.deleteDev(dev.getId());


        // Asserts
        Assertions.assertEquals(dev.getId(), result.id());
        assertThat(result).isInstanceOf(DevsResponseDTO.class);
        verify(devsRepository, times(1)).findById(dev.getId());
        verify(devsRepository, times(1)).delete(any(Devs.class));
    }

    @Test
    @DisplayName("Should throw a UserNotFound Exception when trying to delete a non-existing user")
    void deleteDevFail() {

        // Arrange
        UUID id = UUID.randomUUID();
        when(devsRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        UserNotFound result = Assertions.assertThrows(UserNotFound.class,
                () -> this.devsService.deleteDev(id));

        // Asserts
        Assertions.assertEquals("Usuário não encontrado!", result.getMessage());
        verify(devsRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Should rename Dev successfuly")
    void renameDevSucess() {

        // Arrange
        var devToRename = new Devs("GABRIEL", "505050");
        var newName = new DevsRenameDTO("GUSTAVO");
        when(devsRepository.findDevsByUserName(devToRename.getUserName())).thenReturn(Optional.of(devToRename));


        // Act
        var result = this.devsService.renameDev(devToRename.getUserName(), newName);


        // Asserts
        assertThat(result).isInstanceOf(DevsResponseDTO.class);
        Assertions.assertEquals(newName.userName(), result.userName());
        verify(devsRepository, times(1)).findDevsByUserName(devToRename.getUserName());
        verify(devsRepository, times(1)).findDevsByUserName(newName.userName());
    }

    @Test
    @DisplayName("Should throw a UserNotFound Exception when trying to rename a non-existing user")
    void renameDevFail() {

        // Arrange
        var devToRename = new Devs("GABRIEL", "505050");
        var newName = new DevsRenameDTO("GUSTAVO");
        when(devsRepository.findDevsByUserName(devToRename.getUserName())).thenReturn(Optional.empty());

        // Arrange
        UserNotFound result = Assertions.assertThrows(UserNotFound.class,
                () -> this.devsService.renameDev(devToRename.getUserName(), newName));


        // Arrange
        Assertions.assertEquals("Usuário não encontrado!", result.getMessage());
        verify(devsRepository, times(1)).findDevsByUserName(devToRename.getUserName());
    }

    @Test
    @DisplayName("Should throw a UserNameAlreadyExists Exception when trying to rename to an existing username")
    void renameDevFail2() {

        // Arrange
        var devToRename = new Devs("GABRIEL", "505050");
        var newName = new DevsRenameDTO("GUSTAVO");
        when(devsRepository.findDevsByUserName(devToRename.getUserName())).thenReturn(Optional.of(devToRename));
        when(devsRepository.findDevsByUserName(newName.userName())).thenReturn(Optional.of(devToRename));

        // Act
        UserNameAlreadyExists result = Assertions.assertThrows(UserNameAlreadyExists.class,
                () -> this.devsService.renameDev(devToRename.getUserName(), newName));

        // Asserts
        Assertions.assertEquals("Nome de usuário já existe!", result.getMessage());
        verify(devsRepository, times(1)).findDevsByUserName(devToRename.getUserName());
        verify(devsRepository, times(1)).findDevsByUserName(newName.userName());
    }

    @Test
    @DisplayName("Should return a Dev successfully")
    void findDevByIdSucess() {

        // Arrange
        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findById(dev.getId())).thenReturn(Optional.of(dev));

        // Act
        var result = this.devsService.findDevById(dev.getId());

        // Asserts
        Assertions.assertEquals(result.userName(), dev.getUserName());
        assertThat(result).isInstanceOf(DevsResponseDTO.class);
        verify(devsRepository, times(1)).findById(dev.getId());
    }

    @Test
    @DisplayName("Should throw a UserNotFound Exception when trying to find a non-existing user")
    void findDevByIdFail() {

        // Arrange
        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findById(dev.getId())).thenReturn(Optional.empty());


        // Act
        UserNotFound result = Assertions.assertThrows(UserNotFound.class,
                () -> this.devsService.findDevById(dev.getId()));

        // Asserts
        Assertions.assertEquals("Usuário não encontrado!", result.getMessage());
        verify(devsRepository, times(1)).findById(dev.getId());
    }

    @Test
    @DisplayName("Should return a Dev successfully")
    void findDevByUserNameSucess() {

        // Arrange
        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findDevsByUserName(dev.getUserName())).thenReturn(Optional.of(dev));

        // Act
        var result = this.devsService.findDevByUserName(dev.getUserName());

        // Asserts
        Assertions.assertEquals(result.userName(), dev.getUserName());
        assertThat(result).isInstanceOf(DevsResponseDTO.class);
        verify(devsRepository, times(1)).findDevsByUserName(dev.getUserName());

    }

    @Test
    @DisplayName("Should throw a UserNotFound Exception when trying to find a non-existing user")
    void findDevByUserNameFail() {

        // Arrange
        var dev = new Devs("GABRIEL", "505050");
        when(devsRepository.findDevsByUserName(dev.getUserName())).thenReturn(Optional.empty());

        // Act
        UserNotFound result = Assertions.assertThrows(UserNotFound.class,
                () -> this.devsService.findDevByUserName(dev.getUserName()));

        // Asserts
        Assertions.assertEquals("Usuário não encontrado!", result.getMessage());
        verify(devsRepository, times(1)).findDevsByUserName(dev.getUserName());
    }
}
