package org.combs.micro.hc_tests_service.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "questions")
//todo разобраться с json
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "school_subject", nullable = false, length = 100)
    private String schoolSubject;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "answer", nullable = false, columnDefinition = "TEXT")
    private String answer;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    /*@Type(type = "jsonb")
    @Column(name = "options", columnDefinition = "jsonb")
    private Map<String, Object> options;*/

    @Column(name = "difficulty")
    private Short difficulty;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
