package org.combs.micro.hc_tests_service.repository;

import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.enums.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Boolean existsByDescriptionAndTestId(String description, Long testId);
    Optional<Question> getQuestionsByIdAndTestId(Long id, Long testId);

    List<Question> getQuestionsByTestId(Long testId);

}
