package org.combs.micro.hc_tests_service.repository;

import org.combs.micro.hc_tests_service.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> getResultsByStudentId(Long studentId);
    //List<Result>getResultsBySchoolId();
}
