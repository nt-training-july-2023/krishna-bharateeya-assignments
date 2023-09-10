package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
    public void testAddQuiz() {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuizName("Sample Quiz");
        quizDTO.setQuizDescription("description");
        
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(2021);
        categoryDto.setCategoryName("cat1");
        categoryDto.setDescription("cat desc");
        quizDTO.setCategory(categoryDto);
        
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());
        
        Quiz quiz = new Quiz();
        quiz.setQuizName(quizDTO.getQuizName());
        quiz.setQuizDescription(quizDTO.getQuizDescription());
        quiz.setCategory(category); 
        
        when(quizRepository.findByQuizName(anyString())).thenReturn(Optional.empty());
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

//        String result = quizService.addQuiz(quizDTO);
//        assertEquals("Quiz added successfully", result);
//        verify(quizRepository).save(quiz);
    }

    @Test
    void testUpdateQuiz() throws NotFoundException {
        Integer quizId = 4028;

        Optional<Quiz> optionalQuiz = Optional.of(new Quiz());
        when(quizRepository.findById(quizId)).thenReturn(optionalQuiz);

        when(quizRepository.save(any())).thenReturn(new Quiz());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(3024);
        categoryDto.setCategoryName("cat1");
        categoryDto.setDescription("cat1 desc");

        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());

        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));

        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setQuizId(quizId);
        quizDTO.setQuizName("Sample Quiz");
        quizDTO.setQuizDescription("this is description");
        quizDTO.setTimeInMinutes(60);
        quizDTO.setCategory(categoryDto);

        String resultDTO = quizService.updateQuiz(quizDTO.getQuizId(), quizDTO);

        assertNotNull(resultDTO);        
    }




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
        
        CategoryDto category = new CategoryDto();
        category.setCategoryId(3024);
        category.setCategoryName("Sample Category");
        category.setDescription("Category Description");
        
        Integer quizId = 4029;
        QuizDTO quiz = new QuizDTO();
        quiz.setQuizId(quizId);
        quiz.setQuizName("Sample Quiz");
        quiz.setCategory(category);

        Optional<Quiz> optionalQuiz = Optional.empty();
        when(quizRepository.findById(quizId)).thenReturn(optionalQuiz);
        
//        QuizDTO resultQuiz = quizService.getQuizById(quizId);
//
//        assertNotNull(resultQuiz);
//        assertEquals(quizId, resultQuiz.getQuizId());
//        assertEquals("Sample Quiz", resultQuiz.getQuizName());
//        verify(quizRepository).findById(quizId);
    }

    @Test
    void testGetQuizById_QuizNotFound() {
        Integer quizId =1;
        when(quizRepository.findById(quizId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> quizService.getQuizById(quizId));
    }

    @Test
    public void testGetAllQuizzes() {
        Category category = new Category();
        category.setCategoryId(3024);
        category.setCategoryName("Sample Category");
        category.setDescription("Category Description");

        Quiz quiz = new Quiz();
        quiz.setQuizId(1);
        quiz.setQuizName("Sample Quiz");
        quiz.setCategory(category);

        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(quiz);
        when(quizRepository.findAll()).thenReturn(quizzes);

        when(modelMapper.map(any(Quiz.class), eq(QuizDTO.class))).thenAnswer(invocation -> {
            Quiz source = invocation.getArgument(0);
            QuizDTO target = new QuizDTO();
            target.setQuizName(source.getQuizName());
            return target;
        });

        when(modelMapper.map(any(Category.class), eq(CategoryDto.class))).thenReturn(new CategoryDto());

        List<QuizDTO> quizDTOs = quizService.getAllQuizzes();

        assertEquals(1, quizDTOs.size());
        QuizDTO resultQuizDTO = quizDTOs.get(0);
        assertNotNull(resultQuizDTO);
        assertEquals(quiz.getQuizName(), resultQuizDTO.getQuizName());
        assertNotNull(resultQuizDTO.getCategory());
    }
}
