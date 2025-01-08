package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/results")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @GetMapping
    public ResponseEntity<List<Result>> getAllResults() {
        List<Result> results = resultService.getAllResults();
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResult(@PathVariable Long id) {
        return resultService.getResultById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Result>> getResultByStudentId(@PathVariable Long studentId) {
        List<Result> results = resultService.getResultsByStudentId(studentId);
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }
    @PostMapping
    public ResponseEntity<Result> saveResult(@RequestBody Result result){
        resultService.saveResult(result);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Long id, @RequestBody Result updatedResult) {
        return resultService.getResultById(id)
                .map(existingResult -> {

                    existingResult.setSchoolTest(updatedResult.getSchoolTest());
                    existingResult.setStudentId(updatedResult.getStudentId());
                    existingResult.setScore(updatedResult.getScore());
                    existingResult.setRankScore(updatedResult.getRankScore());
                    existingResult.setStartedAt(updatedResult.getStartedAt());
                    existingResult.setFinishedAt(updatedResult.getFinishedAt());

                    Result savedResult = resultService.saveResult(existingResult);
                    return ResponseEntity.ok(savedResult);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
