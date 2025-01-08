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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tests")
@Entity
public class SchoolTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TestType type;

    @Column(name = "school_subject", nullable = false, length = 100)
    @NotBlank(message = "Введите предмет")
    private String schoolSubject;

    @Column(name = "complexity")
    @Enumerated(EnumType.STRING)
    private Complexity complexity;

    @Column(name = "class_level", nullable = false)
    private Integer classLevel;


    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "duration")
    private Integer duration;


}
