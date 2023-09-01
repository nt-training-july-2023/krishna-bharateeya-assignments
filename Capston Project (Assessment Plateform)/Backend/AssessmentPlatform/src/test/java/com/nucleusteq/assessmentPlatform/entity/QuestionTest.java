package com.nucleusteq.assessmentPlatform.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionTest {

    Question question;
    
    @BeforeEach
    void init() {
        question=new Question();
    }
    
    @Test
    void testNoArgGettersAndSetters() {
        
        Question questionobj=new Question();
        questionobj.setQuestionId(11);
        questionobj.setQuestionText("Question1");
        questionobj.setOptionOne("one");
        questionobj.setOptionTwo("two");
        questionobj.setOptionThree("three");
        questionobj.setOptionFour("Four");
        questionobj.setCorrectOption(1);
        questionobj.setUserSelectedOption(4);
        
        assertEquals(11,questionobj.getQuestionId());
        assertEquals("Question1",questionobj.getQuestionText());
        assertEquals("one",questionobj.getOptionOne());
        assertEquals("two",questionobj.getOptionTwo());
        assertEquals("three",questionobj.getOptionThree());
        assertEquals("Four",questionobj.getOptionFour());
        assertEquals(1,questionobj.getCorrectOption());
        assertEquals(4,questionobj.getUserSelectedOption());
    }
    
    @Test
    void testGettersAndSetters() {
        
        assertEquals(0,question.getQuestionId());
        assertEquals(null,question.getQuestionText());
        assertEquals(null,question.getOptionOne());
        assertEquals(null,question.getOptionTwo());
        assertEquals(null,question.getOptionThree());
        assertEquals(null,question.getOptionFour());
        assertEquals(0,question.getCorrectOption());
        assertEquals(0,question.getUserSelectedOption());
    }
    @Test
    void allArgConstructor() {
        Question allParaQuestion =new Question(
                102,
                "question",
                "one",
                "two",
                "three",
                "four",
                1,
                4
                );
        assertEquals(102,allParaQuestion.getQuestionId());
        assertEquals("question",allParaQuestion.getQuestionText());
        assertEquals("one",allParaQuestion.getOptionOne());
        assertEquals("two",allParaQuestion.getOptionTwo());
        assertEquals("three",allParaQuestion.getOptionThree());
        assertEquals("four",allParaQuestion.getOptionFour());
        assertEquals(1,allParaQuestion.getCorrectOption());
        assertEquals(4,allParaQuestion.getUserSelectedOption());
    }

}
