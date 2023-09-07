package com.nucleusteq.assessmentPlatform.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nucleusteq.assessmentPlatform.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    /**
     * Finds a Quiz entity by Quiz Name.
     *
     * @param mobileNumber The mobile number to search for.
     * @return An Optional containing the found Registration entity, if any.
     */
    Optional<Quiz> findByQuizName(String quizName);
}
