package org.combs.micro.hc_tests_service.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "test_question")
@Entity
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private SchoolTest schoolTest;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "order", nullable = false)
    private Short order;

    @Column(name = "test_points", nullable = false)
    private Short testPoints = 1;

    @Column(name = "rank_points")
    private Integer rankPoints;
}
