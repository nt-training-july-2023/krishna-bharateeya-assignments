package com.nucleusteq.assessmentPlatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
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
    private String questionText;

    /**
     * The first option for the question.
     */
    @Column(nullable = false)
    private String optionOne;

    /**
     * The second option for the question.
     */
    @Column(nullable = false)
    private String optionTwo;

    /**
     * The third option for the question.
     */
    @Column(nullable = false)
    private String optionThree;

    /**
     * The forth option for the question.
     */
    @Column(nullable = false)
    private String optionFour;

    /**
     * The correct option for the question.
     */
    @Column(nullable = false)
    private String correctOption;



    /**
     * The quiz to which the quiz belongs.
     */
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    /**
     * get category.
     * @return category
     */
    public final Quiz getQuiz() {
        return new Quiz(quiz.getQuizId(),
                quiz.getQuizName(),
                quiz.getQuizDescription(),
                quiz.getTimeInMinutes(),
                quiz.getCategory()
                );
    }

    /**
     * set Category.
     * @param cate cate.
     */
    public final void setQuiz(final Quiz que) {
        this.quiz = new Quiz(que.getQuizId(),
                que.getQuizName(),
                que.getQuizDescription(),
                que.getTimeInMinutes(),
                que.getCategory());
    }

    public Question(int questionId, String questionText, String optionOne,
            String optionTwo, String optionThree, String optionFour,
            String correctOption) {
        super();
        this.questionId = questionId;
        this.questionText = questionText;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.correctOption = correctOption;
    }
    
    
}
