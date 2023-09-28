package com.nucleusteq.assessmentPlatform.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.QuestionDto;

/**
 * This is `QuestionService` interface.
 */
@Service
public interface QuestionService {

    /**
     * Adds a new quiz.
     * @param questionDto The DTO containing quiz information.
     * @return A message indicating the result of the operation.
     */
    String addQuestion(QuestionDto questionDto);

    /**
     * Updates a quiz.
     * @param questionId  The update the question information.
     * @param questionDto The updated question information.
     * @return The updated quizDto.
     * @throws NotFoundException If the quizId is invalid.
     */

    String updateQuestion(Integer questionId, QuestionDto questionDto)
            throws NotFoundException;

    /**
     * Deletes a quiz.
     *
     * @param questionId The ID of the question to delete.
     * @return 
     * @throws NotFoundException If the quizId is invalid.
     */

    String deleteQuestion(Integer questionId) throws NotFoundException;

    /**
     * Retrieves a quiz by its ID.
     *
     * @param questionId The ID of the question to retrieve.
     * @return The retrieved questionDto.
     * @throws NotFoundException If the quizId is invalid.
     */
    QuestionDto getQuestionById(Integer questionId) throws NotFoundException;

    /**
     * Retrieves all questions.
     *
     * @return A list of all questions.
     */
    List<QuestionDto> getAllQuestion();

}
