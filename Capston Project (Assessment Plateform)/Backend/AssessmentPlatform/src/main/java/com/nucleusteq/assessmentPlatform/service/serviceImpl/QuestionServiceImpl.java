package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Question;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.repository.QuestionRepository;
import com.nucleusteq.assessmentPlatform.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public String addQuestion(QuestionDto questionDto) {
        
        Question resQue=convertDtoToEntity(questionDto);
        questionRepository.save(resQue);
        return "Question added successfully";
    }

    @Override
    public String updateQuestion(Integer questionId, QuestionDto questionDto) throws NotFoundException {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            
            Question convertedQuestion=convertDtoToEntity(questionDto);
            questionRepository.save(convertedQuestion);
            return "Question updated successfully";
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void deleteQuestion(Integer questionId) throws NotFoundException {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            questionRepository.delete(optionalQuestion.get());
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public QuestionDto getQuestionById(Integer questionId) throws NotFoundException {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            
            QuestionDto resultQueDto=convertEntityToDto(question);
            return resultQueDto;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<QuestionDto> getAllQuestion() {
        List<Question> questions = questionRepository.findAll();
        
        return questions.stream()
                       .map(this::convertEntityToDto)
                       .collect(Collectors.toList());
    }

    private Question convertDtoToEntity(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionId(questionDto.getQuestionId());
        question.setQuestionText(questionDto.getQuestionText());
        question.setOptionOne(questionDto.getOptionOne());
        question.setOptionTwo(questionDto.getOptionTwo());
        question.setOptionThree(questionDto.getOptionThree());
        question.setOptionFour(questionDto.getOptionFour());
        question.setCorrectOption(questionDto.getCorrectOption());

        Category category = new Category();
        category.setCategoryId(questionDto.getQuiz().getCategory().getCategoryId());
        category.setCategoryName(questionDto.getQuiz().getCategory().getCategoryName());
        category.setDescription(questionDto.getQuiz().getCategory().getDescription());

        Quiz quiz = new Quiz();
        quiz.setQuizId(questionDto.getQuiz().getQuizId());
        quiz.setQuizName(questionDto.getQuiz().getQuizName());
        quiz.setQuizDescription(questionDto.getQuiz().getQuizDescription());
        quiz.setTimeInMinutes(questionDto.getQuiz().getTimeInMinutes());
        quiz.setCategory(category); 

        question.setQuiz(quiz);

        return question;
    }


    private QuestionDto convertEntityToDto(Question question) {
        System.out.println("the question dfdfdf:"+question.getQuiz().getQuizName());
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setQuestionText(question.getQuestionText());
        questionDto.setOptionOne(question.getOptionOne());
        questionDto.setOptionTwo(question.getOptionTwo());
        questionDto.setOptionThree(question.getOptionThree());
        questionDto.setOptionFour(question.getOptionFour());
        questionDto.setCorrectOption(question.getCorrectOption());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(question.getQuiz().getCategory().getCategoryId());
        categoryDto.setCategoryName(question.getQuiz().getCategory().getCategoryName());
        categoryDto.setDescription(question.getQuiz().getCategory().getDescription());
        
        QuizDTO quizDto = new QuizDTO();
        quizDto.setQuizId(question.getQuiz().getQuizId());
        quizDto.setQuizName(question.getQuiz().getQuizName());
        quizDto.setQuizDescription(question.getQuiz().getQuizDescription());
        quizDto.setTimeInMinutes(question.getQuiz().getTimeInMinutes());
        quizDto.setCategory(categoryDto);
        
        questionDto.setQuiz(quizDto);
        
        return questionDto;
    }

}
