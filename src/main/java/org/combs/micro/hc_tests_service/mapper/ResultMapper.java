package org.combs.micro.hc_tests_service.mapper;

import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.request.ResultRequest;
import org.combs.micro.hc_tests_service.response.ResultResponse;
import org.springframework.stereotype.Component;


@Component
public class ResultMapper {

    public ResultResponse entityToResponse(Result result){
        return ResultResponse.builder()
                .testTitle(result.getSchoolTest().getTitle())
                .score(result.getScore())
                .rankScore(result.getRankScore())
                .build();
    }
    public Result requestToResult(ResultRequest request){
        return Result.builder()
                .studentId(request.getStudentId())
                .score(request.getScore())
                .rankScore(request.getRankScore())
                .build();
    }
}
