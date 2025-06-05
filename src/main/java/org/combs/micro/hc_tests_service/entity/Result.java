package org.combs.micro.hc_tests_service.entity;


import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "result_id")
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

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
