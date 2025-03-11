package org.combs.micro.hc_tests_service.service;

import lombok.RequiredArgsConstructor;
import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.enums.QuestionType;
import org.combs.micro.hc_tests_service.mapper.QuestionMapper;
import org.combs.micro.hc_tests_service.repository.QuestionRepository;
import org.combs.micro.hc_tests_service.request.QuestionRequest;
import org.combs.micro.hc_tests_service.response.QuestionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    /*public Page<Question> getPageableQuestions(Pageable pageable,
                                                       QuestionType type,
                                                       Integer rankPoints,
                                                       Integer difficulty,
                                                       SchoolSubject schoolSubject){

        return questionRepository.findAllByFilters(pageable,type,rankPoints,difficulty, schoolSubject);
    }*/

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
    }

    public Question createQuestion(Question question) {


        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, QuestionRequest request) {
        Question question = getQuestionById(id);
        questionMapper.updateEntityFromRequest(question, request);

        questionRepository.save(question);

        return question;

    }
}