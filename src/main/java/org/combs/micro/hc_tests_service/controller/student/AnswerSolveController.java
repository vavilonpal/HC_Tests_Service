package org.combs.micro.hc_tests_service.controller.student;


import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.mapper.AnswerMapper;
import org.combs.micro.hc_tests_service.repository.cacheRepository.AnswerCacheRepository;
import org.combs.micro.hc_tests_service.request.AnswerRequest;
import org.combs.micro.hc_tests_service.response.AnswerResponse;
import org.combs.micro.hc_tests_service.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/test/solve")
@RequiredArgsConstructor
public class AnswerSolveController {

    private final AnswerMapper answerMapper;
    private final AnswerService answerService;
    private final AnswerCacheRepository answerCacheRepository;

    /**
     * Сохраняем ответ
     *
     * @param resultId - id резульатата к которому будет относится этот ответ
     * @param request  - запрос с ответом
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
    public ResponseEntity<AnswerResponse> getAnswer(@PathVariable Long id) {

        AnswerResponse cachedAnswerResponse = answerCacheRepository.findById(id);
        if (cachedAnswerResponse != null) {
            System.out.println("fgfggg");

            return ResponseEntity.ok(cachedAnswerResponse);
        }

        Answer answer = answerService.getAnswerById(id);
        AnswerResponse response = answerMapper.answerToResponse(answer);
        return ResponseEntity.ok(response);
    }

    /**
     * Обновление отправленного ответа
     *
     * @param answerId
     * @param request
     * @return
     */
    @PutMapping("/{resultId}/answer/{answerId}")
    public ResponseEntity<AnswerResponse> updateAnswer(@PathVariable Long resultId,
                                                       @PathVariable Long answerId,
                                                       @RequestBody AnswerRequest request) {
        request.setResultId(resultId);
        Answer answer = answerService.updateAnswer(answerId, request);
        AnswerResponse response = answerMapper.answerToResponse(answer);
        return ResponseEntity.ok(response);
    }
}
