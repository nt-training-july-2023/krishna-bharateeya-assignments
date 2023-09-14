package com.nucleusteq.assessmentPlatform.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.Question;

@Service
public  interface QuestionService {

    /**
     * Adds a new quiz.
     *
     * @param quizDTO The DTO containing quiz information.
     * @return A message indicating the result of the operation.
     */
     String addQuestion(QuestionDto questionDto);

     /**
      * Updates a quiz.
      * @param quizId The update the quiz information.
      * @param quizDTO The updated quiz information.
      * @return The updated quizDto.
      * @throws NotFoundException If the quizId is invalid.
      */
     
     String updateQuestion(Integer questionId, QuestionDto questionDto)throws NotFoundException;
    /**
     * Deletes a quiz.
     *
     * @param quizId The ID of the quiz to delete.
     * @throws NotFoundException If the quizId is invalid.
     */
     
    void deleteQuestion(Integer questionId) throws NotFoundException;

    /**
     * Retrieves a quiz by its ID.
     *
     * @param quizId The ID of the quiz to retrieve.
     * @return The retrieved quizDto.
     * @throws NotFoundException If the quizId is invalid.
     */
    QuestionDto getQuestionById(Integer questionId) throws NotFoundException;

    /**
     * Retrieves all quiz.
     *
     * @return A list of all quizzes.
     */
    List<QuestionDto> getAllQuestion();

    
}
