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

import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class ResultMapper {
    private final SchoolTestService testService;
    private final UserService userService;

    public ResultResponse entityToResponse(Result result) {
        Integer solveTime = null;
        if (result.getStartedAt() != null && result.getFinishedAt() != null) {
            solveTime = Math.toIntExact(ChronoUnit.MINUTES.between(
                    result.getStartedAt(), result.getFinishedAt()));
        }

        return ResultResponse.builder()
                .id(result.getId())
                .testTitle(result.getSchoolTest() != null ? result.getSchoolTest().getTitle() : null)
                .score(result.getScore())
                .rankScore(result.getRankScore())
                .solveTime(solveTime)
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
