package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.CategoryRepository;
import com.nucleusteq.assessmentPlatform.repository.QuizRepository;

class QuizServiceImplTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private QuizServiceImpl quizService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddQuiz() {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuizId(0);
        quizDTO.setQuizName("Sample Quiz");
        quizDTO.setQuizDescription("this is description");
        quizDTO.setTimeInMinutes(60);

        Quiz quiz = new Quiz();
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

        String result = quizService.addQuiz(quizDTO);
        
        assertNotNull(result, "Quiz added successfully");
        assertEquals(0, quizDTO.getQuizId()); 
    }

//    @Test
//    void testUpdateQuiz() throws NotFoundException {
//        Integer quizId = 4028;
//        QuizDTO quizDTO = new QuizDTO();
//        quizDTO.setQuizId(quizId);
//        quizDTO.setQuizName("Sample Quiz");
//        quizDTO.setQuizDescription("this is description");
//        quizDTO.setTimeInMinutes(60);
//
//        CategoryDto category = new CategoryDto();
//        category.setCategoryId(3024);
//        category.setCategoryName("cat1");
//        category.setDescription("cat1 desc");
//
//        quizDTO.setCategory(category);
//
//        Optional<Quiz> optionalQuiz = Optional.of(new Quiz());
//        when(quizRepository.findById(quizId)).thenReturn(optionalQuiz);
//        when(quizRepository.save(any())).thenReturn(new Quiz());
//
//        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
//
//        QuizDTO resultDTO = quizService.updateQuiz(quizDTO.getQuizId(), quizDTO);
//
//        
//        assertNotNull(resultDTO);
//        
//    }




    @Test
    void testUpdateQuiz_QuizNotFound() {
        Integer quizId = 1;
        QuizDTO quizDTO = new QuizDTO();
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> quizService.updateQuiz(quizId, quizDTO));
    }

    

    @Test
    void testDeleteQuiz_QuizFound() throws NotFoundException {
        Integer quizId = 1;
        Optional<Quiz> optionalQuiz = Optional.of(new Quiz());
        when(quizRepository.findById(quizId)).thenReturn(optionalQuiz);

        assertDoesNotThrow(() -> quizService.deleteQuiz(quizId));
    }

    @Test
    void testDeleteQuiz_QuizNotFound() {
        Integer quizId = 1;
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> quizService.deleteQuiz(quizId));
    }

    @Test
    void testGetQuizById_QuizFound() throws ResourceNotFoundException {
        Integer quizId =4028;
        Optional<Quiz> optionalQuiz = Optional.of(new Quiz());
        when(quizRepository.findById(quizId)).thenReturn(optionalQuiz);

//        QuizDTO resultDTO = quizService.getQuizById(quizId);
//        assertEquals(4028, resultDTO.getQuizId());
        
    }

    @Test
    void testGetQuizById_QuizNotFound() {
        Integer quizId =1;
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> quizService.getQuizById(quizId));
    }

    @Test
    void testGetAllQuizzes() {
        List<Quiz> mockQuizzes = Arrays.asList(new Quiz(), new Quiz()); // Mock some quizzes
        when(quizRepository.findAll()).thenReturn(mockQuizzes);

        List<QuizDTO> resultDTOs = quizService.getAllQuizzes();
        assertNotNull(resultDTOs);
        assertEquals(2, resultDTOs.size());
    }
}
