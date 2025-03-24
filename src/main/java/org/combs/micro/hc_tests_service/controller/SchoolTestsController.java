package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.mapper.SchoolTestMapper;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.combs.micro.hc_tests_service.response.SchoolTestInfoResponse;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.combs.micro.hc_tests_service.service.SchoolSubjectService;
import org.combs.micro.hc_tests_service.service.SchoolTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class SchoolTestsController {
    private final SchoolTestService schoolTestService;
    private final QuestionService questionService;
    private final SchoolTestMapper testMapper;

    @GetMapping
    public ResponseEntity<List<SchoolTestInfoResponse>> getAllTests() {
        List<SchoolTestInfoResponse> testInfoResponses = schoolTestService.getAllTests().stream()
                .map(testMapper::toInfoResponse)
                .toList();
        return ResponseEntity.ok(testInfoResponses);
    }
    //todo Shows test image for teacher

    // Shows student info about test
    @GetMapping("/info/{id}")
    public ResponseEntity<SchoolTestInfoResponse> getTestInfoById(@PathVariable Long id) {
        SchoolTestInfoResponse testInfoResponse = testMapper.toInfoResponse(schoolTestService.getTestById(id));
        return ResponseEntity.ok(testInfoResponse);
    }

    @PostMapping
    public ResponseEntity<SchoolTestInfoResponse> createTest(@RequestBody @Valid SchoolTestRequest request) {
        SchoolTest test = schoolTestService.createTest(request);
        SchoolTestInfoResponse response = testMapper.toInfoResponse(test);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    @PostMapping("/{testId}/questions")
    public ResponseEntity<QuestionResponse> addQuestionToTest(@PathVariable Long testId,
                                                              @RequestBody QuestionRequest questionRequest){
        QuestionResponse response = questionService.addQuestionToTest(testId, questionRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @PutMapping("/{testId}/questions/{questionId}")
    public ResponseEntity<QuestionResponse> updateQuestionInTest(@PathVariable Long questionId,
                                                                 @PathVariable Long testId,
                                                              @RequestBody QuestionRequest questionRequest){
        QuestionResponse response = questionService.updateQuestionInTest(questionId, testId,questionRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolTestInfoResponse> updateTest(@PathVariable Long id,
                                        @RequestBody @Valid SchoolTestRequest request){
        SchoolTest updatedTest = schoolTestService.updateTest(id,request);
        SchoolTestInfoResponse response = testMapper.toInfoResponse(updatedTest);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable Long id){
        schoolTestService.deleteTest(id);

        return ResponseEntity.ok("Test successfully deleted");
    }

}
