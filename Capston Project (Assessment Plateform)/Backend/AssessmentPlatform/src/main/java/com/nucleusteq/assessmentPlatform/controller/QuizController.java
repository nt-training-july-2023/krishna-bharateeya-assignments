package com.nucleusteq.assessmentPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.service.QuizService;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping
    public String addQuiz(@RequestBody QuizDTO quizDTO) {
        String createdQuiz = quizService.addQuiz(quizDTO);
        return createdQuiz;
    }

    @PutMapping("/{quizId}")
    public String updateQuiz(
        @PathVariable Integer quizId,
        @RequestBody QuizDTO quizDTO
    ) throws NotFoundException {
        String updatedQuiz = quizService.updateQuiz(quizId, quizDTO);
        return updatedQuiz;
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Integer quizId) throws NotFoundException {
        quizService.deleteQuiz(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable Integer quizId) throws NotFoundException {
        QuizDTO quizDTO = quizService.getQuizById(quizId);
        return new ResponseEntity<>(quizDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        List<QuizDTO> quizzes = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }
}
