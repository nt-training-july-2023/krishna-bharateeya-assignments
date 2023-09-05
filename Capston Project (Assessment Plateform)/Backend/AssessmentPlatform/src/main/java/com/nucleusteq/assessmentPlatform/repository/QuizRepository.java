package com.nucleusteq.assessmentPlatform.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nucleusteq.assessmentPlatform.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    
}
