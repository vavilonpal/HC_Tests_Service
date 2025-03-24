package org.combs.micro.hc_tests_service.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.combs.micro.hc_tests_service.enums.QuestionCheckType;
import org.combs.micro.hc_tests_service.enums.QuestionType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@ToString(exclude = "schoolSubject")
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

    @Column(name = "answer")
    private String answer;

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

    @Column(name = "created_at", nullable = false, updatable = false)

    private LocalDateTime createdAt;

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
