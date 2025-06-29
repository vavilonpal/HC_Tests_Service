package org.combs.micro.hc_tests_service.controller.teacher;


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
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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
    //todo Shows test image for teacher

    @GetMapping("/{teacherId}")
    public ResponseEntity<List<SchoolTestInfoResponse>> getAllTestsByTeacher(@PathVariable Long teacherId) {

        List<SchoolTestInfoResponse> testInfoResponses = schoolTestService.getAllTestsByTeacherId(teacherId).stream()
                .map(testMapper::toInfoResponse)
                .toList();
        return ResponseEntity.ok(testInfoResponses);
    }


    @PostMapping
    public ResponseEntity<SchoolTestInfoResponse> createTest(@RequestBody @Valid SchoolTestRequest request) {
        SchoolTest test = schoolTestService.createTest(request);
        SchoolTestInfoResponse response = testMapper.toInfoResponse(test);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
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
