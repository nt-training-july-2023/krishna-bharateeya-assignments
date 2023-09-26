package com.nucleusteq.assessmentPlatform.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
     * The quizzes map with category.
     */
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    /**
     * get Quiz.
     * @return Quiz
     */
    public final List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    /**
     * set Quiz.
     * @param que Quiz.
     */
    public final void setQuestions(final List<Question> que) {
        this.questions = new ArrayList<>(que);
    }

    /**
     * get category.
     * @return category
     */
    public final Category getCategory() {
        return new Category(category.getCategoryId(),
                category.getCategoryName(), category.getDescription());
    }

    /**
     * set Category.
     * @param cate cate.
     */
    public final void setCategory(final Category cate) {
        if (cate != null) {
          this.category = new Category(cate.getCategoryId(),
            cate.getCategoryName(), cate.getDescription());
        } else {
            this.category = null;
        }
    }
    /**
     * Constructs a new Quiz object with the specified parameters.
     * @param qId           The unique identifier for the quiz.
     * @param qName         The name of the quiz.
     * @param qDescription  The description of the quiz.
     * @param time          The time limit for the quiz in minutes.
     * @param cat           The Category object associated with this quiz.
     */
    public Quiz(final int qId, final String qName, final String qDescription,
            final int time, final Category cat) {
        super();
        quizId = qId;
        quizName = qName;
        quizDescription = qDescription;
        timeInMinutes = time;
        category = new Category(cat.getCategoryId(),
                cat.getCategoryName(), cat.getDescription());
    }

}
