package org.combs.micro.hc_tests_service.mapper;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.request.ResultRequest;
import org.combs.micro.hc_tests_service.response.ResultResponse;
import org.combs.micro.hc_tests_service.service.SchoolTestService;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;


@Component
@RequiredArgsConstructor
public class ResultMapper {
    private final SchoolTestService testService;

    public ResultResponse entityToResponse(Result result){
        return ResultResponse.builder()
                .testTitle(result.getSchoolTest().getTitle())
                .score(result.getScore())
                .rankScore(result.getRankScore())
                .build();
    }
    public Result requestToResult(ResultRequest request){
        SchoolTest schoolTest = testService.getTestById(request.getTestId());
        return Result.builder()
                .studentId(request.getStudentId())
                .schoolTest(schoolTest)
                .score(request.getScore())
                .rankScore(request.getRankScore())
                .build();
    }

}
