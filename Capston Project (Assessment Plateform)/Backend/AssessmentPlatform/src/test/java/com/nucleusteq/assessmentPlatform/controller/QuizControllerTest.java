package com.nucleusteq.assessmentPlatform.controller;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.service.serviceImpl.QuizServiceImpl;
public class QuizControllerTest {
    
    @InjectMocks
    private QuizController quizController;
    
    @Mock
    private QuizServiceImpl quizService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testAddQuiz() {
        QuizDTO quizDto = new QuizDTO();
        quizDto.setQuizId(1);
        quizDto.setQuizName("Maths");
        quizDto.setQuizDescription("Maths Category");
        when(quizService.addQuiz(quizDto)).thenReturn("Quiz: "+quizDto.getQuizName()+", added successfully!");
        ResponseEntity<String> response = quizController.addQuiz(quizDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Quiz: "+quizDto.getQuizName()+", added successfully!", response.getBody());
    }

    @Test
    public void testUpdateQuiz() throws NotFoundException {
        Integer quizId = 1;
        QuizDTO quizDTO = createSampleQuiz();

        String expectedResponse = "Quiz updated successfully";
        when(quizService.updateQuiz(quizId, quizDTO)).thenReturn(expectedResponse);

        ResponseEntity<String> responseEntity = quizController.updateQuiz(quizId, quizDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testDeleteQuiz() throws NotFoundException {
        Integer quizId = 1;

        String expectedResponse = "Quiz deleted successfully";
        when(quizService.deleteQuiz(quizId)).thenReturn(expectedResponse);

        ResponseEntity<String> responseEntity = quizController.deleteQuiz(quizId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testGetQuizById() throws NotFoundException {
        Integer quizId = 1;
        QuizDTO expectedQuiz = createSampleQuiz();

        when(quizService.getQuizById(quizId)).thenReturn(expectedQuiz);

        ResponseEntity<QuizDTO> responseEntity = quizController.getQuizById(quizId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedQuiz, responseEntity.getBody());
    }

    @Test
    public void testGetAllQuizzes() {
        List<QuizDTO> expectedQuizzes = Arrays.asList(createSampleQuiz(), createSampleQuiz());

        when(quizService.getAllQuizzes()).thenReturn(expectedQuizzes);

        ResponseEntity<List<QuizDTO>> responseEntity = quizController.getAllQuizzes();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedQuizzes, responseEntity.getBody());
    }

    @Test
    public void testGetAllQuestionByQuiz() {
        int quizId = 1;
        List<QuestionDto> expectedQuestions = Arrays.asList(createSampleQuestion(), createSampleQuestion());

        when(quizService.getAllQuestionByQuiz(quizId)).thenReturn(expectedQuestions);

        ResponseEntity<List<QuestionDto>> responseEntity = quizController.getAllQuestionByQuiz(quizId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedQuestions, responseEntity.getBody());
    }

    private QuizDTO createSampleQuiz() {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuizId(1);
        quizDTO.setQuizName("Sample Quiz");
        return quizDTO;
    }

    private QuestionDto createSampleQuestion() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setQuestionText("Sample Question");
        return questionDto;
    }
}