package com.nucleusteq.assessmentPlatform.entity;

import javax.persistence.JoinColumn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a quiz.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "quizSeq",
initialValue = Quiz.ID_INITIAL_VALUE, allocationSize = 1)
public class Quiz {

    /**
     * Constant for initial value of Quiz ID sequence.
     */
    public static final int ID_INITIAL_VALUE = 4010;

    /**
     * The ID of the quiz.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quizSeq")
    private int quizId;

    /**
     * The name of the quiz.
     */
    @Column(nullable = false)
    private String quizName;

    /**
     * The Description of the quiz .
     */
    @Column(nullable = false)
    private String quizDescription;

    /**
     * The category to which the quiz belongs.
     */

    /**
     * The time of the Quiz.
     */
    private int timeInMinutes;

    /**
     * The Category to the Quiz belong.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * get category.
     * @return category
     */
    public final Category getCategory() {
        return new Category(category.getCategoryId(),
                category.getCategoryName(),
                category.getDescription());
    }

    /**
     * set Category.
     * @param cate cate.
     */
    public final void setCategory(final Category cate) {
        this.category = new Category(cate.getCategoryId(),
                cate.getCategoryName(),
                cate.getDescription());
    }

    /**
     * parameter constructor for sub-category.
     * @param qId quiz Id
     * @param quizNa quiz Name
     * @param quizDes quiz Description
     * @param timInMin time In Minutes
     */
    public Quiz(final int qId,
        final String quizNa,
        final String quizDes,
        final int timInMin) {
            this.quizId = qId;
            this.quizName = quizNa;
            this.quizDescription = quizDes;
            this.timeInMinutes = timInMin;
    }
}
