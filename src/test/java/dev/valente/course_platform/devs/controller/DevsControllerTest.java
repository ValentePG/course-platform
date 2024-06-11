package dev.valente.course_platform.devs.controller;

import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.Devs;
import dev.valente.course_platform.devs.repository.DevsRepository;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DevsControllerTest {



    @Test
    void getAllDevs() {
        DevsResponseDTO test2 = new DevsResponseDTO(1L, "Gabriel", "303030");

        List<DevsResponseDTO> listOfDevs = List.of(test2);

        assertEquals(1, listOfDevs.size());


    }

    @Test
    void saveDev() {
    }
}