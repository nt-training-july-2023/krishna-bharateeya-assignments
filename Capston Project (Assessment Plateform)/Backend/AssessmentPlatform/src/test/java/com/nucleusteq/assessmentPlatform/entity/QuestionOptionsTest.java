package com.nucleusteq.assessmentPlatform.entity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class QuestionOptionsTest {

    @Mock
    private QuestionOptions questionOptions;

    @BeforeEach
    public void init() {
        questionOptions = new QuestionOptions();
    }
    @Test
    public void testGetOptionOne() {
        QuestionOptions options = new QuestionOptions("Option 1", "Option 2", "Option 3", "Option 4", "Correct Option");
        assertEquals("Option 1", options.getOptionOne());
    }

    @Test
    public void testGetOptionTwo() {
        QuestionOptions options = new QuestionOptions("Option 1", "Option 2", "Option 3", "Option 4", "Correct Option");
        assertEquals("Option 2", options.getOptionTwo());
    }

    @Test
    public void testGetOptionThree() {
        QuestionOptions options = new QuestionOptions("Option 1", "Option 2", "Option 3", "Option 4", "Correct Option");
        assertEquals("Option 3", options.getOptionThree());
    }

    @Test
    public void testGetOptionFour() {
        QuestionOptions options = new QuestionOptions("Option 1", "Option 2", "Option 3", "Option 4", "Correct Option");
        assertEquals("Option 4", options.getOptionFour());
    }

    @Test
    public void testGetCorrectOption() {
        QuestionOptions options = new QuestionOptions("Option 1", "Option 2", "Option 3", "Option 4", "Correct Option");
        assertEquals("Correct Option", options.getCorrectOption());
    }
    @Test
    public void testConstructor() {
        String optionOne = "Option 1";
        String optionTwo = "Option 2";
        String optionThree = "Option 3";
        String optionFour = "Option 4";
        String correctOption = "Correct Option";

        QuestionOptions options = new QuestionOptions(optionOne, optionTwo, optionThree, optionFour, correctOption);

        assertEquals(optionOne, options.getOptionOne());
        assertEquals(optionTwo, options.getOptionTwo());
        assertEquals(optionThree, options.getOptionThree());
        assertEquals(optionFour, options.getOptionFour());
        assertEquals(correctOption, options.getCorrectOption());
    }
}
