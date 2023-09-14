package com.nucleusteq.assessmentPlatform.dto;

import com.nucleusteq.assessmentPlatform.entity.QuestionOptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * This is question dto class.
 */
@Setter
@Getter
@NoArgsConstructor
public class QuestionDto {

    /**
     * The ID of the question.
     */
    private int questionId;

    /**
     * The text of the question.
     */
    private String questionText;

    /**
     * The answer options for the question.
     */
    private QuestionOptions options;

    /**
     * Gets the answer options for the question.
     * @return The answer options for the question.
     */
    public final QuestionOptions getOptions() {
        return new QuestionOptions(
                options.getOptionOne(),
                options.getOptionTwo(),
                options.getOptionThree(),
                options.getOptionFour(),
                options.getCorrectOption()
                );
    }

    /**
     * Sets the answer options for the question.
     * @param queOption The answer options to set for the question.
     */
    public final void setOptions(final QuestionOptions queOption) {
        options = new QuestionOptions(
                queOption.getOptionOne(),
                queOption.getOptionTwo(),
                queOption.getOptionThree(),
                queOption.getOptionFour(),
                queOption.getCorrectOption()
                );
    }

    /**
     * The quiz to which the quiz belongs.
     */
    private QuizDTO quiz;

    /**
     * Get a defensive copy of the CategoryDto object associated with this
     * QuizDTO.
     *
     * @return A defensive copy of the CategoryDto object.
     */
    public final QuizDTO getQuiz() {
        return new QuizDTO(quiz.getQuizId(), quiz.getQuizName(),
                quiz.getQuizDescription(), quiz.getTimeInMinutes(),
                quiz.getCategory());
    }

    /**
     * Set the CategoryDto object associated with this QuizDTO.
     *
     * @param quz The CategoryDto object to set. A defensive copy is created to
     *            prevent external modification.
     */
    public final void setQuiz(final QuizDTO quz) {
        this.quiz = new QuizDTO(quz.getQuizId(), quz.getQuizName(),
                quz.getQuizDescription(), quz.getTimeInMinutes(),
                quz.getCategory());
    }

    /**
     * Constructs a new QuestionDto object with the specified parameters.
     * @param qId         The unique identifier for the question.
     * @param qText       The text of the question.
     * @param paramOpns this parameter is options.
     * @param qz          The QuizDTO object associated with this question.
     */
    public QuestionDto(final int qId, final String qText,
           final QuestionOptions paramOpns, final QuizDTO qz) {
        super();
        questionId = qId;
        questionText = qText;
        options = new QuestionOptions(
                paramOpns.getOptionOne(),
                paramOpns.getOptionTwo(),
                paramOpns.getOptionThree(),
                paramOpns.getOptionFour(),
                paramOpns.getCorrectOption()
                );
        quiz = new QuizDTO(
                qz.getQuizId(), qz.getQuizName(), qz.getQuizDescription(),
                qz.getTimeInMinutes(), qz.getCategory());
    }
}
