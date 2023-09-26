package com.nucleusteq.assessmentPlatform.controller;

import java.util.List;
import java.util.Objects;

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
     * This is quiz controller object it call the quiz methods.
     * @param quService user to assign in the constructor.
     */
    @Autowired
    public QuizController(final QuizService quService) {
        this.quizService = Objects.requireNonNull(quService,
                "quizService must not be null");
    }

    /**
     * Adds a new quiz.
     * @param quizDTO The QuizDTO object containing Quiz information.
     * @return A message indicating the result of the quiz addition.
     */
    @PostMapping
    public final String addQuiz(@Valid @RequestBody final QuizDTO quizDTO) {
        String createdQuiz = quizService.addQuiz(quizDTO);
        return createdQuiz;
    }

    /**
     * Updates a quiz.
     * @param quizId  The ID of the quiz to update.
     * @param quizDTO The updated quizDto object.
     * @return The updated quizDto object.
     * @throws NotFoundException If the user's email domain is invalid.
     */
    @PutMapping("/{quizId}")
    public final String updateQuiz(@PathVariable final Integer quizId,
            @Valid @RequestBody final QuizDTO quizDTO) throws NotFoundException {
        String updatedQuiz = quizService.updateQuiz(quizId, quizDTO);
        return updatedQuiz;
    }

    /**
     * Deletes a quiz by its ID.
     * @param quizId The ID of the quiz to delete.
     * @return A message indicating the result of the quiz deletion.
     * @throws NotFoundException If the user's email domain is invalid.
     */
    @DeleteMapping("/{quizId}")
    public final ResponseEntity<Void> deleteQuiz(
            @PathVariable final Integer quizId) throws NotFoundException {
        quizService.deleteQuiz(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
        QuizDTO quizDTO = quizService.getQuizById(quizId);
        return new ResponseEntity<>(quizDTO, HttpStatus.OK);
    }

    /**
     * Retrieves a list of all quizzes.
     * @return A list of quizDto objects representing all quizzes.
     */
    @GetMapping
    public final ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        List<QuizDTO> quizzes = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param quizId The ID of the category to retrieve.
     * @return The list of questions entity.
     */
    @GetMapping("questions/{quizId}")
    public final List<QuestionDto> getAllQuestionByQuiz(
            @PathVariable final int quizId) {
        return quizService.getAllQuestionByQuiz(quizId);
    }
}
