package com.nucleusteq.assessmentPlatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "quistionSeq", initialValue = 5010, allocationSize = 1)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quistionSeq")
    private int questionId;

    @Column(nullable = false)
    private String questionText;
    @Column(nullable = false)
    private String option_1;
    @Column(nullable = false)
    private String option_2;
    @Column(nullable = false)
    private String option_3;
    @Column(nullable = false)
    private String option_4;
    @Column(nullable = false)
    private int correctOption;

    private int userSelectedOption;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

}