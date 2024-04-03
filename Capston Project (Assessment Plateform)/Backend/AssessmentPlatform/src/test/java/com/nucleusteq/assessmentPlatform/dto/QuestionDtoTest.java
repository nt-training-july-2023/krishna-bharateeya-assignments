package com.nucleusteq.assessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.nucleusteq.assessmentPlatform.entity.QuestionOptions;

class QuestionDtoTest {

 QuestionDto questionDto;
    
    @BeforeEach
    void init() {
        questionDto=new QuestionDto();
    }
    
    
    @Test
    public void testQuestionId() {
        QuizDTO quizDTO = new QuizDTO(1, "Sample Quiz", "Description", 30, new CategoryDto(1, "CategoryName", "CategoryDescription"));
        QuestionOptions options = new QuestionOptions("Option1", "Option2", "Option3", "Option4", "CorrectOption");
        QuestionDto questionDto = new QuestionDto(1, "Question Text", options, quizDTO);

        int retrievedQuestionId = questionDto.getQuestionId();
        assertEquals(1, retrievedQuestionId);
    }

    @Test
    public void testQuestionText() {

        QuizDTO quizDTO = new QuizDTO(1, "Sample Quiz", "Description", 30, new CategoryDto(1, "CategoryName", "CategoryDescription"));
        QuestionOptions options = new QuestionOptions("Option1", "Option2", "Option3", "Option4", "CorrectOption");
        QuestionDto questionDto = new QuestionDto(1, "Question Text", options, quizDTO);

        String retrievedQuestionText = questionDto.getQuestionText();

        assertEquals("Question Text", retrievedQuestionText);
    }

    @Test
    public void testOptions() {
        QuestionOptions options = new QuestionOptions("Option1", "Option2", "Option3", "Option4", "CorrectOption");

        QuizDTO quizDTO = new QuizDTO(1, "Sample Quiz", "Description", 30, new CategoryDto(1, "CategoryName", "CategoryDescription"));

        QuestionDto questionDto = new QuestionDto(1, "Question Text", options, quizDTO);

        QuestionOptions retrievedOptions = questionDto.getOptions();

        assertEquals(options.getCorrectOption(), retrievedOptions.getCorrectOption());
    }
    
    @Test
    public void testSetQuiz() {
       
        QuizDTO quizDTO = new QuizDTO(1, "Sample Quiz", "Description", 30, new CategoryDto(1, "CategoryName", "CategoryDescription"));
        QuestionOptions options = new QuestionOptions("Option1", "Option2", "Option3", "Option4", "CorrectOption");
        QuestionDto questionDto = new QuestionDto(1, "Question Text", options, quizDTO);

        questionDto.setQuiz(quizDTO);

        QuizDTO retrievedQuizDTO = questionDto.getQuiz();

        assertEquals(quizDTO.getQuizName(), retrievedQuizDTO.getQuizName());
    }
    
    @Test
    public void testSetOptions() {
        QuizDTO quizDTO = new QuizDTO(1, "Sample Quiz", "Description", 30, new CategoryDto(1, "CategoryName", "CategoryDescription"));
        QuestionOptions options = new QuestionOptions("Option1", "Option2", "Option3", "Option4", "CorrectOption");
        QuestionDto questionDto = new QuestionDto(1, "Question Text", options, quizDTO);

        questionDto.setOptions(options);

        QuestionOptions retrievedOptions = questionDto.getOptions();

        assertEquals(options.getOptionOne(), retrievedOptions.getOptionOne());
    }
    
    @Test
    public void testGetQuestionText() {
        
        QuizDTO quizDTO = new QuizDTO(1, "Sample Quiz", "Description", 30, new CategoryDto(1, "CategoryName", "CategoryDescription"));
        QuestionOptions options = new QuestionOptions("Option1", "Option2", "Option3", "Option4", "CorrectOption");
        QuestionDto questionDto = new QuestionDto(1, "Question Text", options, quizDTO);

        String retrievedQuestionText = questionDto.getQuestionText();

        assertEquals("Question Text", retrievedQuestionText);
    }
    @Test
    public void testGetQuestionId() {
        QuizDTO quizDTO = new QuizDTO(1, "Sample Quiz", "Description", 30, new CategoryDto(1, "CategoryName", "CategoryDescription"));
        QuestionOptions options = new QuestionOptions("Option1", "Option2", "Option3", "Option4", "CorrectOption");
       
        QuestionDto questionDto = new QuestionDto(1, "Question Text", options, quizDTO);

        int retrievedQuestionId = questionDto.getQuestionId();
        assertEquals(1, retrievedQuestionId);
    }
    @Test
    public void testGetSetQuiz() {

        QuizDTO quizDTO = new QuizDTO(1, "Sample Quiz", "Description", 30, new CategoryDto(1, "CategoryName", "CategoryDescription"));
        QuestionOptions options = new QuestionOptions("Option1", "Option2", "Option3", "Option4", "CorrectOption");

        QuestionDto questionDto = new QuestionDto(1, "Question Text", options, quizDTO);
        QuizDTO retrievedQuizDTO = questionDto.getQuiz();
        assertEquals(quizDTO.getQuizName(), retrievedQuizDTO.getQuizName());
    }
    
    @Test
    public void testGetSetOptions() {
        
        CategoryDto cat=new CategoryDto(1,"sample Cat","cat desc");

        QuizDTO quizDTo=new QuizDTO(101,"quiz1","quiz desc",78,cat);
        QuestionOptions options = new QuestionOptions("Option1", "Option2", "Option3", "Option4", "CorrectOption");

        QuestionDto questionDto = new QuestionDto(1, "Question Text", options, quizDTo);

        QuestionOptions retrievedOptions = questionDto.getOptions();

        assertEquals(options.getOptionOne(), retrievedOptions.getOptionOne());
        assertEquals(options.getOptionTwo(), retrievedOptions.getOptionTwo());
    }
    
    @Test
    public void testConstructor() {
        QuestionOptions options = new QuestionOptions("Option1", "Option2", "Option3", "Option4", "CorrectOption");

        CategoryDto cat=new CategoryDto(1, "CategoryName", "CategoryDescription");
        QuizDTO quizDTO = new QuizDTO(1, "Sample Quiz", "Description", 30, cat);

        QuestionDto questionDto = new QuestionDto(1, "Question Text", options, quizDTO);

        assertEquals(1, questionDto.getQuestionId());
        assertEquals("Question Text", questionDto.getQuestionText());
        assertEquals(options.getCorrectOption(), questionDto.getOptions().getCorrectOption());
        
    }

}
