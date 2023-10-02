package com.nucleusteq.assessmentPlatform.service;

import java.util.List;

import com.nucleusteq.assessmentPlatform.dto.ReportDto;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

/**
 * Service interface for managing Report.
 */
public interface ReportService {

    /**
     * Adds a new quiz.
     * @param reportDto The DTO containing report information.
     * @return A message indicating the result of the operation.
     */
    SuccessResponse createReport(ReportDto reportDto);

    /**
     * Updates a category.
     * @param email to get report by email .
     * @return The List Of Reports  Entity.
     */
    List<ReportDto> findReportByEmailId(String email);

    /**
     * Get All Reports.
     * @return The List Of Questions  Entity.
     */
    List<ReportDto> getAllReport();
}
