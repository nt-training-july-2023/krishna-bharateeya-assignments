package com.nucleusteq.assessmentPlatform.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class QuizTest {

    @Mock
    private Category category;
    
    @Mock
    private Quiz quiz;

    @BeforeEach
    void init() {
        quiz = new Quiz();
    }

    @Test
    public void testSettierAndGetter() {
        

        assertEquals(0, quiz.getQuizId());
        assertEquals(null, quiz.getQuizName());
    }
    
    @Test
    public void testSettingAndGettingProperties() {
        quiz.setQuizId(0);
        quiz.setQuizName("Sample Quiz");
        quiz.setQuizDescription("this is description");
        quiz.setTimeInMinutes(60);
        
        Category category = new Category();
        category.setCategoryId(3024);
        category.setCategoryName("cat1");
        category.setDescription("cat1 desc");
        
        quiz.setCategory(category);

        assertEquals(0, quiz.getQuizId());
        assertEquals("Sample Quiz", quiz.getQuizName());
        assertEquals("this is description", quiz.getQuizDescription());
        assertEquals(60, quiz.getTimeInMinutes());
        assertEquals(category.getCategoryName(), quiz.getCategory().getCategoryName());
    }
//    @Test
//    void allArgConstructor() {
//        Quiz allParaQuiz =new Quiz(
//                104,
//                "Demo Quiz", null, 12, null
//                );
//        assertEquals(104,allParaQuiz.getQuizId());
//        assertEquals("Demo Quiz", allParaQuiz.getQuizName());
//    }
    

}
