package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.nucleusteq.assessmentPlatform.dto.ReportDto;
import com.nucleusteq.assessmentPlatform.entity.Report;
import com.nucleusteq.assessmentPlatform.repository.ReportRepository;

class ReportServiceImplTest {

    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReport() {
        ReportDto reportDto = new ReportDto(1, "Krishna kumar", "krishna@nucleustew.com", "Math", "Math Quiz", 20, 15, 2, 10, 8, "2023-09-23 10:00:00");
 
        Report report = new Report(); 
        when(modelMapper.map(reportDto, Report.class)).thenReturn(report);

        String result = reportService.createReport(reportDto);

        verify(reportRepository, times(1)).save(report);
        assertEquals("Report created successfully.", result);
        assertEquals("Krishna kumar", reportDto.getUserName());
    }

    @Test
    public void testFindReportByEmailId() {
        String email = "krishna@nucleusteq.com";
        List<Report> reports = new ArrayList<>(); 
        List<ReportDto> reportDtos = reports.stream()
                .map(report -> modelMapper.map(report, ReportDto.class))
                .collect(Collectors.toList());

        when(reportRepository.findByUserEmailId(email)).thenReturn(reports);
        when(modelMapper.map(any(Report.class), eq(ReportDto.class))).thenReturn(new ReportDto());

        List<ReportDto> result = reportService.findReportByEmailId(email);

        verify(reportRepository, times(1)).findByUserEmailId(email);
        assertEquals(reportDtos, result);
    }

    @Test
    public void testGetAllReport() {
        List<Report> reports = new ArrayList<>();
        List<ReportDto> reportDtos = reports.stream()
                .map(report -> modelMapper.map(report, ReportDto.class))
                .collect(Collectors.toList());

        when(reportRepository.findAll()).thenReturn(reports);
        when(modelMapper.map(any(Report.class), eq(ReportDto.class))).thenReturn(new ReportDto());

        List<ReportDto> result = reportService.getAllReport();

        verify(reportRepository, times(1)).findAll();
        assertEquals(reportDtos, result);
    }

}
