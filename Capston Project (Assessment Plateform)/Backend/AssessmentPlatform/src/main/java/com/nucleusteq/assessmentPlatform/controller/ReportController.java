package com.nucleusteq.assessmentPlatform.controller;

import com.nucleusteq.assessmentPlatform.dto.ReportDto;
import com.nucleusteq.assessmentPlatform.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controller class for managing Reports.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    /**
     * This is report service object it call the report methods.
     */
    private final ReportService reportService;

    /**
     * This is report controller object it call the report methods.
     * @param repoService user to assign in the constructor.
     */
    @Autowired
    public ReportController(final ReportService repoService) {
        this.reportService = repoService;
    }

    /**
     * Adds a new quiz.
     * @param reportDto The reportDto object containing report information.
     * @return A message indicating the result of the report addition.
     */
    @PostMapping
    public final ResponseEntity<String> createReport(
            @RequestBody final ReportDto reportDto) {
        String result = reportService.createReport(reportDto);
        return ResponseEntity.ok(result);
    }

    /**
     * Retrieves a list of all reports.
     * @param email The email to belongs report information.
     * @return A list of reportDto objects representing all reports.
     */
    @GetMapping("/{email}")
    public final ResponseEntity<List<ReportDto>> findByEmailId(
            @PathVariable final String email) {
        List<ReportDto> reportDto = reportService.findReportByEmailId(email);
        if (reportDto != null) {
            return ResponseEntity.ok(reportDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a list of all reports.
     * @return A list of reportDto objects representing all reports.
     */
    @GetMapping("/all")
    public final ResponseEntity<List<ReportDto>> getAllReports() {
        List<ReportDto> reportDtos = reportService.getAllReport();
        return ResponseEntity.ok(reportDtos);
    }
}
