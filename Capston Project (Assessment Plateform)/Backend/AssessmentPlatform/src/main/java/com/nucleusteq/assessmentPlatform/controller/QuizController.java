package com.nucleusteq.assessmentPlatform.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.service.QuizService;
import com.nucleusteq.assessmentPlatform.utility.QuizLoggerMessage;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

import jakarta.validation.Valid;

/**
 * Controller class for managing Quizzes.
 */
@CrossOrigin
@RestController
@RequestMapping("/quizzes")
public class QuizController {

    /**
     * This is quiz service object it call the quiz methods.
     *
     */
    @Autowired
    private QuizService quizService;

    /**
     * this is logger object that is use to generate log.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuizController.class);
    /**
     * Adds a new quiz.
     * @param quizDTO The QuizDTO object containing Quiz information.
     * @return A message indicating the result of the quiz addition.
     */
    @PostMapping
    public final ResponseEntity<SuccessResponse> addQuiz(@Valid @RequestBody
            final QuizDTO quizDTO) {
        LOGGER.info(QuizLoggerMessage.ADD_QUIZ_REQUEST);
        SuccessResponse createdQuiz = quizService.addQuiz(quizDTO);
        LOGGER.info(QuizLoggerMessage.QUIZ_CREATED_SUCCESSFULLY);
        return new ResponseEntity<>(createdQuiz, HttpStatus.CREATED);
    }

    /**
     * Updates a quiz.
     * @param quizId  The ID of the quiz to update.
     * @param quizDTO The updated quizDto object.
     * @return The updated quizDto object.
     * @throws NotFoundException If the user's email domain is invalid.
     */
    @PutMapping("/{quizId}")
    public final ResponseEntity<SuccessResponse> updateQuiz(@PathVariable
            final Integer quizId, @Valid @RequestBody final QuizDTO quizDTO)
            throws NotFoundException {
        LOGGER.info(QuizLoggerMessage.UPDATE_QUIZ_REQUEST);
        SuccessResponse updatedQuiz = quizService.updateQuiz(quizId, quizDTO);
        LOGGER.info(QuizLoggerMessage.QUIZ_UPDATED_SUCCESSFULLY);
        return new ResponseEntity<>(updatedQuiz, HttpStatus.OK);
    }

    /**
     * Deletes a quiz by its ID.
     * @param quizId The ID of the quiz to delete.
     * @return A message indicating the result of the quiz deletion.
     * @throws NotFoundException If the user's email domain is invalid.
     */
    @DeleteMapping("/{quizId}")
    public final ResponseEntity<SuccessResponse> deleteQuiz(
            @PathVariable final Integer quizId) throws NotFoundException {
        LOGGER.info(QuizLoggerMessage.DELETE_QUIZ_REQUEST + quizId);
        SuccessResponse response = quizService.deleteQuiz(quizId);
        LOGGER.info(
                QuizLoggerMessage.DELETE_QUIZ_REQUEST_SUCCESSFULLY + quizId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a quiz by its ID.
     * @param quizId The ID of the quiz to retrieve.
     * @return The quizDto object representing the retrieved quiz.
     * @throws NotFoundException If the user's email domain is invalid.
     */
    @GetMapping("/{quizId}")
    public final ResponseEntity<QuizDTO> getQuizById(
            @PathVariable final Integer quizId) throws NotFoundException {
        LOGGER.info(QuizLoggerMessage.GET_QUIZ_BY_ID_REQUEST + quizId);
        QuizDTO quizDTO = quizService.getQuizById(quizId);
        LOGGER.info(QuizLoggerMessage.QUIZ_UPDATED_SUCCESSFULLY);
        return new ResponseEntity<>(quizDTO, HttpStatus.OK);
    }

    /**
     * Retrieves a list of all quizzes.
     * @return A list of quizDto objects representing all quizzes.
     */
    @GetMapping
    public final ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        LOGGER.info(QuizLoggerMessage.GET_ALL_QUIZZES_REQUEST);
        List<QuizDTO> quizzes = quizService.getAllQuizzes();
        LOGGER.info(QuizLoggerMessage.LIST_OF_QUESTIONS_RETRIEVED_SUCCESSFULLY);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param quizId The ID of the category to retrieve.
     * @return The list of questions entity.
     */
    @GetMapping("questions/{quizId}")
    public final ResponseEntity<List<QuestionDto>> getAllQuestionByQuiz(
            @PathVariable final int quizId) {
        LOGGER.info(
                QuizLoggerMessage.GET_ALL_QUESTIONS_BY_QUIZ_REQUEST + quizId);
        List<QuestionDto> questions = quizService.getAllQuestionByQuiz(quizId);
        LOGGER.info(QuizLoggerMessage.LIST_OF_QUESTIONS_RETRIEVED_SUCCESSFULLY);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
