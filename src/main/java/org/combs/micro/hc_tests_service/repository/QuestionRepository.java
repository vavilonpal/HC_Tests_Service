package org.combs.micro.hc_tests_service.repository;

import org.combs.micro.hc_tests_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
