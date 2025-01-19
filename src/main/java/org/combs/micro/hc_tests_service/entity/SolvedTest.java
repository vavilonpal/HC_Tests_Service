package org.combs.micro.hc_tests_service.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "solved_tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolvedTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_id",nullable = false)
    private Long studentId;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "test_id",nullable = false)
    private Long testId;

    @Column(name = "answers", columnDefinition = "jsonb", nullable = false)
    private Map<Long, AnswerDetail> answers;

    @Column(name = "solve_time")
    private Duration solveTime;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
