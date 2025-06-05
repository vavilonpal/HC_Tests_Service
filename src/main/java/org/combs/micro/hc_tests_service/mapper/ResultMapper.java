package org.combs.micro.hc_tests_service.mapper;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.entity.User;
import org.combs.micro.hc_tests_service.request.ResultRequest;
import org.combs.micro.hc_tests_service.response.ResultResponse;
import org.combs.micro.hc_tests_service.service.SchoolTestService;
import org.combs.micro.hc_tests_service.service.UserService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResultMapper {
    private final SchoolTestService testService;
    private final UserService userService;

    public ResultResponse entityToResponse(Result result){
        return ResultResponse.builder()
                .testTitle(result.getSchoolTest().getTitle())
                .score(result.getScore())
                .rankScore(result.getRankScore())
                .build();
    }
    public Result requestToResult(ResultRequest request){
        SchoolTest schoolTest = testService.getTestById(request.getTestId());
        User student = userService.getUserById(request.getStudentId());
        return Result.builder()
                .student(student)
                .schoolTest(schoolTest)
                .score(request.getScore())
                .rankScore(request.getRankScore())
                .build();
    }

}
