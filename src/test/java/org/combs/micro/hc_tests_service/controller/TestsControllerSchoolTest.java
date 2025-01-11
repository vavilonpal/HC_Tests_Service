package org.combs.micro.hc_tests_service.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.enums.TestType;
import org.combs.micro.hc_tests_service.service.TestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestsController.class)
public class TestsControllerSchoolTest {
    @MockBean
    private TestService testService;
    private static final SchoolTest TRUE_TEST = SchoolTest.builder()
            .id(1L)
            .title("Test title")
            .teacherId(1L)
            .type(TestType.exam)
            .complexity(Complexity.easy)
            .classLevel(1)
            .description("Test for test")
            .createdAt(LocalDateTime.now())
            .duration(30)
            .build();
    private static final SchoolTest FALSE_TEST = new SchoolTest();



    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllTests() throws Exception {
        when(testService.getAllTests()).thenReturn(List.of(new SchoolTest()));
        mockMvc.perform(get("/api/v1/tests")).andExpect(status().isOk());
        verify(testService, times(1)).getAllTests();
    }

    @Test
    void getById() throws Exception{
        when(testService.getTestById(TRUE_TEST.getId())).thenReturn(Optional.of(TRUE_TEST));
        mockMvc.perform(get("/api/v1/tests/{id}",TRUE_TEST.getId())).andExpect(status().isOk());

        when(testService.getTestById(TRUE_TEST.getId())).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/tests/{id}",TRUE_TEST.getId())).andExpect(status().isBadRequest());

    }

    @Test
    void createTest() throws Exception {
        SchoolTest schoolTest = TRUE_TEST;
        String schoolTestJson = objectMapper.writeValueAsString(schoolTest);
        mockMvc.perform(post("/api/v1/tests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(schoolTestJson)).andExpect(status().isBadRequest());
    }


}
