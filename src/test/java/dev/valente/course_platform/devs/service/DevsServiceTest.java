package dev.valente.course_platform.devs.service;


import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
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
    @DisplayName("Should save Dev in DB")
    void saveDevSuccessfull(){

        // AAA

        // Arrange
        var devsCreationRequestDTO = new DevsCreationRequestDTO("GABRIEL","303030");
        var dev = new Devs(devsCreationRequestDTO.userName(), devsCreationRequestDTO.password());
        when(devsRepository.findDevsByUserName(devsCreationRequestDTO.userName())).thenReturn(Optional.empty());
        when(devsRepository.save(any(Devs.class))).thenReturn(any(Devs.class));

        //Act
        var result = this.devsService.saveDev(devsCreationRequestDTO);


        //Asserts

        assertThat(result).isInstanceOf(DevsResponseDTO.class);
        Assertions.assertEquals(result.userName(), dev.getUserName());
        verify(devsRepository, times(1)).findDevsByUserName(devsCreationRequestDTO.userName());
        verify(devsRepository, times(1)).save(any(Devs.class));
    }

    @Test
    @DisplayName("Should thrown an Exception")
    void saveDevFail() {

        // AAA

        // Arrange
        var devsCreationRequestDTO = new DevsCreationRequestDTO("GABRIEL","5757");
        when(devsRepository.findDevsByUserName(devsCreationRequestDTO.userName())).thenThrow(UserNameAlreadyExists.class);

        //Act

        Exception thrown = Assertions.assertThrows(UserNameAlreadyExists.class,
                () -> this.devsService.saveDev(devsCreationRequestDTO));

        //Asserts

        Assertions.assertEquals("Nome de usuário já existe", thrown.getMessage());
        verify(devsRepository, times(1)).findDevsByUserName(devsCreationRequestDTO.userName());
        verify(devsRepository, times(0)).save(any(Devs.class));
    }

    @Test
    @DisplayName("Should delete Dev in DB")
    void deleteDevSuccess() {
    }

    @Test
    @DisplayName("Should delete Dev in DB")
    void deleteDevFail() {
    }



    @Test
    @DisplayName("Should rename Dev in DB")
    void renameDev() {
    }

}
