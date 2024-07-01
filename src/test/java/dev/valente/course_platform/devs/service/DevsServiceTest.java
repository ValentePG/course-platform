package dev.valente.course_platform.devs.service;

import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.exceptions.UserNotCreated;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ActiveProfiles("test")
class DevsServiceTest {

    @MockBean
    DevsService devsService;

    @Test
    void getAllDevs() {

    }

    @Test
    @DisplayName("Should save Dev succesfully from DB")
    void saveDevSucess() {

        // AA

        // Arrange
        var inputUser = new DevsCreationRequestDTO("GABRIEL", "303030");
        List<DevsResponseDTO> devs = new ArrayList<>();

        // Act
        var result = this.devsService.saveDev(inputUser);
        devs.add(result);

        // Assert
        assertEquals(1, devs.size());

    }

    @Test
    @DisplayName("Should return a error")
    void saveDevFail() {
        // AA
        var devsCreationRequestDTO = new DevsCreationRequestDTO("Gabriel", "303030");

        // PRÓXIMO PASSO //


        // Assert
        Assertions.assertThrows(UserNameAlreadyExists.class, () -> this.devsService.saveDev(devsCreationRequestDTO));

    }

    @Test
    void findDevById() {
    }

    @Test
    void findDevByUserName() {
    }

    @Test
    void deleteDev() {
    }

    @Test
    void renameDev() {
    }
}