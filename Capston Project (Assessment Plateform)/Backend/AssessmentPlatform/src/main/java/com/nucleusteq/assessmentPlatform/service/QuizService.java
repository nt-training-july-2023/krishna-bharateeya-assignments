package com.nucleusteq.assessmentPlatform.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.nucleusteq.assessmentPlatform.dto.QuizDTO;

public interface QuizService {
    QuizDTO addQuiz(QuizDTO quizDTO);
    void deleteQuiz(Integer quizId) throws NotFoundException;
    QuizDTO getQuizById(Integer quizId) throws NotFoundException;
    List<QuizDTO> getAllQuizzes();
    QuizDTO updateQuiz(Integer quizId, QuizDTO quizDTO)throws NotFoundException;
}
