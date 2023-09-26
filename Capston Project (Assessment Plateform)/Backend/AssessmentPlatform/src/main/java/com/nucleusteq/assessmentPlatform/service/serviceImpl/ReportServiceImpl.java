package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import com.nucleusteq.assessmentPlatform.dto.ReportDto;
import com.nucleusteq.assessmentPlatform.entity.Report;
import com.nucleusteq.assessmentPlatform.repository.ReportRepository;
import com.nucleusteq.assessmentPlatform.service.ReportService;

import org.modelmapper.ModelMapper;
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
     * Adds a new report.
     * @param reportDto To find the questions.
     * @return A message indicating the result of the operation.
     */
    @Override
    public final String createReport(final ReportDto reportDto) {
        Report report = modelMapper.map(reportDto, Report.class);
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
        return reports.stream()
                .map(report -> modelMapper.map(report, ReportDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all reports By email of all users.
     * @return A list of reportDto objects representing report.
     */
    @Override
    public final List<ReportDto> getAllReport() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream()
                .map(report -> modelMapper.map(report, ReportDto.class))
                .collect(Collectors.toList());
    }
}
