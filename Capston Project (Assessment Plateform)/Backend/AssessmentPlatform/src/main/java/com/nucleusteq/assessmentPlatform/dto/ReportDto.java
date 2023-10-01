package com.nucleusteq.assessmentPlatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "User Name cannot be empty.")
    private String userName;

    /**
     *userEmailId email attribute.
    */
    @NotBlank(message = "User Email Id cannot be empty.")
    private String userEmailId;

    /**
     *category name attribute.
    */
    @NotBlank(message = "Category Name cannot be empty.")
    private String categoryName;
    /**
     *quiz name attribute.
    */
    @NotBlank(message = "Quiz Name cannot be empty.")
    private String quizName;

    /**
     *total marks of quiz attribute.
    */
    @NotNull(message = "Total Marks cannot be empty.")
    private int totalMarks;

    /**
     *marks obtained in quiz attribute.
    */
    @NotNull(message = "Obtained Marks cannot be empty.")
    private int marksObtained;

    /**
     * The count of wrong answers in the assessment.
     */
    @NotNull(message = "Total Questions Marks cannot be empty.")
    private int wrongAnswers;
    /**
     *total questions in quiz attribute.
    */
    @NotNull(message = "Total Questions Marks cannot be empty.")
    private int totalQuestions;

    /**
     *number of attempted questions attribute.
    */
    @NotNull(message = "Attempted Questions cannot be empty.")
    private int attemptedQuestions;

    /**
     *date and time attribute.
    */
    @NotBlank(message = "Date And Time cannot be empty.")
    private String dateAndTime;

}
