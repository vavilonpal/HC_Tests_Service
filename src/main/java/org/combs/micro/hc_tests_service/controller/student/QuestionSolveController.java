package org.combs.micro.hc_tests_service.controller.student;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.mapper.QuestionMapper;
import org.combs.micro.hc_tests_service.response.SolveQuestionResponse;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/test/solve")
@RequiredArgsConstructor
public class QuestionSolveController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    // Get question for giving on answer
    @GetMapping("/{testId}/question/{questionId}")
    public ResponseEntity<SolveQuestionResponse> getQuestionForSolving(@PathVariable Long testId,
                                                                       @PathVariable Long questionId) {
        Question question = questionService.getQuestionByIdAndTestId(questionId, testId);
        SolveQuestionResponse solveResponse = questionMapper.toSolveResponse(question);
        return ResponseEntity.ok(solveResponse);
    }
    @GetMapping("/{testId}/question")
    public ResponseEntity<List<SolveQuestionResponse>> getQuestionForSolving(@PathVariable Long testId){
        List<SolveQuestionResponse> solveQuestionResponses = questionService.getQuestionsByTestId(testId).stream()
                .map(questionMapper::toSolveResponse)
                .toList();

        return ResponseEntity.ok(solveQuestionResponses);
    }

}
