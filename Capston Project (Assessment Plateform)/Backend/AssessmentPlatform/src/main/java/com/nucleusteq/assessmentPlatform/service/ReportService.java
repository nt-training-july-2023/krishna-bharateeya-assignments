package com.nucleusteq.assessmentPlatform.service;

import java.util.List;

import com.nucleusteq.assessmentPlatform.dto.ReportDto;

public interface ReportService {

    String createReport(ReportDto reportDto);
    
    List<ReportDto> findReportByEmailId(String email);

    List<ReportDto> getAllReport();
}
