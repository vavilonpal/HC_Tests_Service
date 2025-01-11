package org.combs.micro.hc_tests_service.controller;

import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.generate-ddl", () -> true);
    }

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    private final Question TEST_QUESTION = Question
            .builder()
            .id(1L)
            .teacherId(2L)
            .schoolSubject("Biology")
            .description("Test question")
            .answer("true test")
            .type("hard")
            .difficulty(3)
            .rankPoints(1)
            .build();

    @BeforeEach
    void init() {
        String baseUrl = "http://localhost:" + port + "/api/v1/questions";
    }

    @Test
    void createQuestion_andGetQuestionTest() throws Exception {
        String baseUrl = "http://localhost:" + port + "/api/v1/questions";
        ResponseEntity<Question> postResponse = restTemplate.postForEntity(baseUrl, TEST_QUESTION, Question.class);

        mockMvc.perform(get(baseUrl+"/1")).andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        );

        ResponseEntity<Question> getResponse = restTemplate.getForEntity(baseUrl+"/"+TEST_QUESTION.getId(), Question.class);
        assertNotNull(getResponse.getBody());
        assertEquals(TEST_QUESTION.getId(), getResponse.getBody().getId());


    }

    @Test
    void updateQuestionTest(){
        String baseUrl = "http://localhost:" + port + "/api/v1/questions";

        ResponseEntity<Question> getResponse = restTemplate.getForEntity(baseUrl+"/"+TEST_QUESTION.getId(), Question.class);
        assertNotNull(getResponse.getBody());
        getResponse.getBody().setSchoolSubject("Physics");

        restTemplate.put(baseUrl+"/"+TEST_QUESTION.getId(), getResponse);

        ResponseEntity<Question> getChangedResponse = restTemplate.getForEntity(baseUrl+"/"+TEST_QUESTION.getId(), Question.class);
        assertNotNull(getResponse.getBody());
        assertEquals("Physics", getChangedResponse.getBody().getSchoolSubject());




    }
}