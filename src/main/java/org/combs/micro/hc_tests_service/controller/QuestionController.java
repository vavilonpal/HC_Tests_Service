package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.combs.micro.hc_tests_service.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getALlQuestions(){
        List<Question> questions = questionService.getAllQuestions();
        if (questions.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(questions);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id){
        return questionService.getQuestionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.badRequest().build());
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
    public ResponseEntity<Question> updateResult(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        return ResponseEntity.ok(questionService.updateQuestion(id, updatedQuestion));

    }

    @GetMapping("/school-subject/{subject}")
    public ResponseEntity<List<Question>> getTestsBySchoolSubject(@PathVariable SchoolSubject subject){
        List<Question> questions = questionService.getQuestionsBySubject(subject);
        if (questions.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questions);
    }
}
