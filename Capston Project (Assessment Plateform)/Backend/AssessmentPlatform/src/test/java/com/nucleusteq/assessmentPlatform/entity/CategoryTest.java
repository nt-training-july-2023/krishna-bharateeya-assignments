package com.nucleusteq.assessmentPlatform.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {
    Category category;
    @BeforeEach
    void init() {
        category = new Category();
    }
    @Test
    void testGettersAndSetters() {
        assertEquals(0,category.getCategoryId());
        assertEquals(null, category.getCategoryName());
        assertEquals(null, category.getDescription());
        category.setCategoryId(1);
        category.setCategoryName("GK");
        category.setDescription("GK Description");
        assertEquals(1, category.getCategoryId());
        assertEquals("GK", category.getCategoryName());
        assertEquals("GK Description", category.getDescription());
    }
    @Test
    void testDefaultConstructor() {
        Category categoryObj = new Category();
        assertEquals(0,categoryObj.getCategoryId());
        assertEquals(null, categoryObj.getCategoryName());
        assertEquals(null, categoryObj.getDescription());
    }
    @Test
    void testParameterisedConstructor() {
        Category category = new Category();
        category.setCategoryId(3010);
        category.setCategoryName("Front End");
        category.setDescription("React");
        
        assertEquals(3010, category.getCategoryId());
        assertEquals("Front End", category.getCategoryName());
        assertEquals("React", category.getDescription());
    }
    
    @Test
    void allArgConstructor() {
        Category allParaCategory =new Category(
                103,
                "java",
                "Spring"
                );
        assertEquals(103,allParaCategory.getCategoryId());
        assertEquals("java", allParaCategory.getCategoryName());
        assertEquals("Spring", allParaCategory.getDescription());
    }
    
    @Test
    void setQuizzes_SetsQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        Quiz quiz1 = new Quiz(1, "Quiz 1", "Quiz 1 Description", 30);
        Quiz quiz2 = new Quiz(2, "Quiz 2", "Quiz 2 Description", 45);
        quizzes.add(quiz1);
        quizzes.add(quiz2);

        category.setQuizzes(quizzes);

        List<Quiz> categoryQuizzes = category.getQuizzes();
        assertNotNull(categoryQuizzes);
        assertEquals(2, categoryQuizzes.size());
        assertEquals(quiz1, categoryQuizzes.get(0));
        assertEquals(quiz2, categoryQuizzes.get(1));
    }

    @Test
    void getQuizzes_ReturnsQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        Quiz quiz1 = new Quiz(1, "Quiz 1", "Quiz 1 Description", 30);
        Quiz quiz2 = new Quiz(2, "Quiz 2", "Quiz 2 Description", 45);
        quizzes.add(quiz1);
        quizzes.add(quiz2);
        category.setQuizzes(quizzes);

        List<Quiz> categoryQuizzes = category.getQuizzes();

        assertNotNull(categoryQuizzes);
        assertEquals(2, categoryQuizzes.size());
        assertEquals(quiz1, categoryQuizzes.get(0));
        assertEquals(quiz2, categoryQuizzes.get(1));
    }
}