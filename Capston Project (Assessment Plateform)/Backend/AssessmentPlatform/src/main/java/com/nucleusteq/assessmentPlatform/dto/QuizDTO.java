package com.nucleusteq.assessmentPlatform.dto;

import org.springframework.validation.annotation.Validated;

import com.nucleusteq.assessmentPlatform.utility.ValidationMessage;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a quiz.
 */
@Validated
@Setter
@Getter
@NoArgsConstructor
public class QuizDTO {

    /**
     * The ID of the quiz.
     */
    private int quizId;

    /**
     * The name of the quiz.
     */
    @NotBlank(message = ValidationMessage.QUIZ_NAME_EMPTY)
    private String quizName;

    /**
     * The Description of the quiz.
     */
    @NotBlank(message = ValidationMessage.QUIZ_DESCRIPTION_EMPTY)
    private String quizDescription;

    /**
     * The time of the quiz.
     */
    @Min(value = 1, message = ValidationMessage.TIME_MINIMUM)
    private int timeInMinutes;

    /**
     * The state of the quiz.
     */
    private boolean enabled;
    /**
     * The category belong to quiz.
     */
    @NotNull(message = ValidationMessage.CATEGORY_NULL)
    @Valid
    private CategoryDto category;

    /**
     * Get a defensive copy of the CategoryDto object associated with this
     * QuizDTO.
     *
     * @return A defensive copy of the CategoryDto object.
     */
    public final CategoryDto getCategory() {
        return new CategoryDto(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription());
    }

    /**
     * Set the CategoryDto object associated with this QuizDTO.
     *
     * @param cat The CategoryDto object to set. A defensive copy is
     *                 created to prevent external modification.
     */
    public final void setCategory(final CategoryDto cat) {
        this.category = new CategoryDto(
                cat.getCategoryId(),
                cat.getCategoryName(),
                cat.getDescription());
    }

    /**
     * Constructor to create a QuizDTO with the specified attributes.
     *
     * @param qId          The ID of the quiz.
     * @param qName        The name of the quiz.
     * @param qDescription The description of the quiz.
     * @param time         The time duration of the quiz in minutes.
     * @param cat          The CategoryDto object associated with the quiz. A
     *                     defensive copy is created to prevent external
     *                     modification.
     */
    public QuizDTO(final int qId, final String qName, final String qDescription,
            final int time, final CategoryDto cat) {
        this.quizId = qId;
        this.quizName = qName;
        this.quizDescription = qDescription;
        this.timeInMinutes = time;
        this.category = new CategoryDto(
                cat.getCategoryId(),
                cat.getCategoryName(),
                cat.getDescription());
    }

}
