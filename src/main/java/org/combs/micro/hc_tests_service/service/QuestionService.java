package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.exeptions.notFound.QuestionNotFoundException;
import org.combs.micro.hc_tests_service.exeptions.existsException.QuestionByThisDescriptionExistsInThisTest;
import org.combs.micro.hc_tests_service.mapper.QuestionMapper;
import org.combs.micro.hc_tests_service.repository.QuestionRepository;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final SchoolTestService testService;
    private final QuestionMapper questionMapper;



    public List<Question> getAllTestQuestions(Long testId){
        return questionRepository.getQuestionsByTestId(testId);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
    }

    public Question getQuestionByIdAndTestId(Long id, Long testId) {
        return questionRepository.getQuestionsByIdAndTestId(id, testId)
                .orElseThrow(() -> new QuestionNotFoundException("Question by this id and test-id doesnt exists"));
    }


    private void checkQuestionExistsInTestByDescription(Long testId, String questionDescription) {
        if (questionRepository.existsByDescriptionAndTestId(questionDescription, testId)) {
            throw new QuestionByThisDescriptionExistsInThisTest("Question by this description already exists");
        }
        ;
    }

    public Question addQuestionToTest(Long testId, QuestionRequest questionRequest) {
        SchoolTest test = testService.getTestById(testId);
        Question question = questionMapper.toCreateEntity(questionRequest);

        checkQuestionExistsInTestByDescription(testId, questionRequest.getDescription());

        question.setTest(test);

        return questionRepository.save(question);
    }

    public Question updateQuestionInTest(Long id, Long testId, QuestionRequest request) {
        Question question = getQuestionByIdAndTestId(id, testId);

        // Если описание запроса и вопроса из бд отличается,
        // проверяем нет ли такого же описания  у других вопросов теста
        if (!(request.getDescription().equals(question.getDescription()))) {
            checkQuestionExistsInTestByDescription(testId, request.getDescription());
        }

        questionMapper.updateEntityFromRequest(question, request);

        return questionRepository.save(question);
    }
    public void  deleteQuestion(Long id, Long testId){
        Question question = getQuestionByIdAndTestId(id, testId);
        questionRepository.delete(question);
    }
}