package org.combs.micro.hc_tests_service.repository;

import org.combs.micro.hc_tests_service.entity.SchoolSubject;
import org.combs.micro.hc_tests_service.entity.SchoolTest;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<SchoolTest, Long> {
    @Query("select t from SchoolTest t " +
            "where (:subject is null or t.schoolSubject = :subject) " +
            "and (:complexity is null or t.complexity = :complexity) " +
            "and (:class_level is null or t.classLevel = :class_level) " +
            "and (:teacher_id is null or t.teacherId = :teacher_id)")
    Page<SchoolTest> findAllByFilters(Pageable pageable,
                                      @Param("subject") SchoolSubject schoolSubject,
                                      @Param("complexity") Integer complexity,
                                      @Param("class_level") String classLevel,
                                      @Param("teacher_id") Long teacherId);

}
