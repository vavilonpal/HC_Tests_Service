package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question updatedQuestion) {
        return questionRepository.findById(id).map(question -> {
            question.setTeacherId(updatedQuestion.getTeacherId());
            question.setSchoolSubject(updatedQuestion.getSchoolSubject());
            question.setDescription(updatedQuestion.getDescription());
            question.setAnswer(updatedQuestion.getAnswer());
            question.setType(updatedQuestion.getType());
            question.setDifficulty(updatedQuestion.getDifficulty());
            question.setRankPoints(updatedQuestion.getRankPoints());
            return questionRepository.save(question);
        }).orElseThrow(() -> new EntityNotFoundException("Question not found"));
    }

    public List<Question> getQuestionsBySubject(SchoolSubject subject){
        return questionRepository.findAllBySchoolSubject(subject);
    }
}