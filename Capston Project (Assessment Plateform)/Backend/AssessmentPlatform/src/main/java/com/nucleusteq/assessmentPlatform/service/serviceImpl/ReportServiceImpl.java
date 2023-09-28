package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import com.nucleusteq.assessmentPlatform.controller.ReportController;
import com.nucleusteq.assessmentPlatform.dto.ReportDto;
import com.nucleusteq.assessmentPlatform.entity.Report;
import com.nucleusteq.assessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.assessmentPlatform.repository.ReportRepository;
import com.nucleusteq.assessmentPlatform.service.ReportService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the ReportService interface for managing Report.
 */
@Service
public class ReportServiceImpl implements ReportService {

    /**
     * This is Report Repository object that is for calling. the repository.
     * methods.
     */
    @Autowired
    private ReportRepository reportRepository;

    /**
     * This is use to map the category with Dto and viceversa.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * this is logger object that is use to generate log.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ReportController.class);

    /**
     * Adds a new report.
     * @param reportDto To find the questions.
     * @return A message indicating the result of the operation.
     */
    @Override
    public final String createReport(final ReportDto reportDto) {
        Report report = convertIntoEntity(reportDto);
        reportRepository.save(report);
        return "Report created successfully.";
    }

    /**
     * Retrieves a list of all reports By email.
     * @return A list of reportDto objects representing report.
     */
    @Override
    public final List<ReportDto> findReportByEmailId(final String email) {

        List<Report> reports = reportRepository.findByUserEmailId(email);
        if (reports.isEmpty()) {
            LOGGER.error("No reports found with email: {}", email);
            throw new ResourceNotFoundException(
                    "No reports found with email: " + email);
        }
        return reports.stream().map(this::convertIntoDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all reports By email of all users.
     * @return A list of reportDto objects representing report.
     */
    @Override
    public final List<ReportDto> getAllReport() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(this::convertIntoDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a {@link ReportDto} object into a {@link Report} entity using
     * ModelMapper.
     * @param reportDto The {@link ReportDto} to convert.
     * @return The converted {@link Report} entity.
     */
    public Report convertIntoEntity(final ReportDto reportDto) {
        return modelMapper.map(reportDto, Report.class);
    }

    /**
     * Converts a {@link Report} entity into a {@link ReportDto} using
     * ModelMapper.
     * @param report The {@link Report} entity to convert.
     * @return The converted {@link ReportDto}.
     */
    public ReportDto convertIntoDto(final Report report) {
        return modelMapper.map(report, ReportDto.class);
    }

}
