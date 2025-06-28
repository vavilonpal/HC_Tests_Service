package org.combs.micro.hc_tests_service.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import jakarta.persistence.*;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"question", "studentId"})
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown JSON properties
@ToString(exclude = {"question", "result"})
@Builder
@Entity
@Table(name =  "answers", schema = "hc_school_tests_sc")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  /*  //@Convert(converter = AnswerJsonConverter.class)
    @Type(type = "jsonb")
    @Column(name = "student_answer", columnDefinition = "jsonb")
    private Map<String, List<Object>> studentAnswer;
*/

    // todo переделавт все json в  правильный формат по стайтей
    // https://www.baeldung.com/spring-boot-jpa-storing-postgresql-jsonb
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "student_answer", columnDefinition = "jsonb")
    private JsonNode studentAnswer;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private Result result;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

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
