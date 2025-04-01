package org.combs.micro.hc_tests_service.entity;


import lombok.*;
import org.combs.micro.hc_tests_service.converter.AnswerJsonConverter;
import org.combs.micro.hc_tests_service.enums.QuestionType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString(exclude = {"schoolSubject", "test"})
@EqualsAndHashCode(of = {"schoolSubject", "description"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "questions",schema = "hc_school_tests_sc")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @ManyToOne()
    @JoinColumn(name = "test_id")
    private SchoolTest test;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;


    // todo замапить борбаотку ответа как массив
    @Convert(converter = AnswerJsonConverter.class)
    @Column(name = "answer", columnDefinition = "jsonb")
    private Map<String, List<Object>> answer;

    @ManyToOne
    @JoinColumn(name = "school_subj_id")
    private SchoolSubject schoolSubject;


    @Column(name = "check_type")
    private Boolean checkType;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "rank_points")
    private Integer rankPoints;

    @Column(name = "test_points")
    private Integer scorePoints;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onPersist(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void  onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
