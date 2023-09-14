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
        questionobj.setCorrectOption("Four");
        
        
        assertEquals(11,questionobj.getQuestionId());
        assertEquals("Question1",questionobj.getQuestionText());
        assertEquals("one",questionobj.getOptionOne());
        assertEquals("two",questionobj.getOptionTwo());
        assertEquals("three",questionobj.getOptionThree());
        assertEquals("Four",questionobj.getOptionFour());
        assertEquals("Four",questionobj.getCorrectOption());
        
    }
    
    @Test
    void testGettersAndSetters() {
        
        assertEquals(0,question.getQuestionId());
        assertEquals(null,question.getQuestionText());
        assertEquals(null,question.getOptionOne());
        assertEquals(null,question.getOptionTwo());
        assertEquals(null,question.getOptionThree());
        assertEquals(null,question.getOptionFour());
        assertEquals(null,question.getCorrectOption());
        
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
                "one"
                );
        assertEquals(102,allParaQuestion.getQuestionId());
        assertEquals("question",allParaQuestion.getQuestionText());
        assertEquals("one",allParaQuestion.getOptionOne());
        assertEquals("two",allParaQuestion.getOptionTwo());
        assertEquals("three",allParaQuestion.getOptionThree());
        assertEquals("four",allParaQuestion.getOptionFour());
        assertEquals("one",allParaQuestion.getCorrectOption());
    }

}
