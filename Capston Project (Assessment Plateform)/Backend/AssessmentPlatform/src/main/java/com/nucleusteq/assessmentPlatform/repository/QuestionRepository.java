package com.nucleusteq.assessmentPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nucleusteq.assessmentPlatform.entity.Question;
/**
 * Repository interface for managing Question entities.
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
