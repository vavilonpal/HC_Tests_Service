package org.combs.micro.hc_tests_service.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.combs.micro.hc_tests_service.converter.AnswerJsonConverter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import jakarta.persistence.*;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"question", "studentId"})
@ToString(exclude = {"question", "result"})
@Builder
@Entity
@Table(name =  "answers", schema = "hc_school_tests_sc")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Convert(converter = AnswerJsonConverter.class)
    @Type(type = "jsonb")
    @Column(name = "student_answer", columnDefinition = "jsonb")
    private Map<String, List<Object>> studentAnswer;


    // todo переделавт все json в  правильный формат по стайтей
    // https://www.baeldung.com/spring-boot-jpa-storing-postgresql-jsonb
    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "student_answer", columnDefinition = "jsonb")
    private StudentAnswer studentAnswer;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private Result result;

    @Column(name = "student_id")
    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


    @Column(name = "rank_points")
    private Integer rankPoints;

    @Column(name = "score_points")
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
