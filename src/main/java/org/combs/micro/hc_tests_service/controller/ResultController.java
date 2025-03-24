package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.mapper.ResultMapper;
import org.combs.micro.hc_tests_service.request.ResultRequest;
import org.combs.micro.hc_tests_service.response.ResultResponse;
import org.combs.micro.hc_tests_service.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/results")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;
    private final ResultMapper resultMapper;


    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getResultById(@PathVariable Long id){
        ResultResponse response = resultMapper.entityToResponse(resultService.getResultById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ResultResponse>> getResultsByStudentId(@PathVariable Long studentId){
        List<ResultResponse> studentResults = resultService.getStudentAllResults(studentId).stream()
                .map(resultMapper::entityToResponse)
                .toList();
        return ResponseEntity.ok(studentResults);
    }

    @PostMapping
    public ResponseEntity<ResultResponse> createResult(@RequestBody ResultRequest resultRequest){
        return ResponseEntity.ok(ResultResponse.builder().build());
    }


}
