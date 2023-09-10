package com.nucleusteq.assessmentPlatform.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nucleusteq.assessmentPlatform.dto.QuizDTO;
import com.nucleusteq.assessmentPlatform.entity.Category;
import com.nucleusteq.assessmentPlatform.entity.Quiz;

/**
 * Repository interface for managing quiz entities.
 */
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    /**
     * Finds a Quiz entity by Quiz Name.
     *
     * @param quizName The quiz Name to search for.
     * @return An Optional containing the found Registration entity, if any.
     */
    Optional<Quiz> findByQuizName(String quizName);

//    List<Quiz> findByCategoryId(int categoryId);
    
    List<Quiz> findByCategory(Category category);
}
