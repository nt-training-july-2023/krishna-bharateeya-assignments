package com.nucleusteq.assessmentPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nucleusteq.assessmentPlatform.dto.QuestionDto;
import com.nucleusteq.assessmentPlatform.service.QuestionService;

/**
 * This controller class, `QuestionController`, handles HTTP requests related
 * to. managing questions in the assessment platform. It provides end points
 * for. adding, updating, deleting, and retrieving questions.
 */
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    /**
     * this is use to call the question service methods.
     */
    @Autowired
    private QuestionService questionService;

    /**
     * Adds a new question to the assessment platform.
     * @param questionDto The DTO representing the question to be added.
     * @return A ResponseEntity with a success and HTTP status 201 (Created).
     */
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(
            @RequestBody QuestionDto questionDto) {
        String result = questionService.addQuestion(questionDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * Updates an existing question in the assessment platform.
     * 
     * @param questionId  The ID of the question to be updated.
     * @param questionDto The QuestionDto containing the updated question data.
     * @return A ResponseEntity with a success message and HTTP status 200 (OK).
     * @throws NotFoundException if the specified question is not found.
     */
    @PutMapping("/update/{questionId}")
    public ResponseEntity<String> updateQuestion(
            @PathVariable Integer questionId,
            @RequestBody QuestionDto questionDto) throws NotFoundException {
        String result = questionService.updateQuestion(questionId, questionDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Deletes a question from the assessment platform.
     * 
     * @param questionId The ID of the question to be deleted.
     * @return A ResponseEntity with HTTP status 204 (No Content) on successful.
     *         deletion.
     * @throws NotFoundException if the specified question is not found.
     */
    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer questionId)
            throws NotFoundException {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves a question by its unique ID.
     * @param questionId The ID of the question to be retrieved.
     * @return A ResponseEntity containing the QuestionDto and HTTP status 200.
     *         (OK).
     * @throws NotFoundException if the specified question is not found.
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionDto> getQuestionById(
            @PathVariable Integer questionId) throws NotFoundException {
        QuestionDto questionDto = questionService.getQuestionById(questionId);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    /**
     * Retrieves all questions available in the assessment platform.
     * 
     * @return A ResponseEntity containing a list of QuestionDto objects and.
     *         HTTP status 200 (OK).
     */
    @GetMapping("/all")
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        List<QuestionDto> questions = questionService.getAllQuestion();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
