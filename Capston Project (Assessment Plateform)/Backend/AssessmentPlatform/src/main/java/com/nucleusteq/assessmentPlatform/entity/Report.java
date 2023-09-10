package com.nucleusteq.assessmentPlatform.entity;

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
 * Entity class representing a report.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "reportSeq",
initialValue = Report.ID_INITIAL_VALUE, allocationSize = 1)
public class Report {

    /**
     * Constant for initial value of report ID sequence.
     m*/
    public static final int ID_INITIAL_VALUE = 6010;

    /**
     * The ID of the report.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportSeq")
    private int reportId;

    /**
     * The user for whom the report is generated.
     */
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Registration reportOfTheUser;


    /**
     * The total marks in the assessment.
     */
    private int totalMark;

    /**
     * The obtained marks in the assessment.
     */
    private int obtainedMark;

    /**
     * The count of wrong answers in the assessment.
     */
    private int wrongAnswers;

}
