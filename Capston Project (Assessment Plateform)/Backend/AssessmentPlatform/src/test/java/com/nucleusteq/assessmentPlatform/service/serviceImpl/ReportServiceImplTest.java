package com.nucleusteq.assessmentPlatform.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import com.nucleusteq.assessmentPlatform.dto.ReportDto;
import com.nucleusteq.assessmentPlatform.entity.Report;
import com.nucleusteq.assessmentPlatform.repository.ReportRepository;
import com.nucleusteq.assessmentPlatform.utility.SuccessResponse;

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

        SuccessResponse response = reportService.createReport(reportDto);

        SuccessResponse expectedResponse = new SuccessResponse(
                HttpStatus.CREATED.value(),
                "Report created successfully."
            );
        
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testFindReportByEmailId() {
        String email = "piyush@nucleusteq.com";
        
        Report report1=new Report();
        report1.setReportId(1);
        report1.setUserEmailId("piyush@nucleusteq.com");

        Report report2=new Report();
        report2.setReportId(2);
        report2.setUserEmailId("piyush@nucleusteq.com");
          

        List<Report> reports = Arrays.asList(report1,report2); 

        when(reportRepository.findByUserEmailId(email)).thenReturn(reports);

        List<ReportDto> result = reportService.findReportByEmailId(email);

        verify(reportRepository, times(1)).findByUserEmailId(email);
        assertEquals(2, result.size());
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
