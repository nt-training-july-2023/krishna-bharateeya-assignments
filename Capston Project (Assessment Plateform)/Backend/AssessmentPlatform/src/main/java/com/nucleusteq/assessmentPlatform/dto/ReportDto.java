package com.nucleusteq.assessmentPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Dto class representing a report.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    /**
     *reportId of the report.
    */
    private int reportId;

    /**
     *userName name attribute.
    */
    private String userName;

    /**
     *userEmailId email attribute.
    */
    private String userEmailId;

    /**
     *category name attribute.
    */
    private String categoryName;
    /**
     *quiz name attribute.
    */
    private String quizName;

    /**
     *total marks of quiz attribute.
    */
    private int totalMarks;

    /**
     *marks obtained in quiz attribute.
    */
    private int marksObtained;

    /**
     * The count of wrong answers in the assessment.
     */
    private int wrongAnswers;
    /**
     *total questions in quiz attribute.
    */
    private int totalQuestions;

    /**
     *number of attempted questions attribute.
    */
    private int attemptedQuestions;

    /**
     *date and time attribute.
    */
    private String dateAndTime;

}
