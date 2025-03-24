package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.client.StudentServiceClient;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.exeptions.ResultNotFoundException;
import org.combs.micro.hc_tests_service.exeptions.StudentNotFoundException;
import org.combs.micro.hc_tests_service.exeptions.TeacherNotFoundException;
import org.combs.micro.hc_tests_service.mapper.ResultMapper;
import org.combs.micro.hc_tests_service.repository.ResultRepository;
import org.combs.micro.hc_tests_service.request.ResultRequest;
import org.combs.micro.hc_tests_service.response.ResultResponse;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultMapper resultMapper;
    private final ResultRepository resultRepository;
    private StudentServiceClient studentServiceClient;

    public Result getResultById(Long id) {
        return resultRepository.findById(id).orElseThrow(() -> new ResultNotFoundException("Result not found"));
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Result saveResult(ResultRequest resultRequest) {
        Result result = resultMapper.requestToResult(resultRequest);
        return resultRepository.save(result);
    }

    public Result updateResult(Long id, ResultRequest updatedResult) {
        Result result = getResultById(id);

        result.setScore(updatedResult.getScore());
        return resultRepository.save(result);

    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    public List<Result> getStudentAllResults(Long studentId) {
        checkStudentExists(studentId);

        return resultRepository.findAllByStudentId(studentId);
    }

    private  void checkStudentExists(Long studentId){
        boolean studentExist = studentServiceClient.existById(studentId);

        if(!studentExist){
            throw new StudentNotFoundException("Ученик ID " + studentId + " не найден");
        }
    }
}
