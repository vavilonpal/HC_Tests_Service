package org.combs.micro.hc_tests_service.repository;

import org.combs.micro.hc_tests_service.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
