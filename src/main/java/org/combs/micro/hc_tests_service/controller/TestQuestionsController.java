package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.mapper.QuestionMapper;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.combs.micro.hc_tests_service.response.DeleteResponse;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestQuestionsController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;


    @GetMapping("/{testId}/questions")
    public ResponseEntity<List<QuestionResponse>> getTestQuestions(@PathVariable Long testId) {

        List<QuestionResponse> questionResponses = questionService.getAllTestQuestions(testId).stream()
                .map(questionMapper::toResponse)
                .toList();
        return ResponseEntity.ok(questionResponses);
    }

    @GetMapping("/{testId}/questions/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionInTestById(@PathVariable Long testId,
                                                                  @PathVariable Long questionId) {
        Question question = questionService.getQuestionByIdAndTestId(questionId, testId);
        QuestionResponse response = questionMapper.toResponse(question);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{testId}/questions")
    public ResponseEntity<QuestionResponse> addQuestionToTest(@PathVariable Long testId,
                                                              @RequestBody QuestionRequest questionRequest) {
        Question question = questionService.addQuestionToTest(testId, questionRequest);
        QuestionResponse response = questionMapper.toResponse(question);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{testId}/questions/{questionId}")
    public ResponseEntity<QuestionResponse> updateQuestionInTest(@PathVariable Long testId,
                                                                 @PathVariable Long questionId,
                                                                 @RequestBody QuestionRequest questionRequest) {
        Question question = questionService.updateQuestionInTest(questionId, testId, questionRequest);
        QuestionResponse response = questionMapper.toResponse(question);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{testId}/questions/{questionId}")
    public ResponseEntity<DeleteResponse> deleteQuestion(@PathVariable Long testId,
                                                         @PathVariable Long questionId) {
        questionService.deleteQuestion(questionId, testId);
        DeleteResponse deleteResponse = new DeleteResponse("Successful Delete!");
        return ResponseEntity.ok(deleteResponse);
    }


}
