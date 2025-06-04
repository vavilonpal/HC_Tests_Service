package org.combs.micro.hc_tests_service.controller.student;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.mapper.AnswerMapper;
import org.combs.micro.hc_tests_service.mapper.QuestionMapper;
import org.combs.micro.hc_tests_service.mapper.ResultMapper;
import org.combs.micro.hc_tests_service.mapper.SchoolTestMapper;
import org.combs.micro.hc_tests_service.repository.cacheRepository.AnswerCacheRepository;
import org.combs.micro.hc_tests_service.request.AnswerRequest;
import org.combs.micro.hc_tests_service.request.ResultRequest;
import org.combs.micro.hc_tests_service.response.AnswerResponse;
import org.combs.micro.hc_tests_service.response.ResultResponse;
import org.combs.micro.hc_tests_service.response.SchoolTestInfoResponse;
import org.combs.micro.hc_tests_service.response.SolveQuestionResponse;
import org.combs.micro.hc_tests_service.service.AnswerService;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.combs.micro.hc_tests_service.service.ResultService;
import org.combs.micro.hc_tests_service.service.SchoolTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;


/*
  Контроллер отвечает за отправку ответов на вопросы
  И получение вопросов для решения
 */
@RestController
@RequestMapping("/api/v1/test/solve")
@RequiredArgsConstructor
public class TestSolveController {
    private final ResultMapper resultMapper;
    private final ResultService resultService;
    private final SchoolTestMapper testMapper;
    private final QuestionMapper questionMapper;
    private final QuestionService questionService;
    private final SchoolTestService schoolTestService;


    // Shows student info about test
    @GetMapping("/info/{id}")
    public ResponseEntity<SchoolTestInfoResponse> getTestInfoById(@PathVariable Long id) {
        SchoolTestInfoResponse testInfoResponse = testMapper.toInfoResponse(schoolTestService.getTestById(id));
        return ResponseEntity.ok(testInfoResponse);
    }

    //Test solving start
    @PostMapping
    public ResponseEntity<Set<SolveQuestionResponse>> startTestSolving(@RequestBody ResultRequest resultRequest) {
        Result result = resultService.createResult(resultRequest);

        Set<SolveQuestionResponse> testQuestions = questionService.getAllTestQuestions(resultRequest.getTestId()).stream()
                .map(questionMapper::toSolveResponse)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(testQuestions);
    }

    @PutMapping("/{resultId}")
    public ResponseEntity<ResultResponse> terminateSolvingTest(@PathVariable Long resultId) {
        Result terminatedResult = resultService.updateResult(resultId);
        ResultResponse response = resultMapper.entityToResponse(terminatedResult);
        return ResponseEntity.ok(response);
    }
}
