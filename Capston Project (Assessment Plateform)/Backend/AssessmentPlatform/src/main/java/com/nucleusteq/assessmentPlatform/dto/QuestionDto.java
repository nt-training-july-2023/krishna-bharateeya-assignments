package com.nucleusteq.assessmentPlatform.dto;

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
     * The first option for the question.
     */
    private String optionOne;

    /**
     * The second option for the question.
     */
    private String optionTwo;

    /**
     * The third option for the question.
     */
    private String optionThree;

    /**
     * The forth option for the question.
     */
    private String optionFour;

    /**
     * The correct option for the question.
     */
    private String correctOption;

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
     * @param opOne       The first answer option for the question.
     * @param opTwo       The second answer option for the question.
     * @param opThree     The third answer option for the question.
     * @param opFour      The fourth answer option for the question.
     * @param corrOption  The correct answer option for the question.
     * @param qz          The QuizDTO object associated with this question.
     */
    public QuestionDto(final int qId, final String qText,
            final String opOne, final String opTwo,
            final String opThree, final String opFour,
            final String corrOption, final QuizDTO qz) {
        super();
        questionId = qId;
        questionText = qText;
        optionOne = opOne;
        optionTwo = opTwo;
        optionThree = opThree;
        optionFour = opFour;
        correctOption = corrOption;
        quiz = new QuizDTO(

                qz.getQuizId(), qz.getQuizName(), qz.getQuizDescription(),
                qz.getTimeInMinutes(), qz.getCategory());
    }
}
