package org.combs.micro.hc_tests_service.repository;

import org.combs.micro.hc_tests_service.entity.Question;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.entity.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long> {
    List<Question> getTestQuestionByTest(SchoolTest schoolTest);
}
