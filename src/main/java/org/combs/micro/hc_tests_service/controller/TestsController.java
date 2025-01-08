package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestsController {
    private final TestService testService;

    @PostMapping
    public ResponseEntity<?> createTest(@RequestBody @Validated SchoolTest schoolTest,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        SchoolTest newSchoolTest = testService.createTest(schoolTest);
        return ResponseEntity.status(HttpStatus.CREATED).body(schoolTest);
    }
    @GetMapping
    public ResponseEntity<List<SchoolTest>> getAllTests(){
        List<SchoolTest> schoolTests = testService.getAllTests();

        if (schoolTests.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schoolTests);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SchoolTest> getTestById(@PathVariable Long id){
        return testService.getTestById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @GetMapping("/class-lvl/{level}")
    public ResponseEntity<List<SchoolTest>> getTestsByClassLevel(@PathVariable Short level){
        List<SchoolTest> schoolTests = testService.getTestsByClassLevel(level);
        if (schoolTests.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schoolTests);
    }

    //todo cehck working with enum variable in path
    @GetMapping("/complexity/{complexity}")
    public ResponseEntity<List<SchoolTest>> getTestsByComplexity(@PathVariable Complexity complexity){
        List<SchoolTest> schoolTests = testService.getTestsByComplexity(complexity);
        if (schoolTests.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schoolTests);
    }
    @GetMapping("/school-subject/{subject}")
    public ResponseEntity<List<SchoolTest>> getTestsBySchoolSubject(@PathVariable String subject){
        List<SchoolTest> schoolTests = testService.getTestsBySchoolSubject(subject);
        if (schoolTests.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schoolTests);
    }

}
