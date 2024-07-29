package dev.valente.course_platform.devs.service;


import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.exceptions.DevNotFound;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static dev.valente.course_platform.common.DevsConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DevsServiceTest {

    @Mock
    DevsRepository devsRepository;

    @Mock
    CacheService cacheService;

    @InjectMocks
    private DevsService devsService;

    @Test
    @DisplayName("Should save Dev successfuly")
    void saveDev_WithValidData_ReturnsDev(){

        // Arrange
        var devsCreationRequestDTO = new DevsCreationRequestDTO(DEVS.getUserName(), DEVS.getPassword());
        when(devsRepository.findDevsByUserName(devsCreationRequestDTO.userName())).thenReturn(Optional.empty());

        // Act
        DevsResponseDTO sut = this.devsService.saveDev(devsCreationRequestDTO);

        // Asserts
        assertThat(sut).isEqualTo(DEVS_RESPONSE_DTO);
        verify(devsRepository, times(1))
                .findDevsByUserName(devsCreationRequestDTO.userName());
        verify(devsRepository, times(1))
                .save(any(Devs.class));
    }

    @Test
    @DisplayName("Should throw a UserNameAlreadyExists Exception when trying to save an existing User")
    void saveDev_WithExistingData_ThrowsException() {

        // AAA

        // Arrange
        var devsCreationRequestDTO = new DevsCreationRequestDTO(DEVS.getUserName(), DEVS.getPassword());
        when(devsRepository.findDevsByUserName(devsCreationRequestDTO.userName())).thenReturn(Optional.of(DEVS));

        // Act & Asserts

        assertThatThrownBy(() -> this.devsService.saveDev(devsCreationRequestDTO))
                .isInstanceOf(UserNameAlreadyExists.class);

    }

    @Test
    @DisplayName("Should delete Dev successfully")
    void deleteDev_WhenDevFound_ReturnsDev() {
        // Arrange
        when(devsRepository.findById(DEVS_WITH_ID.getId())).thenReturn(Optional.of(DEVS_WITH_ID));
        doNothing().when(cacheService).evictCache(DEVS_WITH_ID.getClass().getSimpleName().toLowerCase(), DEVS_WITH_ID.getId());

        // Act
        var sut = this.devsService.deleteDev(DEVS_WITH_ID.getId());


        // Asserts
        assertThat(sut).isEqualTo(DEVS_RESPONSE_DTO_WITH_ID);
    }

    @Test
    @DisplayName("Should throw a DevNotFound Exception when trying to delete a non-existing user")
    void deleteDev_WhenDevNotFound_ThrowsException() {

        // Arrange
        when(devsRepository.findById(DEVS_WITH_ID.getId())).thenReturn(Optional.empty());

        // Act & Asserts
        assertThatThrownBy(() -> this.devsService.deleteDev(DEVS_WITH_ID.getId()))
                .isInstanceOf(DevNotFound.class);

    }

    @Test
    @DisplayName("Should rename Dev successfuly")
    void renameDev_WhenDevExistAndNewNameDoesNot_ReturnsDev() {

        // Arrange
        when(devsRepository.findById(DEVS_TO_RENAME.getId())).thenReturn(Optional.of(DEVS_TO_RENAME));


        // Act
        var sut = this.devsService.renameDev(DEVS_TO_RENAME.getId(), DEVS_RENAME_DTO);


        // Asserts
        assertThat(sut).isEqualTo(DEVS_RENAMED_DTO);
    }

    @Test
    @DisplayName("Should throw a DevNotFound Exception when trying to rename a non-existing user")
    void renameDev_WhenDevNotFound_ThrowsException() {

        // Arrange
        when(devsRepository.findById(DEVS_WITH_ID.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> this.devsService.renameDev(DEVS_WITH_ID.getId(), DEVS_RENAME_DTO))
                .isInstanceOf(DevNotFound.class);

    }

    @Test
    @DisplayName("Should throw a UserNameAlreadyExists Exception when trying to rename to an existing username")
    void renameDev_WhenNameAlreadyExists_ThrowsException() {

        // Arrange
        when(devsRepository.findById(DEVS_WITH_ID.getId())).thenReturn(Optional.of(DEVS_WITH_ID));
        when(devsRepository.findDevsByUserName(DEVS_RENAME_DTO.userName())).thenReturn(Optional.of(DEVS));

        // Act & Assert
        assertThatThrownBy(() -> this.devsService.renameDev(DEVS_WITH_ID.getId(), DEVS_RENAME_DTO))
                .isInstanceOf(UserNameAlreadyExists.class);

    }

    @Test
    @DisplayName("Should return a Dev successfully")
    void findDevById_WhenDevFound_ReturnsDev(){

        // Arrange
        when(devsRepository.findById(DEVS_WITH_ID.getId())).thenReturn(Optional.of(DEVS_WITH_ID));

        // Act
        var sut = this.devsService.findDevById(DEVS_WITH_ID.getId());

        // Assert
        assertThat(sut).isEqualTo(DEVS_RESPONSE_DTO_WITH_ID);
    }

    @Test
    @DisplayName("Should throw a DevNotFound Exception when trying to find a non-existing user")
    void findDevById_WhenDevNotFound_ThrowsException() {

        // Arrange
        when(devsRepository.findById(DEVS_WITH_ID.getId())).thenReturn(Optional.empty());


        // Act & Assert
        assertThatThrownBy(() -> this.devsService.findDevById(DEVS_WITH_ID.getId()))
                .isInstanceOf(DevNotFound.class);
    }

    @Test
    @DisplayName("Should return a Dev successfully")
    void findDevByUserName_WhenDevFound_ReturnsDev() {

        // Arrange
        when(devsRepository.findDevsByUserName(DEVS.getUserName())).thenReturn(Optional.of(DEVS));

        // Act
        var sut = this.devsService.findDevByUserName(DEVS.getUserName());

        // Assert
        assertThat(sut).isEqualTo(DEVS_RESPONSE_DTO);

    }

    @Test
    @DisplayName("Should throw a DevNotFound Exception when trying to find a non-existing user")
    void findDevByUserName_WhenDevNotFound_ThrowsException() {

        // Arrange
        when(devsRepository.findDevsByUserName(DEVS.getUserName())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> this.devsService.findDevByUserName(DEVS.getUserName()))
                .isInstanceOf(DevNotFound.class);

    }
}
