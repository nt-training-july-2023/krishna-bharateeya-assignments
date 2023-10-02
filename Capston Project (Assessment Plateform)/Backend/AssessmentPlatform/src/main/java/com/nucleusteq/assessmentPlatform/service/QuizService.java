package com.nucleusteq.assessmentPlatform.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

/**
 * Service interface for managing quizzes.
 */
public interface QuizService {

    /**
     * Adds a new quiz.
     * @param quizDTO The DTO containing quiz information.
     * @return A message indicating the result of the operation.
     */
    SuccessResponse addQuiz(QuizDTO quizDTO);

    /**
     * Deletes a quiz.
     * @param quizId The ID of the quiz to delete.
     * @return response to in string format.
     * @throws NotFoundException If the quizId is invalid.
     */
    SuccessResponse deleteQuiz(Integer quizId) throws NotFoundException;

    /**
     * Retrieves a quiz by its ID.
     * @param quizId The ID of the quiz to retrieve.
     * @return The retrieved quizDto.
     * @throws NotFoundException If the quizId is invalid.
     */
    QuizDTO getQuizById(Integer quizId) throws NotFoundException;

    /**
     * Retrieves all quiz.
     *
     * @return A list of all quizzes.
     */
    List<QuizDTO> getAllQuizzes();

    /**
     * Updates a quiz.
     * @param quizId The update the quiz information.
     * @param quizDTO The updated quiz information.
     * @return The updated quizDto.
     * @throws NotFoundException If the quizId is invalid.
     */
    SuccessResponse updateQuiz(Integer quizId, QuizDTO quizDTO
            )throws NotFoundException;

    /**
     * Updates a category.
     * @param quizId to get quiz by category .
     * @return The List Of Questions  Entity.
     */
    List<QuestionDto> getAllQuestionByQuiz(int quizId);
}
