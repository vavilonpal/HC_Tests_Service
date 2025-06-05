package org.combs.micro.hc_tests_service.controller.student;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.mapper.ResultMapper;
import org.combs.micro.hc_tests_service.response.ResultResponse;
import org.combs.micro.hc_tests_service.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/results")
@RequiredArgsConstructor
public class StudentResultController {
    private final ResultMapper resultMapper;
    private final ResultService resultService;
    //todo result by stedent id


    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getResultById(@PathVariable Long id){
        ResultResponse response = resultMapper.entityToResponse(resultService.getResultById(id));
        return ResponseEntity.ok(response);
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ResultResponse>> getStudentResults(@PathVariable Long studentId){
        List<ResultResponse> studentAllResults = resultService.getStudentAllResults(studentId).stream()
                .map(resultMapper::entityToResponse)
                .toList();

        return ResponseEntity.ok(studentAllResults);
    }

  /*  @GetMapping("/student/{studentId}/result/{resultId}")
    public ResponseEntity<ResultResponse> getResultByStudentIdAndTestId(@PathVariable Long studentId,
                                                                        @PathVariable Long testId){
        String username = "DD";
        resultService.getReusltByIdAndStudentName(studentId, username);
        return ResponseEntity.badRequest();
    }*/
}
