package com.nucleusteq.assessmentPlatform.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Quiz;

/**
 * Repository interface for managing quiz entities.
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    /**
     * Finds a Quiz entity by Quiz Name.
     * @param quizName The quiz Name to search for.
     * @return An Optional containing the found Registration entity, if any.
     */
    Optional<Quiz> findByQuizName(String quizName);

    /**
     * Finds a Quiz entity by Quiz Name.
     * @param category The category Object to search for.
     * @return A List containing the found Quiz entity, if any.
     */
    List<Quiz> findByCategory(Category category);
}
