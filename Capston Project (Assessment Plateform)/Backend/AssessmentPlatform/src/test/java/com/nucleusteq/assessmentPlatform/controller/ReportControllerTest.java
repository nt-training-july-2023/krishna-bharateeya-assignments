package com.nucleusteq.assessmentPlatform.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucleusteq.assessmentPlatform.dto.ReportDto;
import com.nucleusteq.assessmentPlatform.service.ReportService;

class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private ReportService reportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        new ObjectMapper();
    }

    @Test
    public void testCreateReport() throws Exception {
        ReportDto reportDto = new ReportDto();
        when(reportService.createReport(reportDto)).thenReturn("Report created successfully");

        ResponseEntity<String> response = reportController.createReport(reportDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Report created successfully", response.getBody());
    }

    @Test
    public void testFindByEmailId() {
        String email = "test@example.com";
        List<ReportDto> reportDtos = new ArrayList<>();
        when(reportService.findReportByEmailId(email)).thenReturn(reportDtos);

        ResponseEntity<List<ReportDto>> response = reportController.findByEmailId(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reportDtos, response.getBody());
    }




    @Test
    public void testGetAllReports() {
        List<ReportDto> reportDtos = new ArrayList<>();
        when(reportService.getAllReport()).thenReturn(reportDtos);

        ResponseEntity<List<ReportDto>> response = reportController.getAllReports();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reportDtos, response.getBody());
    }

}
