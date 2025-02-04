package org.combs.micro.hc_tests_service.entity;


import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"question", "studentAnswer"})
@Builder
@Entity
@Table(name =  "answers", schema = "hc_school_tests_sc")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "result_id")
    private Result result;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "student_answer", nullable = false)
    private String studentAnswer;

    @Column(name = "rank_points")
    private Integer rankPoints;
    @Column(name = "score")
    private Integer score;


}
