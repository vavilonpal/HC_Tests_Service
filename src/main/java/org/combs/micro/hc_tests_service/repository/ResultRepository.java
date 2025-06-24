package org.combs.micro.hc_tests_service.repository;

import org.combs.micro.hc_tests_service.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> getResultsByStudentId(Long studentId);

    @Query("SELECT COUNT(r) FROM Result r WHERE r.student.id = :studentId")
    Integer getStudentResultsAmount(@Param("studentId") Long studentId);

    @Query("SELECT sum(r.rankScore) FROM Result r WHERE r.student.id = :studentId")
    Integer getSumOfRankScore(@Param("studentId") Long studentId);

    @Query("SELECT sum(r.score) FROM Result r WHERE r.student.id = :studentId")
    Integer getSumOfScore(@Param("studentId") Long studentId);
}
