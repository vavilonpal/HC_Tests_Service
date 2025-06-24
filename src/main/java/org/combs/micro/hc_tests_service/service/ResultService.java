package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Answer;
import org.combs.micro.hc_tests_service.entity.Result;
import org.combs.micro.hc_tests_service.exeptions.AttemptTimeSolveExpireException;
import org.combs.micro.hc_tests_service.exeptions.notFound.ResultNotFoundException;
import org.combs.micro.hc_tests_service.mapper.ResultMapper;
import org.combs.micro.hc_tests_service.repository.ResultRepository;
import org.combs.micro.hc_tests_service.request.ResultRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultMapper resultMapper;
    private final ResultRepository resultRepository;

    //todo add checking user assign to result

    public void checkTestSolvingTimeExpire(Result result) {
        if (result.getFinishedAt() != null) {
            throw new AttemptTimeSolveExpireException("Test solve time is expire");
        }
    }
    public Result getResultById(Long id) {
        return resultRepository.findById(id).orElseThrow(() -> new ResultNotFoundException("Result not found"));
    }

    public Result createResult(ResultRequest resultRequest) {
        Result result = resultMapper.requestToResult(resultRequest);
        return resultRepository.save(result);
    }

    public Result updateResult(Long id) {
        Result result = getResultById(id);

        if (result.getFinishedAt() !=  null){
            throw  new AttemptTimeSolveExpireException("Time to solve test is expire");
        }
        result.setFinishedAt(LocalDateTime.now());

        calculateResultPoints(result);

        return resultRepository.save(result);

    }

    public void calculateResultPoints(Result result) {
        if (result == null || result.getAnswers() == null || result.getAnswers().isEmpty()) {
            assert result != null;
            result.setRankScore(0);
            result.setScore(0);
            return;
        }

        List<Answer> answers = result.getAnswers();

        Integer rankPoints = answers.stream()
                .filter(Objects::nonNull)
                .map(Answer::getRankPoints)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();

        Integer scorePoints = answers.stream()
                .filter(Objects::nonNull)
                .map(Answer::getScorePoints)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();

        result.setRankScore(rankPoints);
        result.setScore(scorePoints);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    public List<Result> getStudentAllResults(Long studentId) {
        return resultRepository.getResultsByStudentId(studentId);

    }



    public void getReusltByIdAndStudentName(Long studentId, String username) {
    }

    //todo not work
    public void finishTestIfNotFinished(Long resultId) {
        Result result = resultRepository.findById(resultId).orElseThrow();
            result.setFinishedAt(LocalDateTime.now());
            resultRepository.save(result);
        }
}
