package com.nucleusteq.assessmentPlatform.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
    public void testGetQuizById() throws Exception {
        when(quizService.getQuizById(anyInt())).thenReturn(sampleQuizDTO);

        mockMvc.perform(get("/quizzes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quizId").value(sampleQuizDTO.getQuizId()))
                .andExpect(jsonPath("$.quizName").value(sampleQuizDTO.getQuizName()))
                .andExpect(jsonPath("$.quizDescription").value(sampleQuizDTO.getQuizDescription()))
                .andExpect(jsonPath("$.timeInMinutes").value(sampleQuizDTO.getTimeInMinutes()));
    }

    @Test
    public void testGetAllQuizzes() throws Exception {
        List<QuizDTO> quizDTOList = Collections.singletonList(sampleQuizDTO);
        when(quizService.getAllQuizzes()).thenReturn(quizDTOList);


        mockMvc.perform(get("/quizzes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].quizId").value(sampleQuizDTO.getQuizId()))
                .andExpect(jsonPath("$[0].quizName").value(sampleQuizDTO.getQuizName()))
                .andExpect(jsonPath("$[0].quizDescription").value(sampleQuizDTO.getQuizDescription()))
                .andExpect(jsonPath("$[0].timeInMinutes").value(sampleQuizDTO.getTimeInMinutes()));
    }
}
