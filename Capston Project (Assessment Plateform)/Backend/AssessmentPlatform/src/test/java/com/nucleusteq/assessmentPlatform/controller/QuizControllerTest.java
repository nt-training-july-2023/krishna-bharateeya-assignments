package com.nucleusteq.assessmentPlatform.controller;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.service.QuizService;
@WebMvcTest(QuizController.class)
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizService quizService;

    @Mock
    private QuizDTO sampleQuizDTO;

    @BeforeEach
    public void setUp() {
        sampleQuizDTO = new QuizDTO();
        sampleQuizDTO.setQuizId(1);
        sampleQuizDTO.setQuizName("Sample Quiz");
        sampleQuizDTO.setQuizDescription("This is a test quiz");
        sampleQuizDTO.setTimeInMinutes(30);
    }

    @Test
    public void testAddQuiz() throws Exception {
        when(quizService.addQuiz(any(QuizDTO.class))).thenReturn("Quiz added successfully");

        mockMvc.perform(post("/quizzes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"quizName\":\"Sample Quiz\",\"quizDescription\":\"This is a test quiz\",\"timeInMinutes\":30}")
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string("Quiz added successfully"));
    }

    @Test
    public void testUpdateQuiz() throws Exception {
        when(quizService.updateQuiz(anyInt(), any(QuizDTO.class))).thenReturn("Quiz updated successfully");

        mockMvc.perform(put("/quizzes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"quizName\":\"Sample Quiz\",\"quizDescription\":\"This is an updated test quiz\",\"timeInMinutes\":45}")
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string("Quiz updated successfully"));
    }

    @Test
    public void testDeleteQuiz() throws Exception {
        doNothing().when(quizService).deleteQuiz(anyInt());

        mockMvc.perform(delete("/quizzes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetQuizById() throws NotFoundException {
        int quizId = 1;
        QuizDTO quizDto = new QuizDTO();
        quizDto.setQuizId(quizId);
        quizDto.setQuizName("React");

        QuizService quizService = mock(QuizService.class); 
        when(quizService.getQuizById(quizId)).thenReturn(quizDto);

        QuizController quizController = new QuizController(quizService); 

        ResponseEntity<QuizDTO> result = quizController.getQuizById(quizId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(quizDto, result.getBody());
        assertEquals("React", result.getBody().getQuizName());
        verify(quizService).getQuizById(quizId);
    }

    @Test
    void testGetAllQuizzes() {
        List<QuizDTO> quizDTOList = Collections.singletonList(sampleQuizDTO);

        QuizService quizService = mock(QuizService.class); 
        when(quizService.getAllQuizzes()).thenReturn(quizDTOList);

        QuizController quizController = new QuizController(quizService); 

        ResponseEntity<List<QuizDTO>> result = quizController.getAllQuizzes();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(quizDTOList, result.getBody());
        assertEquals(sampleQuizDTO.getQuizId(), result.getBody().get(0).getQuizId());
        verify(quizService).getAllQuizzes();
    }
}
