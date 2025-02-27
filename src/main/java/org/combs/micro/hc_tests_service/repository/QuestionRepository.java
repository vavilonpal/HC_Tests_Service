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

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllBySchoolSubject(SchoolSubject subject);

   /* @Query("SELECT q FROM Question q " +
            "WHERE (:type IS NULL OR q.type = :type) " +
            "AND (:rank_points IS NULL OR q.rankPoints = :rank_points) " +
            "AND (:difficulty IS NULL OR q.difficulty = :difficulty) " +
            "AND (:subject IS NULL OR q.schoolSubject = :subject)")
    Page<Question> findAllByFilters(Pageable pageable,
                                    @Param("type") QuestionType type,
                                    @Param("rank_points") Integer rankPoints,
                                    @Param("difficultly") Integer difficulty,
                                    @Param("subject") SchoolSubject subject
    );*/
}
