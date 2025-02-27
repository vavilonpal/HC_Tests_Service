package org.combs.micro.hc_tests_service.facade;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.service.AnswerService;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.combs.micro.hc_tests_service.service.ResultService;
import org.combs.micro.hc_tests_service.service.SchoolTestService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SchoolTestSolveFacade {
    private final SchoolTestService schoolTestService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ResultService resultService;
}
