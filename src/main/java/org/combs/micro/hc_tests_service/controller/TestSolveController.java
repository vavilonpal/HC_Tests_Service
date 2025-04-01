package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.mapper.AnswerMapper;
import org.combs.micro.hc_tests_service.mapper.QuestionMapper;
import org.combs.micro.hc_tests_service.request.AnswerRequest;
import org.combs.micro.hc_tests_service.response.AnswerResponse;
import org.combs.micro.hc_tests_service.response.SolveQuestionResponse;
import org.combs.micro.hc_tests_service.service.AnswerService;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test/solve")
@RequiredArgsConstructor
/**
 * Контроллер отвечает за отправку ответов на вопросы
 * И получение вопросов для решения
 */
public class TestSolveController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    @GetMapping("/{testId}/{questionId}")
    public ResponseEntity<SolveQuestionResponse> getQuestionForSolving(@PathVariable Long testId,
                                                                       @PathVariable Long questionId) {
        Question question = questionService.getQuestionByIdAndTestId(questionId, testId);
        SolveQuestionResponse solveResponse = questionMapper.toSolveResponse(question);
        return ResponseEntity.ok(solveResponse);
    }
    @PostMapping("/{testId}/{questionId}")
    public ResponseEntity<AnswerResponse> sendAnswer(@PathVariable Long testId,
                                                                   @PathVariable Long questionId,
                                                                   @RequestBody AnswerRequest request) {
        Answer answer = answerService.createAnswer(request);
        AnswerResponse response = answerMapper.answerToResponse(answer);
        return ResponseEntity.ok(response);
    }


    /**
     * Обновление отправленного ответа
     * @param testId
     * @param answerId
     * @param request
     * @return
     */
    @PutMapping("/{testId}/{answerId}")
    public ResponseEntity<AnswerResponse> updateAnswer(@PathVariable Long testId,
                                                       @PathVariable Long answerId,
                                                       @RequestBody AnswerRequest request) {
        Answer answer = answerService.updateAnswer(answerId,request);
        AnswerResponse response = answerMapper.answerToResponse(answer);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/answer/{id}")
    public ResponseEntity<AnswerResponse> getAnswer (@PathVariable Long id){
        Answer answer = answerService.getAnswerById(id);
        AnswerResponse response = answerMapper.answerToResponse(answer);
        return ResponseEntity.ok(response);
    }
}
