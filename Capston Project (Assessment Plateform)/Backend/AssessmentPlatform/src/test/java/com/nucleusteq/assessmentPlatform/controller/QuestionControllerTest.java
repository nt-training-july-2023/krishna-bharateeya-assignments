package com.nucleusteq.assessmentPlatform.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.QuestionOptions;
import com.nucleusteq.assessmentPlatform.service.QuestionService;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

class QuestionControllerTest {

    @InjectMocks
    private QuestionController questionController;
    @Mock
    private QuestionService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    @Test
    public void testAddQuestion() {
        
        CategoryDto categoryDto = new CategoryDto(1, "C. S.","CS Description");

        QuizDTO quizDTO = new QuizDTO(1, "Java", "Java Description", 90, categoryDto); 
        
        QuestionDto questionDTO = new QuestionDto();
        questionDTO.setQuestionId(1);
        questionDTO.setQuestionText("Not a Programming Language :");
        QuestionOptions options = new QuestionOptions("java", "html", "hindi", "python", "hindi");
        questionDTO.setOptions(options);
        questionDTO.setQuiz(quizDTO);
        
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.CREATED.value(),"Question created successfully.");

        when(service.addQuestion(questionDTO)).thenReturn(successResponse);

        ResponseEntity<SuccessResponse> response = questionController.addQuestion(questionDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(successResponse, response.getBody());
    }

    @Test
    public void testUpdateQuestion() throws NotFoundException {
        Integer questionId = 1;
        CategoryDto categoryDto = new CategoryDto(1, "C. S.","CS Description");

        QuizDTO quizDTO = new QuizDTO(1, "Java", "Java Description", 90, categoryDto); 
        
        QuestionDto questionDTO = new QuestionDto();
        questionDTO.setQuestionId(1);
        questionDTO.setQuestionText("Not a Programming Language :");
        QuestionOptions options = new QuestionOptions("java", "html", "hindi", "python", "hindi");
        questionDTO.setOptions(options);
        questionDTO.setQuiz(quizDTO);
       
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(),"Question updated successfully.");

        when(service.updateQuestion(eq(questionId), any(QuestionDto.class))).thenReturn(successResponse);
        ResponseEntity<SuccessResponse> responseEntity = questionController.updateQuestion(questionId, questionDTO);
        verify(service).updateQuestion(eq(questionId), any(QuestionDto.class));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Question updated successfully.", responseEntity.getBody().getMessage());
    }
    

    
    @Test
    public void testDeleteQuestion() throws NotFoundException {
        CategoryDto categoryDto = new CategoryDto(1, "C. S.", "CS Description");
        QuizDTO quizDTO = new QuizDTO(1, "Java", "Java Description", 90, categoryDto);
        QuestionDto questionDTO = new QuestionDto();
        questionDTO.setQuestionId(1);
        questionDTO.setQuestionText("Not a Programming Language :");
        QuestionOptions options = new QuestionOptions("java", "html", "hindi", "python", "hindi");
        questionDTO.setOptions(options);
        questionDTO.setQuiz(quizDTO);

        Integer questionId = 1;
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Question deleted successfully.");

        when(service.deleteQuestion(eq(questionId))).thenReturn(successResponse);

        ResponseEntity<SuccessResponse> responseEntity = questionController.deleteQuestion(questionId);
        verify(service).deleteQuestion(eq(questionId));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Question deleted successfully.", responseEntity.getBody().getMessage());
    
    }




    @Test
    public void testGetQuestionById() throws NotFoundException {
        Integer questionId = 1;
        
        CategoryDto categoryDto = new CategoryDto(1, "C. S.","CS Description");

        QuizDTO quizDTO = new QuizDTO(1, "Java", "Java Description", 90, categoryDto); 
        
        QuestionDto questionDTO = new QuestionDto();
        questionDTO.setQuestionId(1);
        questionDTO.setQuestionText("Not a Programming Language :");
        QuestionOptions options = new QuestionOptions("java", "html", "hindi", "python", "hindi");
        questionDTO.setOptions(options);
        questionDTO.setQuiz(quizDTO);
        
        when(service.getQuestionById(eq(questionId))).thenReturn(questionDTO);

        ResponseEntity<QuestionDto> response = questionController.getQuestionById(questionId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questionDTO, response.getBody());
    }


    @Test
    public void testGetAllQuestions() {
        CategoryDto categoryDto = new CategoryDto(1, "C. S.","CS Description");

        QuizDTO quizDTO = new QuizDTO(1, "Java", "Java Description", 90, categoryDto); 
        
        QuestionDto questionDTO1 = new QuestionDto();
        questionDTO1.setQuestionId(1);
        questionDTO1.setQuestionText("Not a Programming Language :");
        QuestionOptions options = new QuestionOptions("java", "html", "hindi", "python", "hindi");
        questionDTO1.setOptions(options);
        questionDTO1.setQuiz(quizDTO);

        QuestionDto questionDTO2 = new QuestionDto();
        questionDTO2.setQuestionId(2);
        questionDTO2.setQuestionText("Not a Programming Language :");
        QuestionOptions option = new QuestionOptions("java", "html", "hindi", "python", "hindi");
        questionDTO2.setOptions(option);
        questionDTO2.setQuiz(quizDTO);

        List<QuestionDto> questionList = Arrays.asList(questionDTO1,questionDTO2);

        when(service.getAllQuestion()).thenReturn(questionList);

        ResponseEntity<List<QuestionDto>> response = questionController.getAllQuestions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questionList, response.getBody());
    }
    
   
}
