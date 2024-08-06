package dev.valente.course_platform.devs.controller;

import static dev.valente.course_platform.common.DevsConstants.DEVS_CREATION_REQUEST_DTO;
import static dev.valente.course_platform.common.DevsConstants.DEVS_WITH_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.valente.course_platform.devs.DTOs.DevsCreationRequestDTO;
import dev.valente.course_platform.devs.DTOs.DevsResponseDTO;
import dev.valente.course_platform.devs.service.DevsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@WebMvcTest(DevsController.class)
public class DevsControllerTest {

    @MockBean
    private DevsService devsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testeTeste() throws Exception {

//        String json = objectMapper.writeValueAsString(DEVS_CREATION_REQUEST_DTO);
//        DevsCreationRequestDTO devsCreationRequestDTO = objectMapper.readValue(json, DevsCreationRequestDTO.class);
//
//        MvcResult mvcResult = mockMvc.perform(post("/api/devs")
//                .content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.userName").value(DEVS_CREATION_REQUEST_DTO.userName()))
//                .andReturn();

    }
}
