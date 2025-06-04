package org.combs.micro.hc_tests_service.controller.student;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.response.ResultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/results")
@RequiredArgsConstructor
public class StudentResultController {
        //todo result by stedent id

    @GetMapping("/{id}")
    public ResponseEntity<ResultResponse> getResultById(@PathVariable Long id){
        ResultResponse response = resultMapper.entityToResponse(resultService.getResultById(id));
        return ResponseEntity.ok(response);
    }
}
