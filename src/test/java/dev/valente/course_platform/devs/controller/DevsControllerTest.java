package dev.valente.course_platform.devs.controller;

import static dev.valente.course_platform.common.DevsConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import dev.valente.course_platform.devs.exceptions.DevNotFound;
import dev.valente.course_platform.devs.exceptions.UserNameAlreadyExists;
import dev.valente.course_platform.devs.service.DevsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import java.util.UUID;


@WebMvcTest(DevsController.class)
public class DevsControllerTest {

    @MockBean
    private DevsService devsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveDev_WithValidData_ReturnsCreatedAndDevsResponseDTO() throws Exception {

        when(devsService.saveDev(DEVS_CREATION_REQUEST_DTO_VALID)).thenReturn(DEVS_RESPONSE_DTO_WITH_ID);

        mockMvc.perform(post("/api/devs")
                            .content(objectMapper.writeValueAsString(DEVS_CREATION_REQUEST_DTO_VALID))
                            .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$").hasJsonPath())
                        .andDo(print());

    }

    @Test
    public void saveDev_WithInvalidData_ReturnsUnprocessableEntity() throws Exception {

        mockMvc.perform(post("/api/devs")
                            .content(objectMapper.writeValueAsString(DEVS_CREATION_REQUEST_DTO_INVALID))
                            .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isUnprocessableEntity())
                        .andDo(print());


    }

    @Test
    public void saveDev_WhenDevAlreadyExists_ReturnsBadRequest() throws Exception {

        doThrow(UserNameAlreadyExists.class).when(devsService).saveDev(DEVS_CREATION_REQUEST_DTO_VALID);

        mockMvc.perform(post("/api/devs")
                            .content(objectMapper.writeValueAsString(DEVS_CREATION_REQUEST_DTO_VALID))
                            .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andDo(print());

    }

    @Test
    public void getAllDevs_WhenNoDeveloperIsRegistered_ReturnsVoidListOfDevsResponseDTO() throws Exception {

        mockMvc.perform(get("/api/devs"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isEmpty())
                        .andDo(print());


    }

    @Test
    public void getAllDevs_WhenAtLeastOneDeveloperIsRegistered_ReturnsListOfDevsResponseDTO() throws Exception {

        when(devsService.getAllDevs()).thenReturn(List.of(DEVS_RESPONSE_DTO_WITH_ID));

        mockMvc.perform(get("/api/devs"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").hasJsonPath())
                        .andDo(print());

    }


    @Test
    void findDevById_WithExistentId_ReturnsDevsResponseDTO() throws Exception {
        when(devsService.findDevById(any(UUID.class))).thenReturn(DEVS_RESPONSE_DTO_WITH_ID);

        mockMvc.perform(get("/api/devs/id")
                            .param("id", String.valueOf(UUID.randomUUID())))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").hasJsonPath())
                        .andDo(print());

    }

    @Test
    void findDevById_WithUnexistentId_ReturnsNotFound() throws Exception {
        doThrow(DevNotFound.class).when(devsService).findDevById(any(UUID.class));

        mockMvc.perform(get("/api/devs/id")
                            .param("id", String.valueOf(UUID.randomUUID())))
                        .andExpect(status().isNotFound())
                        .andDo(print());

    }

    @Test
    void findDevByUserName_WithExistentUserName_ReturnsDevsResponseDTO() throws Exception {

        when(devsService.findDevByUserName(any(String.class))).thenReturn(DEVS_RESPONSE_DTO_WITH_ID);

        mockMvc.perform(get("/api/devs/username")
                            .param("userName", DEVS_RESPONSE_DTO_WITH_ID.userName()))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").hasJsonPath())
                        .andDo(print());


    }

    @Test
    void findDevByUserName_WithUnexistentUserName_RetunsDevsResponseDTO() throws Exception {

        doThrow(DevNotFound.class).when(devsService).findDevByUserName(any(String.class));

        mockMvc.perform(get("/api/devs/username")
                            .param("userName", "GUSTAVO"))
                        .andExpect(status().isNotFound())
                        .andDo(print());
    }



    @Test
    void deleteDev_WithExistentId_ReturnsNoContent() throws Exception {
        when(devsService.deleteDev(any(UUID.class))).thenReturn(DEVS_RESPONSE_DTO_WITH_ID);

        mockMvc.perform(delete("/api/devs/{id}", UUID.randomUUID()))
                        .andExpect(status().isNoContent())
                        .andExpect(jsonPath("$").hasJsonPath())
                        .andDo(print());
    }

    @Test
    void deleteDev_WithUnexistentId_ReturnsNotFound() throws Exception {
        doThrow(DevNotFound.class).when(devsService).deleteDev(any(UUID.class));

        mockMvc.perform(delete("/api/devs/{id}", UUID.randomUUID()))
                        .andExpect(status().isNotFound())
                        .andDo(print());
    }

    @Test
    void renameDev_WithExistentIdAndUnexistentNewName_ReturnsDevsResponseDTO() throws Exception {
        UUID id = UUID.randomUUID();

        when(devsService.renameDev(id, DEVS_RENAME_DTO_VALID)).thenReturn(DEVS_RENAMED_DTO);

        mockMvc.perform(put("/api/devs/{id}", id)
                            .content(objectMapper.writeValueAsString(DEVS_RENAME_DTO_VALID))
                            .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$.userName").value(DEVS_RENAME_DTO_VALID.userName()))
                        .andExpect(status().isOk())
                        .andDo(print());
    }

    @Test
    void renameDev_WithExistentIdAndExistentNewName_ReturnsBadRequest() throws Exception {
        UUID id = UUID.randomUUID();

        doThrow(UserNameAlreadyExists.class).when(devsService).renameDev(id, DEVS_RENAME_DTO_VALID);

        mockMvc.perform(put("/api/devs/{id}", id)
                            .content(objectMapper.writeValueAsString(DEVS_RENAME_DTO_VALID))
                            .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andDo(print());

    }

    @Test
    void renameDev_WithUnexistentId_ReturnsNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        doThrow(DevNotFound.class).when(devsService).renameDev(id, DEVS_RENAME_DTO_VALID);

        mockMvc.perform(put("/api/devs/{id}", id)
                            .content(objectMapper.writeValueAsString(DEVS_RENAME_DTO_VALID))
                            .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andDo(print());
    }

    @Test
    void renameDev_WithInvalidData_ReturnsUnprocessableEntity() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(put("/api/devs/{id}", id)
                            .content(objectMapper.writeValueAsString(DEVS_RENAME_DTO_INVALID))
                            .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isUnprocessableEntity())
                        .andDo(print());
    }
}
