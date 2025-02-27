package org.combs.micro.hc_tests_service.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "results", schema = "hc_school_tests_sc")
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private SchoolTest schoolTest;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "rank_score")
    private Integer rankScore;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;


    @PrePersist
    public  void prePersist(){
        this.startedAt = LocalDateTime.now();
    }
    @PreUpdate
    public  void preUpdate(){
        this.finishedAt = LocalDateTime.now();
    }
}
