package com.nucleusteq.assessmentPlatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a question in a quiz.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "quistionSeq",
initialValue = Question.ID_INITIAL_VALUE, allocationSize = 1)
public class Question {

    /**
     * Constant for initial value of question ID sequence.
     */
    public static final int ID_INITIAL_VALUE = 5010;

    /**
     * The ID of the question.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "quistionSeq")
    private int questionId;

    /**
     * The text of the question.
     */
    @Column(nullable = false)
    @NotBlank(message = "Question Text cannot be empty.")
    private String questionText;

    /**
     * The first option for the question.
     */
    @Column(nullable = false)
    @NotBlank(message = "Option One Text cannot be empty.")
    private String optionOne;

    /**
     * The second option for the question.
     */
    @Column(nullable = false)
    @NotBlank(message = "Option Two Text cannot be empty.")
    private String optionTwo;

    /**
     * The third option for the question.
     */
    @Column(nullable = false)
    @NotBlank(message = "Option Three Text cannot be empty.")
    private String optionThree;

    /**
     * The forth option for the question.
     */
    @Column(nullable = false)
    @NotBlank(message = "Option Four Text cannot be empty.")
    private String optionFour;

    /**
     * The correct option for the question.
     */
    @Column(nullable = false)
    @NotBlank(message = "correct Option Text cannot be empty.")
    private String correctOption;

    /**
     * The quiz to which the quiz belongs.
     */
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @NotNull(message = "Quiz Object cannot be empty.")
    private Quiz quiz;

    /**
     * get category.
     * @return category
     */
    public final Quiz getQuiz() {
        return new Quiz(quiz.getQuizId(), quiz.getQuizName(),
                quiz.getQuizDescription(), quiz.getTimeInMinutes(),
                quiz.getCategory());
    }

    /**
     * set Category.
     * @param que for question parameter.
     */
    public final void setQuiz(final Quiz que) {
        this.quiz = new Quiz(que.getQuizId(), que.getQuizName(),
                que.getQuizDescription(), que.getTimeInMinutes(),
                que.getCategory());
    }
    /**
     * Constructs a new Question object with the specified parameters.
     *
     * @param qId         The unique identifier for the question.
     * @param qText       The text of the question.
     * @param opOne       The first answer option for the question.
     * @param opTwo       The second answer option for the question.
     * @param opThree     The third answer option for the question.
     * @param opFour      The fourth answer option for the question.
     * @param corrOption  The correct answer option for the question.
     */
    public Question(final int qId, final String qText, final String opOne,
            final String opTwo, final String opThree, final String opFour,
            final String corrOption) {
        super();
        questionId = qId;
        questionText = qText;
        optionOne = opOne;
        optionTwo = opTwo;
        optionThree = opThree;
        optionFour = opFour;
        correctOption = corrOption;
    }

}
