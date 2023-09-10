package com.nucleusteq.assessmentPlatform.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class representing a quiz.
 */
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
    private String quizName;

    /**
     * The Description of the quiz.
     */
    private String quizDescription;

    /**
     * The time of the quiz.
     */
    private int timeInMinutes;

    /**
     * The category belong to quiz.
     */
    private CategoryDto category;

    /**
     * Get a defensive copy of the CategoryDto object associated with this
     * QuizDTO.
     *
     * @return A defensive copy of the CategoryDto object.
     */
    public final CategoryDto getCategory() {
        return new CategoryDto(category);
    }

    /**
     * Set the CategoryDto object associated with this QuizDTO.
     *
     * @param cat The CategoryDto object to set. A defensive copy is
     *                 created to prevent external modification.
     */
    public final void setCategory(final CategoryDto cat) {
        this.category = new CategoryDto(cat);
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
        this.category = new CategoryDto(cat);
    }

}
