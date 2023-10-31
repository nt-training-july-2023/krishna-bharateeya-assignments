package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.HttpStatus;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Question;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.CategoryRepository;
import com.nucleusteq.assessmentPlatform.repository.QuizRepository;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

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
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(2021);
        categoryDto.setCategoryName("cat1");
        categoryDto.setDescription("cat desc");
        
        Category category = new Category();
        category.setCategoryId(2021);
        category.setCategoryName("cat1");
        category.setDescription("cat desc");
        
        QuizDTO quizDto = new QuizDTO();
        quizDto.setQuizId(1);
        quizDto.setQuizName("Sample Quiz");
        quizDto.setCategory(categoryDto);

        Quiz quiz = new Quiz();
        quiz.setQuizId(1);
        quiz.setQuizName("Sample Quiz");
        when(categoryRepository.findById(2021)).thenReturn(Optional.of(category));
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        when(modelMapper.map(quiz, QuizDTO.class)).thenReturn(quizDto);
        when(quizRepository.findByQuizName(quiz.getQuizName())).thenReturn(Optional.empty());
        when(quizRepository.save(any(Quiz.class))).thenAnswer(invocation -> {
            Quiz savedQuiz = invocation.getArgument(0);
            return savedQuiz;
        });
        
        SuccessResponse expectedResponse = new SuccessResponse(
                HttpStatus.CREATED.value(),
                "Quiz created successfully."
            );

        SuccessResponse response = quizService.addQuiz(quizDto);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        assertEquals(expectedResponse, response);

    }


    @Test
    void testUpdateQuiz() throws NotFoundException {
        Integer quizId = 4028;

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
        
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizId);
        quiz.setQuizName("Sample Quiz");
        quiz.setQuizDescription("this is description");
        quiz.setTimeInMinutes(60);
        quiz.setCategory(category);
        
        Optional<Quiz> optionalQuiz = Optional.of(quiz);
        when(quizRepository.findById(quizId)).thenReturn(optionalQuiz);
        when(modelMapper.map(quizDTO, Quiz.class)).thenReturn(quiz);
        SuccessResponse response = quizService.updateQuiz(quizDTO.getQuizId(), quizDTO);

        SuccessResponse expectedResponse = new SuccessResponse(
                HttpStatus.OK.value(),
                "Quiz updated successfully."
            );
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(expectedResponse, response);
        
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
 
    @Test
    public void testConvertQuizEntityToDTO() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("ExistingCategory");
        categoryDto.setDescription("description");

        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());

        Quiz sampleQuiz = new Quiz();
        sampleQuiz.setQuizId(1);
        sampleQuiz.setQuizName("Sample Quiz");
        sampleQuiz.setCategory(category);
        when(modelMapper.map(sampleQuiz, QuizDTO.class)).thenReturn(
                new QuizDTO(1, "Sample Quiz", "Sample Quiz Description", 60, categoryDto));
        when(modelMapper.map(category, CategoryDto.class)).thenReturn(categoryDto);
    }

    
    
    @Test
    public void testGetAllQuestionByQuiz() {
        
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("ExistingCategory");
        categoryDto.setDescription("description");

        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setDescription(categoryDto.getDescription());

        int quizId = 1;
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizId);
        quiz.setCategory(category);
        
        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setQuestionId(101);
        question1.setQuestionText("Question 1");
        question1.setQuiz(quiz); 
        questions.add(question1);

        quiz.setQuestions(questions);

        when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        List<QuestionDto> questionDtos = quizService.getAllQuestionByQuiz(quizId);

        assertNotNull(questionDtos);
        assertEquals(questions.size(), questionDtos.size());
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            QuestionDto questionDto = questionDtos.get(i);

            assertEquals(question.getQuestionId(), questionDto.getQuestionId());
            assertEquals(question.getQuestionText(), questionDto.getQuestionText());
        }
    }
    
    
    @Test
    public void testEnableQuiz() {
        Quiz quiz = new Quiz();
        quiz.setQuizId(1);
        quiz.setEnabled(false);

        when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

        quizService.enableQuiz(1);
        assertTrue(quiz.isEnabled());
    }

    @Test
    public void testEnableQuizNotFound() {
        when(quizRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> quizService.enableQuiz(1));
    }

    
    @Test
    public void testDisableQuiz() {

        Quiz quiz = new Quiz();
        quiz.setQuizId(1);
        quiz.setEnabled(true);

        when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));
        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);
        quizService.disableQuiz(1);
        assertFalse(quiz.isEnabled());
    }

    @Test
    public void testDisableQuizNotFound() {
        
        when(quizRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> quizService.disableQuiz(1));

    }

}
