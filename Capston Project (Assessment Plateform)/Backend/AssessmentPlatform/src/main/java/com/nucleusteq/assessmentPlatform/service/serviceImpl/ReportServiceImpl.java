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

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, ModelMapper modelMapper) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String createReport(ReportDto reportDto) {
        Report report = modelMapper.map(reportDto, Report.class);
        reportRepository.save(report);
        return "Report created successfully.";
    }

    @Override
    public List<ReportDto> findReportByEmailId(String email) {

        List<Report> reports = reportRepository.findByUserEmailId(email);
        return reports.stream()
                .map(report -> modelMapper.map(report, ReportDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportDto> getAllReport() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream()
                .map(report -> modelMapper.map(report, ReportDto.class))
                .collect(Collectors.toList());
    }
}
