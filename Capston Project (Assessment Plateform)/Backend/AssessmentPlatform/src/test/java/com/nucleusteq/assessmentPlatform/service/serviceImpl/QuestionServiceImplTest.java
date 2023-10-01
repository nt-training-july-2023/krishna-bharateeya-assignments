package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Question;
import com.nucleusteq.assessmentPlatform.entity.QuestionOptions;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.repository.QuestionRepository;
import com.nucleusteq.assessmentPlatform.repository.QuizRepository;

class QuestionServiceImplTest {

    @InjectMocks
    private QuestionServiceImpl questionService;
    
    @Mock
    private QuizRepository quizRepository;
    
    @Mock
    private QuestionServiceImpl questionService2;
    @Mock
    private QuestionRepository questionRepository;

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

      
      when(quizRepository.findById(questionDTO.getQuiz().getQuizId())).thenReturn(Optional.of(new Quiz()));

        when(questionRepository.save(any(Question.class))).thenReturn(new Question());

        String result = questionService.addQuestion(questionDTO);

        assertEquals("Question added successfully", result);
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
        
        Optional<Question> optionalQuestion = Optional.of(new Question());

        when(questionRepository.findById(questionId)).thenReturn(optionalQuestion);
        when(questionRepository.save(any(Question.class))).thenReturn(new Question());

        String result = questionService.updateQuestion(questionId, questionDTO);

        assertEquals("Question updated successfully", result);
    }
    
    @Test
    public void testUpdateQuestionNotFound() {
        Integer questionId = 1;
        CategoryDto categoryDto = new CategoryDto(1, "C. S.","CS Description");

        QuizDTO quizDTO = new QuizDTO(1, "Java", "Java Description", 90, categoryDto); 
        
        QuestionDto questionDTO = new QuestionDto();
        questionDTO.setQuestionId(12);
        questionDTO.setQuestionText("Not a Programming Language :");
        QuestionOptions options = new QuestionOptions("java", "html", "hindi", "python", "hindi");
        questionDTO.setOptions(options);
        questionDTO.setQuiz(quizDTO);
        Optional<Question> optionalQuestion = Optional.empty();

        when(questionRepository.findById(questionId)).thenReturn(optionalQuestion);

        assertThrows(com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException.class, () -> questionService.updateQuestion(questionId, questionDTO));
    }

@Test
public void testDeleteQuestion() throws NotFoundException {
    Integer questionId = 1;
    Optional<Question> optionalQuestion = Optional.of(new Question());

    when(questionRepository.findById(questionId)).thenReturn(optionalQuestion);

    assertDoesNotThrow(() -> questionService.deleteQuestion(questionId));
}

@Test
public void testDeleteQuestionNotFound() {
    Integer questionId = 1;
    Optional<Question> optionalQuestion = Optional.empty();

    when(questionRepository.findById(questionId)).thenReturn(optionalQuestion);

    assertThrows(com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException.class, () -> questionService.deleteQuestion(questionId));
}

@Test
public void testGetQuestionById() throws NotFoundException {
    Integer questionId = 1;
    
    Category category = new Category(1, "C. S.","CS Description");

    Quiz quiz= new Quiz(1, "Java", "Java Description", 90, category); 
    
    Question question = new Question();
    question.setQuestionId(1);
    question.setQuestionText("Not a Programming Language :");
    question.setOptionOne("java");
    question.setOptionTwo("html");
    question.setOptionThree("hindi");
    question.setOptionFour("python");
    question.setCorrectOption("hindi");
    question.setQuiz(quiz);
    
    Optional<Question> optionalQuestion = Optional.of(question);

    when(questionRepository.findById(questionId)).thenReturn(optionalQuestion);

    QuestionDto result = questionService.getQuestionById(questionId);

    assertNotNull(result);
    assertEquals(questionId, result.getQuestionId());
}

@Test
public void testGetQuestionByIdNotFound() {
    Integer questionId = 1;
    Optional<Question> optionalQuestion = Optional.empty();

    when(questionRepository.findById(questionId)).thenReturn(optionalQuestion);

    assertThrows(com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException.class, () -> questionService.getQuestionById(questionId));
}


@Test
public void testGetAllQuestion() {
    
    Category category = new Category(1, "C. S.","CS Description");

    Quiz quiz= new Quiz(1, "Java", "Java Description", 90, category); 
    
    Question question = new Question();
    question.setQuestionId(1);
    question.setQuestionText("Not a Programming Language :");
    question.setOptionOne("java");
    question.setOptionTwo("html");
    question.setOptionThree("hindi");
    question.setOptionFour("python");
    question.setCorrectOption("hindi");
    question.setQuiz(quiz);
   
    List<Question> questionList = new ArrayList<>();
    questionList.add(question);

    when(questionRepository.findAll()).thenReturn(questionList);

    List<QuestionDto> result = questionService.getAllQuestion();

    assertNotNull(result);
    assertFalse(result.isEmpty());
}
}