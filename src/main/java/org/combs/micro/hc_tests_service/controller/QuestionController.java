package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.converter.QuestionTypeConverter;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.enums.QuestionType;
import org.combs.micro.hc_tests_service.mapper.QuestionMapper;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.combs.micro.hc_tests_service.service.SchoolSubjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final SchoolSubjectService schoolSubjectService;
    private final QuestionTypeConverter questionTypeConverter;

    @GetMapping
    public ResponseEntity<Page<QuestionResponse>> getALlQuestions(Pageable pageable,
                                                          @RequestParam(required = false)String questionType,
                                                          @RequestParam(required = false)Integer rankPoints,
                                                          @RequestParam(required = false)Integer difficulty,
                                                          @RequestParam(required = false)String subjectName
                                                          ){
        QuestionType type = questionTypeConverter.convert(questionType);
        SchoolSubject schoolSubject = schoolSubjectService.getSubjectByName(subjectName);

        Page<QuestionResponse> questions = questionService.getPageableQuestions(pageable, type,rankPoints,difficulty,schoolSubject);

        return ResponseEntity.ok(questions);
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable Long id){
        QuestionResponse questionResponse = questionMapper.toResponse(questionService.getQuestionById(id));

        return ResponseEntity.ok(questionResponse);
    }

    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody @Validated Question question,
                                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        questionService.createQuestion(question);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResult(@PathVariable Long id,
                                                         @RequestBody @Valid QuestionRequest request,
                                                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors());
        }

        QuestionResponse response = questionService.updateQuestion(id, request);

        return ResponseEntity.ok(response);

    }
}
