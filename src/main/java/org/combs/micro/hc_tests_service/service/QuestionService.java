package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.enums.QuestionType;
import org.combs.micro.hc_tests_service.exeptions.existsException.QuestionByThisDescriptionExistsInThisTest;
import org.combs.micro.hc_tests_service.mapper.QuestionMapper;
import org.combs.micro.hc_tests_service.repository.QuestionRepository;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final SchoolTestService testService;
    private final QuestionMapper questionMapper;


    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
    }

    public Question createQuestion(QuestionRequest request) {
        /*
         * todo
         *  wath how to  open session for creating question
         *  and take ability to save question with current test id
         * */
        Question question = questionMapper.toCreateEntity(request);
        return questionRepository.save(question);
    }

    private void checkQuestionExistsInTestByDescription(Long testId, String questionDescription) {
        if (questionRepository.existsByDescriptionAndTestId(questionDescription, testId)) {
            throw new QuestionByThisDescriptionExistsInThisTest("Question by this description already exists");
        }
        ;
    }

    public QuestionResponse addQuestionToTest(Long testId, QuestionRequest questionRequest) {
        SchoolTest test = testService.getTestById(testId);
        Question question = questionMapper.toCreateEntity(questionRequest);

        checkQuestionExistsInTestByDescription(testId, questionRequest.getDescription());

        question.setTest(test);

        return questionMapper.toResponse(questionRepository.save(question));
    }

    public QuestionResponse updateQuestionInTest(Long id, Long testId, QuestionRequest request) {
        // todo check if test consist this qquesiton
        Question question = getQuestionById(id);

        // Если описание запроса и вопроса из бд отличается,
        // проверяем нет ли такого же описания  у других вопросов теста
        if (!(request.getDescription().equals(question.getDescription()))) {
            checkQuestionExistsInTestByDescription(testId, question.getDescription());
        }

        questionMapper.updateEntityFromRequest(question, request);

        return questionMapper.toResponse(questionRepository.save(question));

    }
}