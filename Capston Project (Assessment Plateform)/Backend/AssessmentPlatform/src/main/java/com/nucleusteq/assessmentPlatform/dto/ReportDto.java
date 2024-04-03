package com.nucleusteq.assessmentPlatform.dto;

import com.nucleusteq.assessmentPlatform.utility.ValidationMessage;

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
    @NotBlank(message = ValidationMessage.USER_NAME_EMPTY)
    private String userName;

    /**
     *userEmailId email attribute.
    */
    @NotBlank(message = ValidationMessage.USER_EMAIL_ID_EMPTY)
    private String userEmailId;

    /**
     *category name attribute.
    */
    @NotBlank(message = ValidationMessage.REPORT_CATEGORY_NAME_EMPTY)
    private String categoryName;
    /**
     *quiz name attribute.
    */
    @NotBlank(message = ValidationMessage.REPORT_QUIZ_NAME_EMPTY)
    private String quizName;

    /**
     *total marks of quiz attribute.
    */
    @NotNull(message = ValidationMessage.TOTAL_MARKS_NULL)
    private int totalMarks;

    /**
     *marks obtained in quiz attribute.
    */
    @NotNull(message = ValidationMessage.MARKS_OBTAINED_NULL)
    private int marksObtained;

    /**
     * The count of wrong answers in the assessment.
     */
    @NotNull(message = ValidationMessage.WRONG_ANSWERS_NULL)
    private int wrongAnswers;
    /**
     *total questions in quiz attribute.
    */
    @NotNull(message = ValidationMessage.TOTAL_QUESTIONS_NULL)
    private int totalQuestions;

    /**
     *number of attempted questions attribute.
    */
    @NotNull(message = ValidationMessage.ATTEMPTED_QUESTIONS_NULL)
    private int attemptedQuestions;

    /**
     *date and time attribute.
    */
    @NotBlank(message = ValidationMessage.DATE_AND_TIME_EMPTY)
    private String dateAndTime;

}
