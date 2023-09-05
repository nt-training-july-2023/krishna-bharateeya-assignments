package com.nucleusteq.assessmentPlatform.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuizTest {

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

        assertEquals(0, quiz.getQuizId());
        assertEquals("Sample Quiz", quiz.getQuizName());
    }
    @Test
    void allArgConstructor() {
        Quiz allParaQuiz =new Quiz(
                104,
                "Demo Quiz", null, 12
                );
        assertEquals(104,allParaQuiz.getQuizId());
        assertEquals("Demo Quiz", allParaQuiz.getQuizName());
    }

}
