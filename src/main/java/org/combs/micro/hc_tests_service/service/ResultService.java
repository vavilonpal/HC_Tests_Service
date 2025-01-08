package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Optional<Result> getResultById(Long id) {
        return resultRepository.findById(id);
    }
    public List<Result> getResultsByStudentId(Long studentId){
        return resultRepository.getResultsByStudentId(studentId);
    }
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    public Result updateResult(Long id, Result updatedResult) {
        return resultRepository.findById(id).map(result -> {
            result.setSchoolTest(updatedResult.getSchoolTest());
            result.setStudentId(updatedResult.getStudentId());
            result.setScore(updatedResult.getScore());
            result.setStartedAt(updatedResult.getStartedAt());
            result.setFinishedAt(updatedResult.getFinishedAt());
            return resultRepository.save(result);
        }).orElseThrow(() -> new RuntimeException("Result not found"));
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}
