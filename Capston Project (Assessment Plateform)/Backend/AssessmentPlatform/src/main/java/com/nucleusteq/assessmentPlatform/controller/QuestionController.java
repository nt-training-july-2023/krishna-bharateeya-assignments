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
import com.nucleusteq.assessmentPlatform.service.QuestionService;

import jakarta.validation.Valid;

/**
 * This controller class, `QuestionController`, handles HTTP requests related
 * to. managing questions in the assessment platform. It provides end points
 * for. adding, updating, deleting, and retrieving questions.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    /**
     * this is use to call the question service methods.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * this is logger object that is use to generate log.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(QuestionController.class);
    /**
     * Adds a new question to the assessment platform.
     * @param questionDto The DTO representing the question to be added.
     * @return A ResponseEntity with a success and HTTP status 201 (Created).
     */
    @PostMapping("/add")
    public final ResponseEntity<String> addQuestion(
            @RequestBody @Valid final QuestionDto questionDto) {
        LOGGER.info("Received a request to Create a new question.");
        String result = questionService.addQuestion(questionDto);
        LOGGER.info("Question Created Successfully.");
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * Updates an existing question in the assessment platform.
     * @param questionId  The ID of the question to be updated.
     * @param questionDto The QuestionDto containing the updated question data.
     * @return A ResponseEntity with a success message and HTTP status 200 (OK).
     * @throws NotFoundException if the specified question is not found.
     */
    @PutMapping("/update/{questionId}")
    public final ResponseEntity<String> updateQuestion(
            @PathVariable final Integer questionId,
            @Valid @RequestBody final QuestionDto questionDto)
            throws NotFoundException {
        LOGGER.info("Received a request to Create a new question.");
        String result = questionService.updateQuestion(questionId, questionDto);
        LOGGER.info("Question Updated Successfully.");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Deletes a question from the assessment platform.
     * @param questionId The ID of the question to be deleted.
     * @return A ResponseEntity with HTTP status 204 (No Content) on successful.
     *         deletion.
     * @throws NotFoundException if the specified question is not found.
     */
    @DeleteMapping("/delete/{questionId}")
    public final ResponseEntity<String> deleteQuestion(
            @PathVariable final Integer questionId) throws NotFoundException {
        LOGGER.info("Received a request to Delete a question with id :{}"+questionId);
        String response=questionService.deleteQuestion(questionId);
        LOGGER.info("Question deleted Successfully.");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * Retrieves a question by its unique ID.
     * @param questionId The ID of the question to be retrieved.
     * @return A ResponseEntity containing the QuestionDto and HTTP status 200.
     *         (OK).
     * @throws NotFoundException if the specified question is not found.
     */
    @GetMapping("/{questionId}")
    public final ResponseEntity<QuestionDto> getQuestionById(
            @PathVariable final Integer questionId) throws NotFoundException {
        LOGGER.info("Received Request to get question by QuestionId :{}"+questionId);
        QuestionDto questionDto = questionService.getQuestionById(questionId);
        LOGGER.info("Question Retrived Successfully.");
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    /**
     * Retrieves all questions available in the assessment platform.
     * @return A ResponseEntity containing a list of QuestionDto objects and.
     *         HTTP status 200 (OK).
     */
    @GetMapping("/all")
    public final ResponseEntity<List<QuestionDto>> getAllQuestions() {
        LOGGER.info("Retriveing all Questions.");
        List<QuestionDto> questions = questionService.getAllQuestion();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
