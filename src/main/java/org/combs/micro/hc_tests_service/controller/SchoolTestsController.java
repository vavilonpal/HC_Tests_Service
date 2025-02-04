package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.combs.micro.hc_tests_service.response.SchoolTestResponse;
import org.combs.micro.hc_tests_service.mapper.SchoolTestMapper;
import org.combs.micro.hc_tests_service.service.SchoolTestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class SchoolTestsController {
    private final SchoolTestService schoolTestService;
    private final SchoolTestMapper testMapper;


    @GetMapping
    public ResponseEntity<Page<SchoolTestResponse>> getAllTests(Pageable pageable,
                                                                @RequestParam(required = false) SchoolSubject schoolSubject,
                                                                @RequestParam(required = false) Integer complexity,
                                                                @RequestParam(required = false) String classLevel,
                                                                @RequestParam(required = false) Long teacherId) {
        Page<SchoolTestResponse> schoolTestResponses = schoolTestService.getAllPageableTests(pageable, schoolSubject, complexity, classLevel, teacherId);

        if (schoolTestResponses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(schoolTestResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolTestResponse> getTestById(@PathVariable Long id) {
        SchoolTest test = schoolTestService.getTestById(id);
        return ResponseEntity.ok(testMapper.toResponse(test));
    }

    @PostMapping
    public ResponseEntity<?> createTest(@RequestBody @Valid SchoolTestRequest testRequest,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        schoolTestService.createTest(testRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(testRequest);
    }

}
