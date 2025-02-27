package org.combs.micro.hc_tests_service.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.enums.TestType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "school_tests", schema = "hc_school_tests_sc")
@Entity
public class SchoolTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TestType type;

    @ManyToOne
    @JoinColumn(name = "school_subject_id")
    private SchoolSubject schoolSubject;

    @Column(name = "complexity")
    @Enumerated(EnumType.STRING)
    private Complexity complexity;

    @Column(name = "class_level", nullable = false)
    private Integer classLevel;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "test_id")
    private Set<Question> questions = new HashSet<>();

    @Column(name = "created_at", nullable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();


}
