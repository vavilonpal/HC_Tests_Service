package org.combs.micro.hc_tests_service.repository;

import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<SchoolTest, Long> {
    List<SchoolTest> findAllBySchoolSubject(SchoolSubject schoolSubject);
    List<SchoolTest> findAllByComplexity(Complexity complexity);

    List<SchoolTest> findAllByClassLevel(Integer classLevel);
}
