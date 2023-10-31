package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Question;
import com.nucleusteq.assessmentPlatform.entity.QuestionOptions;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.exception.DuplicateOptionException;
import com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.QuestionRepository;
import com.nucleusteq.assessmentPlatform.repository.QuizRepository;
import com.nucleusteq.assessmentPlatform.service.QuestionService;
import com.nucleusteq.assessmentPlatform.utility.Message;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

/**
 * This class, `QuestionServiceImpl`, is the implementation of the.
 * `QuestionService` interface.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    /**
     * this is use to call the question repository object.
     */
    @Autowired
    private QuestionRepository questionRepository;
    /**
     * this is use to call the quiz repository object.
     */
    @Autowired
    private QuizRepository quizRepository;
    /**
     * Creating a instance of Logger Class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuestionServiceImpl.class);

    /**
     * Creating a instance to check duplicate.
     */
    private static final int EXPECTED_NUMBER_OF_OPTIONS = 4;

    /**
     * Adds a new question to the assessment platform.
     * @param questionDto The DTO (Data Transfer Object).
     * @return A message indicating the success of the operation.
     */
    @Override
    public final SuccessResponse addQuestion(final QuestionDto questionDto) {

        Question resQue = convertDtoToEntity(questionDto);

        Set<String> optionSet = new HashSet<>();
        optionSet.add(resQue.getOptionOne());
        optionSet.add(resQue.getOptionTwo());
        optionSet.add(resQue.getOptionThree());
        optionSet.add(resQue.getOptionFour());

//        if (optionSet.size() < EXPECTED_NUMBER_OF_OPTIONS) {
//          LOGGER.error(Message.DUPLICATE_OPTION_ERROR);
//          throw new DuplicateOptionException(Message.DUPLICATE_OPTION_ERROR);
//        }

        String checkCorrectAnswer = resQue.getCorrectOption();
        if (!checkCorrectAnswer.equals(resQue.getOptionOne())
                && !checkCorrectAnswer.equals(resQue.getOptionTwo())
                && !checkCorrectAnswer.equals(resQue.getOptionThree())
                && !checkCorrectAnswer.equals(resQue.getOptionFour())) {
            LOGGER.error(Message.CORRECT_OPTION_ERROR);
            throw new ResourceNotFoundException(
                    Message.CORRECT_OPTION_ERROR);
        }
        Optional<Quiz> existingQuiz = quizRepository
                .findById(resQue.getQuiz().getQuizId());
        if (existingQuiz.isEmpty()) {
            throw new ResourceNotFoundException(
                    Message.QUIZ_NOT_FOUND + resQue.getQuiz().getQuizId());
        }
        questionRepository.save(resQue);
        return new SuccessResponse(HttpStatus.CREATED.value(),
                Message.QUESTION_CREATED_SUCCESSFULLY);

    }

    /**
     * Updates an existing question in the assessment platform.
     * @param questionId  The ID of the question to be updated.
     * @param questionDto The DTO containing the updated question data.
     * @return A message indicating the success of the operation.
     * @throws NotFoundException if the specified question is not found.
     */
    @Override
    public final SuccessResponse updateQuestion(final Integer questionId,
            final QuestionDto questionDto) throws NotFoundException {
        Optional<Question> optionalQuestion = questionRepository
                .findById(questionId);
        if (!optionalQuestion.isPresent()) {
            LOGGER.error(Message.QUESTION_NOT_FOUND + questionId);
            throw new ResourceNotFoundException(
                    Message.QUESTION_NOT_FOUND + questionId);
        }

        Question updatedQuestion = convertDtoToEntity(questionDto);
        String checkCorrectAnswer = updatedQuestion.getCorrectOption();
        if (!checkCorrectAnswer.equals(updatedQuestion.getOptionOne())
                && !checkCorrectAnswer.equals(updatedQuestion.getOptionTwo())
                && !checkCorrectAnswer.equals(updatedQuestion.getOptionThree())
                && !checkCorrectAnswer
                        .equals(updatedQuestion.getOptionFour())) {
            LOGGER.error(Message.CORRECT_OPTION_ERROR);
            throw new ResourceNotFoundException(
                    Message.CORRECT_OPTION_ERROR);
        }
        updatedQuestion.setQuestionId(questionId);
        questionRepository.save(updatedQuestion);
        return new SuccessResponse(HttpStatus.OK.value(),
                Message.QUESTION_UPDATED_SUCCESSFULLY);

    }

    /**
     * Deletes a question from the assessment platform.
     * @param questionId The ID of the question to be deleted.
     * @throws NotFoundException if the specified question is not found.
     */
    @Override
    public final SuccessResponse deleteQuestion(final Integer questionId)
            throws NotFoundException {
        Optional<Question> optionalQuestion = questionRepository
                .findById(questionId);
        if (!optionalQuestion.isPresent()) {
            LOGGER.error(Message.QUESTION_NOT_FOUND + questionId);
            throw new ResourceNotFoundException(
                    Message.QUESTION_NOT_FOUND + questionId);
        }
        questionRepository.delete(optionalQuestion.get());
        return new SuccessResponse(HttpStatus.OK.value(),
                Message.QUESTION_DELETED_SUCCESSFULLY);

    }

    /**
     * Retrieves a question by its unique ID.
     * @param questionId The ID of the question to be retrieved.
     * @return The DTO representing the retrieved question.
     * @throws NotFoundException if the specified question is not found.
     */
    @Override
    public final QuestionDto getQuestionById(final Integer questionId)
            throws NotFoundException {
        Optional<Question> optionalQuestion = questionRepository
                .findById(questionId);
        if (!optionalQuestion.isPresent()) {
            LOGGER.error(Message.QUESTION_NOT_FOUND  + questionId);
            throw new ResourceNotFoundException(
                    Message.QUESTION_NOT_FOUND  + questionId);
        }
        Question question = optionalQuestion.get();
        QuestionDto resultQueDto = convertEntityToDto(question);
        return resultQueDto;

    }

    /**
     * Retrieves all questions available in the assessment platform.
     * @return A list of DTOs representing all available questions.
     */
    @Override
    public final List<QuestionDto> getAllQuestion() {
        List<Question> questions = questionRepository.findAll();

        return questions.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * @param questionDto The object to be converted.
     * @return the converted into question entity.
     */
    private Question convertDtoToEntity(final QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionId(questionDto.getQuestionId());
        question.setQuestionText(questionDto.getQuestionText());
        question.setOptionOne(questionDto.getOptions().getOptionOne());
        question.setOptionTwo(questionDto.getOptions().getOptionTwo());
        question.setOptionThree(questionDto.getOptions().getOptionThree());
        question.setOptionFour(questionDto.getOptions().getOptionFour());
        question.setCorrectOption(questionDto.getOptions().getCorrectOption());

        Category category = new Category();
        category.setCategoryId(
                questionDto.getQuiz().getCategory().getCategoryId());
        category.setCategoryName(
                questionDto.getQuiz().getCategory().getCategoryName());
        category.setDescription(
                questionDto.getQuiz().getCategory().getDescription());

        Quiz quiz = new Quiz();
        quiz.setQuizId(questionDto.getQuiz().getQuizId());
        quiz.setQuizName(questionDto.getQuiz().getQuizName());
        quiz.setQuizDescription(questionDto.getQuiz().getQuizDescription());
        quiz.setTimeInMinutes(questionDto.getQuiz().getTimeInMinutes());
        quiz.setCategory(category);

        question.setQuiz(quiz);

        return question;
    }

    /**
     * @param question The object to be converted.
     * @return the converted into QuestionDto entity.
     */
    private QuestionDto convertEntityToDto(final Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setQuestionText(question.getQuestionText());
        QuestionOptions options = new QuestionOptions(question.getOptionOne(),
                question.getOptionTwo(), question.getOptionThree(),
                question.getOptionFour(), question.getCorrectOption());
        questionDto.setOptions(options);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(
                question.getQuiz().getCategory().getCategoryId());
        categoryDto.setCategoryName(
                question.getQuiz().getCategory().getCategoryName());
        categoryDto.setDescription(
                question.getQuiz().getCategory().getDescription());

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
