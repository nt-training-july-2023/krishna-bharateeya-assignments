package com.nucleusteq.assessmentPlatform.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nucleusteq.assessmentPlatform.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    /**
     * Finds a Registration entity by email.
     *
     * @param email The email to search for.
     * @return An Optional containing the found Registration entity, if any.
     */
    List<Report> findByUserEmailId(String email);
}
