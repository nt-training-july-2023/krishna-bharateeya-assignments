package com.nucleusteq.assessmentPlatform.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a category.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "catSeq",
initialValue = Category.ID_INITIAL_VALUE, allocationSize = 1)
public class Category {

    /**
     * Constant for initial value of category ID sequence.
     */
    public static final int ID_INITIAL_VALUE = 3010;
    /**
     * The ID of the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catSeq")
    private int categoryId;

    /**
     * The name of the category.
     */
    @Column(nullable = false)
    private String categoryName;

    /**
     * The description of the category.
     */
    private String description;
   
    
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Quiz> quizzes = new ArrayList<>();
    
    
    /**
     * get Quiz.
     * @return Quiz
     */
    public List<Quiz> getQuizzes() {
        return new ArrayList<>(quizzes);
    }
    /**
     * set Quiz.
     * @param subcategory Quiz
     */
    public void setQuizzes(final List<Quiz> quizzes) {
        this.quizzes = new ArrayList<>(quizzes);
    }
    /**
     * parameter constructor for category.
     *@param categoryid categoryId
     * @param categoryname categoryName
     * @param categorydescription description
     */
    public Category(final int categoryid,
            final String categoryName,
            final String description) {
        this.categoryId = categoryid;
        this.categoryName = categoryName;
        this.description = description;
    }

}
