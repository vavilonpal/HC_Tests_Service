package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
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
    private final TestService testService;


    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id){
        return questionService.getQuestionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }
    /*@GetMapping
    public ResponseEntity<List<Question>> getQuestionByTest(@RequestParam(name = "test") Long id){
        if(!testService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        //todo ------------------------
        return ResponseEntity.notFound().build();
    }*/


    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody @Validated Question question,
                                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.ok(bindingResult);
        }
        questionService.createQuestion(question);
        return ResponseEntity.ok().build();
    }

}
