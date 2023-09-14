package com.nucleusteq.assessmentPlatform.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
                quiz.getQuizDescription(), 
                quiz.getTimeInMinutes(),
                quiz.getCategory());
    }

    /**
     * Set the CategoryDto object associated with this QuizDTO.
     *
     * @param cat The CategoryDto object to set. A defensive copy is
     *                 created to prevent external modification.
     */
    public final void setQuiz(final QuizDTO quiz) {
        this.quiz = new QuizDTO(
                quiz.getQuizId(),
                quiz.getQuizName(),
                quiz.getQuizDescription(),
                quiz.getTimeInMinutes(),
                quiz.getCategory());
    }

    public QuestionDto(int questionId, String questionText, String optionOne,
            String optionTwo, String optionThree, String optionFour,
            String correctOption, QuizDTO quiz) {
        super();
        this.questionId = questionId;
        this.questionText = questionText;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.correctOption = correctOption;
        this.quiz = new QuizDTO(
                
                quiz.getQuizId(),
                quiz.getQuizName(),
                quiz.getQuizDescription(),
                quiz.getTimeInMinutes(),
                quiz.getCategory()
                );
    }
}
