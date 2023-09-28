package com.nucleusteq.assessmentPlatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
     * Constant for initial value of report ID sequence. m
     */
    public static final int ID_INITIAL_VALUE = 6010;

    /**
     * The ID of the report.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportSeq")
    private int reportId;

    /**
     * userName name attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = "User Name cannot be empty.")
    private String userName;

    /**
     * userEmailId email attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = "User Email Id cannot be empty.")
    private String userEmailId;

    /**
     * category name attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = "Category Name cannot be empty.")
    private String categoryName;
    /**
     * quiz name attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = "Quiz Name cannot be empty.")
    private String quizName;

    /**
     * total marks of quiz attribute.
     */
    @Column(nullable = false)
    @NotNull(message = "Total Marks cannot be empty.")
    private Integer totalMarks;

    /**
     * marks obtained in quiz attribute.
     */
    @Column(nullable = false)
    @NotNull(message = "Obtained Marks cannot be empty.")
    private int marksObtained;

    /**
     * total questions in quiz attribute.
     */
    @Column(nullable = false)
    @NotNull(message = "Total Questions Marks cannot be empty.")
    private int totalQuestions;

    /**
     * number of attempted questions attribute.
     */
    @Column(nullable = false)
    @NotNull(message = "Attempted Questions cannot be empty.")
    private int attemptedQuestions;

    /**
     * date and time attribute.
     */
    @Column(nullable = false)
    @NotBlank(message = "Date And Time cannot be empty.")
    private String dateAndTime;

    /**
     * The count of wrong answers in the assessment.
     */
    @NotNull(message = "wrong Answers cannot be empty.")
    private int wrongAnswers;

}
