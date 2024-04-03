package com.nucleusteq.assessmentPlatform.controller;

import com.nucleusteq.assessmentPlatform.dto.ReportDto;
import com.nucleusteq.assessmentPlatform.service.ReportService;
import com.nucleusteq.assessmentPlatform.utility.ReportLoggerMessage;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private ReportService reportService;

    /**
     * this is logger object that is use to generate log.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ReportController.class);

    /**
     * Adds a new quiz.
     * @param reportDto The reportDto object containing report information.
     * @return A message indicating the result of the report addition.
     */
    @PostMapping
    public final ResponseEntity<SuccessResponse> createReport(
            @Valid @RequestBody final ReportDto reportDto) {
        LOGGER.info(ReportLoggerMessage.CREATE_REPORT_REQUEST);
        SuccessResponse result = reportService.createReport(reportDto);
        LOGGER.info(ReportLoggerMessage.REPORT_GENERATED_SUCCESSFULLY);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all reports.
     * @param email The email to belongs report information.
     * @return A list of reportDto objects representing all reports.
     */
    @GetMapping("/{email}")
    public final ResponseEntity<List<ReportDto>> findByEmailId(
            @PathVariable final String email) {
        LOGGER.info(ReportLoggerMessage.FIND_REPORT_BY_EMAIL_REQUEST + email);
        List<ReportDto> reportDto = reportService.findReportByEmailId(email);
        LOGGER.info(ReportLoggerMessage.REPORTS_RETRIEVED_SUCCESSFULLY);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);

    }

    /**
     * Retrieves a list of all reports.
     * @return A list of reportDto objects representing all reports.
     */
    @GetMapping("/all")
    public final ResponseEntity<List<ReportDto>> getAllReports() {
        LOGGER.info(ReportLoggerMessage.GET_ALL_REPORTS_REQUEST);
        List<ReportDto> reportDtos = reportService.getAllReport();
        LOGGER.info(ReportLoggerMessage.ALL_REPORTS_RETRIEVED_SUCCESSFULLY);
        return new ResponseEntity<>(reportDtos, HttpStatus.OK);
    }
}
