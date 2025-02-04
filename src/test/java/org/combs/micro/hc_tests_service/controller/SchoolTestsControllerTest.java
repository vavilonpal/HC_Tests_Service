package org.combs.micro.hc_tests_service.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.enums.TestType;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.combs.micro.hc_tests_service.response.SchoolTestResponse;
import org.combs.micro.hc_tests_service.mapper.SchoolTestMapper;
import org.combs.micro.hc_tests_service.service.SchoolTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SchoolTestsController.class)
public class SchoolTestsControllerTest {
    @MockBean
    private SchoolTestService schoolTestService;
    @MockBean
    private SchoolTestMapper schoolTestMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllTests_ShouldReturn200_WhenTestsExist() throws Exception {
        List<SchoolTestResponse> testResponses = List.of(SchoolTestResponse.builder().id(1L).title("Test response").build());
        Page<SchoolTestResponse> pageResponse = new PageImpl<>(testResponses);

        when(schoolTestService.getAllPageableTests(any(), any(), any(), any(), any())).thenReturn(pageResponse);

        mockMvc.perform(get("/api/v1/tests")  // Укажите ваш путь
                        .param("complexity", "5")
                        .param("classLevel", "10")
                        .param("teacherId", "2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].title").value("Test response"));
    }

    @Test
    void getAllTests_ShouldReturn404_WhenNoTestsFound() throws Exception {
        when(schoolTestService.getAllPageableTests(any(), any(), any(), any(), any()))
                .thenReturn(Page.empty());

        mockMvc.perform(get("/api//v1/tests")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());  // Ожидаем 404 Not Found
    }

    @Test
    void getAllTests_ShouldReturn200_WhenNoFiltersPassed() throws Exception {
        List<SchoolTestResponse> testResponses = List.of(SchoolTestResponse.builder().id(1L).title("Test response").build());
        Page<SchoolTestResponse> pageResponse = new PageImpl<>(testResponses);

        when(schoolTestService.getAllPageableTests(any(), any(), any(), any(), any()))
                .thenReturn(pageResponse);

        mockMvc.perform(get("/api/v1/tests")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getTestById() throws Exception{
        SchoolTest test = SchoolTest.builder().id(1L).title("test").build();

        when(schoolTestService.getTestById(1L))
                .thenReturn(test);
        when(schoolTestMapper.toResponse(test))
                .thenReturn(SchoolTestResponse.builder().id(test.getId()).title(test.getTitle()).build());

        mockMvc.perform(get("/api/v1/tests/1")
                .accept(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(status().isOk());
    }

    @Test
    void createTest_ShouldReturn201_WhenValidRequest() throws Exception {
        SchoolTestRequest request = SchoolTestRequest.builder()
                .title("test")
                .teacherId(1L)
                .type(TestType.exam)
                .schoolSubject(new SchoolSubject(1L, "test"))
                .complexity(Complexity.easy)
                .classLevel(11)
                .description("test")
                .duration(60)
                .build();

        // Выполнение запроса
        mockMvc.perform(post("/api/tests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("test"));

        // Проверка вызова метода сервиса
        verify(schoolTestService, times(1)).createTest(any(SchoolTestRequest.class));
    }

    @Test
    void createTest_ShouldReturn400_WhenInvalidRequest() throws Exception {
        SchoolTestRequest invalidRequest = SchoolTestRequest.builder()
                .complexity(Complexity.easy)
                .classLevel(11)
                .description("test")
                .build();

        mockMvc.perform(post("/api/tests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").isArray());  // Ошибки валидации

        // Убеждаемся, что метод сервиса НЕ вызывался
        verify(schoolTestService, never()).createTest(any(SchoolTestRequest.class));
    }





}
