package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.mapper.QuestionMapper;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;


    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable Long id) {
        QuestionResponse response = questionMapper
                .toResponse(questionService.getQuestionById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionRequest request){
        QuestionResponse response = questionMapper.toResponse(questionService
                .createQuestion(request));

        return ResponseEntity.ok(response);
    }
}
