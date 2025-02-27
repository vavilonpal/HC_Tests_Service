package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.mapper.SchoolTestMapper;
import org.combs.micro.hc_tests_service.request.SchoolTestRequest;
import org.combs.micro.hc_tests_service.response.SchoolTestInfoResponse;
import org.combs.micro.hc_tests_service.service.SchoolSubjectService;
import org.combs.micro.hc_tests_service.service.SchoolTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class SchoolTestsController {
    private final SchoolTestService schoolTestService;
    private final SchoolTestMapper testMapper;

    @GetMapping
    public ResponseEntity<List<SchoolTestInfoResponse>> getAllTests() {
        List<SchoolTestInfoResponse> testInfoResponses = schoolTestService.getAllTests().stream()
                .map(testMapper::toInfoResponse)
                .toList();
        return ResponseEntity.ok(testInfoResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolTestInfoResponse> getTestInfoById(@PathVariable Long id) {
        SchoolTestInfoResponse testInfoResponse = testMapper.toInfoResponse(schoolTestService.getTestById(id));
        return ResponseEntity.ok(testInfoResponse);
    }

    @PostMapping
    public ResponseEntity<?> createTest(@RequestBody SchoolTestRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        SchoolTestInfoResponse response = testMapper.toInfoResponse(
                schoolTestService.createTest(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTest(@PathVariable Long id,
                                        @RequestBody SchoolTestRequest request,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        SchoolTestInfoResponse response = testMapper.toInfoResponse(schoolTestService.updateTest(id,request));
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable Long id){
        schoolTestService.deleteTest(id);

        return ResponseEntity.ok("Test successfully deleted");
    }

}
