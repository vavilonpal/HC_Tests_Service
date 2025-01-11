package org.combs.micro.hc_tests_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "school_subjects", schema = "hc_school_tests_sc")
public class SchoolSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "school_subj_id")
    private List<SchoolTest> schoolTests;
    @OneToMany
    @JoinColumn(name = "school_subj_id")
    private List<Question> questions;



}
