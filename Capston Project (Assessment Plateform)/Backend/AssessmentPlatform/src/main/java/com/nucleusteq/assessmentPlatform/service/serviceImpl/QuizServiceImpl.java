package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.CategoryDto;
import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Question;
import com.nucleusteq.assessmentPlatform.entity.QuestionOptions;
import com.nucleusteq.assessmentPlatform.entity.Quiz;
import com.nucleusteq.assessmentPlatform.exception.AlreadyExistsException;
import com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.CategoryRepository;
import com.nucleusteq.assessmentPlatform.repository.QuizRepository;
import com.nucleusteq.assessmentPlatform.service.QuizService;

/**
 * Implementation of the CategoryService interface for managing categories.
 */
@Service
public class QuizServiceImpl implements QuizService {

    /**
     * This is Quiz Repository object that is for calling. the repository.
     * methods.
     */
    @Autowired
    private QuizRepository quizRepository;

    /**
     * This is Category Repository object that is for calling. the repository.
     * methods.
     */
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * This is use to map the category with Dto and viceversa..
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * this is logger object that is use to generate log.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuizServiceImpl.class);

    /**
     * Adds a new quiz.
     *
     * @param quizDTO The DTO containing category information.
     * @return A message indicating the result of the operation.
     */
    @Override
    public final String addQuiz(final QuizDTO quizDTO) {

        Optional<Quiz> existingQuiz = quizRepository
                .findByQuizName(quizDTO.getQuizName());
        if (existingQuiz.isPresent()) {
            LOGGER.error("Quiz with the same name already exists.");
            throw new AlreadyExistsException(
                    "Quiz with the same name already exists");
        }
        Optional<Category> categoryDto = categoryRepository
                .findById(quizDTO.getCategory().getCategoryId());

        if (!categoryDto.isPresent()) {
            LOGGER.error("Category object not found for id: "
                    + quizDTO.getCategory().getCategoryId());
            throw new ResourceNotFoundException(
                    "Category object not found for id: "
                            + quizDTO.getCategory().getCategoryId());
        }

        Quiz quiz = convertToEntity(quizDTO);
        quizRepository.save(quiz);

        return "Quiz added successfully";
    }

    /**
     * Updates a quiz.
     * @param quizId  The ID of the quiz.
     * @param quizDTO The DTO containing updated quiz information.
     * @return The updated String.
     */
    @Override
    public final String updateQuiz(final Integer quizId, final QuizDTO quizDTO)
            throws ResourceNotFoundException {
        Quiz existingQuiz = quizRepository.findById(quizId).orElseGet(() -> {
            LOGGER.error("Quiz not found for id " + quizId);
            throw new ResourceNotFoundException(
                    "Quiz not found for id " + quizId);
        });
        if (!existingQuiz.getQuizName().equals(quizDTO.getQuizName())
                && quizRepository.findByQuizName(quizDTO.getQuizName())
                        .isPresent()) {
            LOGGER.error("This quiz Already Exist.");
            throw new AlreadyExistsException("This quiz Already Exist.");
        }

        existingQuiz.setQuizName(quizDTO.getQuizName());
        existingQuiz.setQuizDescription(quizDTO.getQuizDescription());
        existingQuiz.setTimeInMinutes(quizDTO.getTimeInMinutes());
        existingQuiz.setCategory(categoryRepository
                .findById(quizDTO.getCategory().getCategoryId())
                .orElseGet(() -> {
                    LOGGER.error("Category object not found for id :"
                            + quizDTO.getCategory().getCategoryId());
                    throw new ResourceNotFoundException(
                            "Category object not found for id :"
                                    + quizDTO.getCategory().getCategoryId());
                }));
        quizRepository.save(existingQuiz);
        return "Quiz updated successfully";

    }

    /**
     * Deletes a quiz.
     *
     * @param quizId The ID of the quiz to delete.
     *
     */
    @Override
    public final String deleteQuiz(final Integer quizId)
            throws ResourceNotFoundException {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> {
                    LOGGER.error("Quiz not found with Id : {}", quizId);
                    return new ResourceNotFoundException(
                            "Quiz with ID " + quizId + " not found");
                });
        quizRepository.deleteById(quiz.getQuizId());
        return "Quiz deleted successfully with id :" + quizId;

    }

    /**
     * Retrieves a quiz by ID.
     *
     * @param quizId The ID of the quiz to retrieve.
     * @return The quizDto representing the quiz.
     * @throws RuntimeException If the quiz is not found.
     */
    @Override
    public final QuizDTO getQuizById(final Integer quizId)
            throws ResourceNotFoundException {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> {
            LOGGER.error("Quiz not found with Id : {}", quizId);
            return new ResourceNotFoundException(
                    "Quiz with ID " + quizId + " not found");
        });
        return convertToDTO(quiz);
    }

    /**
     * Retrieves a list of all quizzes.
     *
     * @return A list of CategoryDto objects representing quizzes.
     */
    @Override
    public final List<QuizDTO> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts a quiz entity to a QuizDTO.
     *
     * @param quiz The quiz entity to convert.
     * @return The converted QuizDTO.
     */
    QuizDTO convertToDTO(final Quiz quiz) {
        QuizDTO quizDTO = modelMapper.map(quiz, QuizDTO.class);
        if (quiz.getCategory() != null) {
            CategoryDto categoryDto = modelMapper.map(quiz.getCategory(),
                    CategoryDto.class);
            quizDTO.setCategory(categoryDto);
        }
        return quizDTO;
    }

    /**
     * Converts a quizDTO to a quiz entity.
     *
     * @param quizDTO The quizDTO to convert.
     * @return The converted quiz entity.
     */
    Quiz convertToEntity(final QuizDTO quizDTO) {
        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);
        if (quizDTO.getCategory() != null) {
            Category category = modelMapper.map(quizDTO.getCategory(),
                    Category.class);
            quiz.setCategory(category);
        }
        return quiz;
    }

    /**
     * Converts a quizDTO to a quiz entity.
     *
     * @param quizId To find the questions.
     * @return The list of question entity.
     */
    @Override
    public final List<QuestionDto> getAllQuestionByQuiz(final int quizId) {

        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        List<Question> questions = optionalQuiz.get().getQuestions();
        return questions.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * @param question The object to be converted.
     *
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
