package org.combs.micro.hc_tests_service.repository;

import org.combs.micro.hc_tests_service.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByStudentIdAndResultId(Long studentId, Long resultId);
}
