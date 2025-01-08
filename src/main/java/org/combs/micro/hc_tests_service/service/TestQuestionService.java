package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.TestQuestion;
import org.combs.micro.hc_tests_service.repository.TestQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestQuestionService {
    private final TestQuestionRepository testQuestionRepository;

    public List<TestQuestion> getAllTestQuestions() {
        return testQuestionRepository.findAll();
    }

    public Optional<TestQuestion> getTestQuestionById(Long id) {
        return testQuestionRepository.findById(id);
    }

    public TestQuestion createTestQuestion(TestQuestion testQuestion) {
        return testQuestionRepository.save(testQuestion);
    }

    public TestQuestion updateTestQuestion(Long id, TestQuestion updatedTestQuestion) {
        return testQuestionRepository.findById(id).map(testQuestion -> {
            testQuestion.setSchoolTest(updatedTestQuestion.getSchoolTest());
            testQuestion.setQuestion(updatedTestQuestion.getQuestion());
            testQuestion.setOrder(updatedTestQuestion.getOrder());
            testQuestion.setTestPoints(updatedTestQuestion.getTestPoints());
            testQuestion.setRankPoints(updatedTestQuestion.getRankPoints());
            return testQuestionRepository.save(testQuestion);
        }).orElseThrow(() -> new RuntimeException("TestQuestion not found"));
    }

    public void deleteTestQuestion(Long id) {
        testQuestionRepository.deleteById(id);
    };
}
