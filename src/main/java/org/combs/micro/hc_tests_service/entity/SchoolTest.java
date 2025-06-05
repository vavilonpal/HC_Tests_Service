package org.combs.micro.hc_tests_service.entity;


import lombok.*;
import org.combs.micro.hc_tests_service.enums.Complexity;
import org.combs.micro.hc_tests_service.enums.TestType;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TestType type;

    @ManyToOne
    @JoinColumn(name = "school_subj_id")
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

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Question> questions = new HashSet<>();

    @Column(name = "created_at", nullable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();


}
