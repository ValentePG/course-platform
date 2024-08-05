package dev.valente.course_platform.devs.controller;

import dev.valente.course_platform.devs.service.DevsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DevsController.class)
public class DevsControllerTest {

    @MockBean
    private DevsService devsService;

    @Autowired
    private MockMvc mockMvc;
}
