package com.nucleusteq.assessmentPlatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a quiz.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "quizSeq",
initialValue = Quiz.ID_INITIAL_VALUE, allocationSize = 1)
public class Quiz {

    /**
     * Constant for initial value of Quiz ID sequence.
     */
    public static final int ID_INITIAL_VALUE = 4010;

    /**
     * The ID of the quiz.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quizSeq")
    private int quizId;

    /**
     * The name of the quiz.
     */
    @Column(nullable = false)
    private String quizName;

    /**
     * The category to which the quiz belongs.
     */
//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;

}
