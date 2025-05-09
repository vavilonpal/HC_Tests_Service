package org.combs.micro.hc_tests_service.controller;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.mapper.AnswerMapper;
import org.combs.micro.hc_tests_service.mapper.QuestionMapper;
import org.combs.micro.hc_tests_service.mapper.ResultMapper;
import org.combs.micro.hc_tests_service.repository.cacheRepository.AnswerCacheRepository;
import org.combs.micro.hc_tests_service.request.AnswerRequest;
import org.combs.micro.hc_tests_service.request.ResultRequest;
import org.combs.micro.hc_tests_service.response.AnswerResponse;
import org.combs.micro.hc_tests_service.response.ResultResponse;
import org.combs.micro.hc_tests_service.response.SolveQuestionResponse;
import org.combs.micro.hc_tests_service.service.AnswerService;
import org.combs.micro.hc_tests_service.service.QuestionService;
import org.combs.micro.hc_tests_service.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/test-solve")
@RequiredArgsConstructor
/*
  Контроллер отвечает за отправку ответов на вопросы
  И получение вопросов для решения
 */
public class SolveTestController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;
    private final ResultService resultService;
    private final ResultMapper resultMapper;
    private final AnswerCacheRepository answerCacheRepository;

    @PostMapping
    public ResponseEntity<Set<SolveQuestionResponse>> startTestSolving(@RequestBody ResultRequest resultRequest){
        Result result = resultService.createResult(resultRequest);

        Set<SolveQuestionResponse> testQuestions = questionService.getAllTestQuestions(resultRequest.getTestId()).stream()
                .map(questionMapper::toSolveResponse)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(testQuestions);
    }
    @GetMapping("/{testId}/question/{questionId}")
    public ResponseEntity<SolveQuestionResponse> getQuestionForSolving(@PathVariable Long testId,
                                                                       @PathVariable Long questionId) {
        Question question = questionService.getQuestionByIdAndTestId(questionId, testId);
        SolveQuestionResponse solveResponse = questionMapper.toSolveResponse(question);
        return ResponseEntity.ok(solveResponse);
    }

    /**
     * Сохраняем ответ
     * @param resultId - id резульатата к которому будет относится этот ответ
     * @param request - запрос с ответом
     * @return
     */
    @PostMapping("/{resultId}/answer")
    public ResponseEntity<AnswerResponse> sendAnswer(@PathVariable Long resultId,
                                                     @RequestBody AnswerRequest request) {
        request.setResultId(resultId);
        Answer answer = answerService.createAnswer(request);
        answerCacheRepository.save(answer);
        AnswerResponse response = answerMapper.answerToResponse(answer);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity<AnswerResponse> getAnswer (@PathVariable Long id){

        AnswerResponse cachedAnswerResponse = answerCacheRepository.findById(id);
        if (cachedAnswerResponse != null){
            System.out.println("fgfggg");

            return ResponseEntity.ok(cachedAnswerResponse);
        }

        Answer answer = answerService.getAnswerById(id);
        AnswerResponse response = answerMapper.answerToResponse(answer);
        return ResponseEntity.ok(response);
    }
    /**
     * Обновление отправленного ответа
     * @param answerId
     * @param request
     * @return
     */
    @PutMapping("/{resultId}/answer/{answerId}")
    public ResponseEntity<AnswerResponse> updateAnswer(@PathVariable Long resultId,
                                                       @PathVariable Long answerId,
                                                       @RequestBody AnswerRequest request) {
        request.setResultId(resultId);
        Answer answer = answerService.updateAnswer(answerId,request);
        AnswerResponse response = answerMapper.answerToResponse(answer);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{resultId}")
    public ResponseEntity<ResultResponse> terminateSolvingTest(@PathVariable Long resultId){
        Result terminatedResult = resultService.updateResult(resultId);
        ResultResponse response = resultMapper.entityToResponse(terminatedResult);
        return ResponseEntity.ok(response);
    }
}
