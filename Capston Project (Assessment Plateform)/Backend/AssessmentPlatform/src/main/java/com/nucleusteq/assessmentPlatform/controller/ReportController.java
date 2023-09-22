package com.nucleusteq.assessmentPlatform.controller;
import com.nucleusteq.assessmentPlatform.dto.ReportDto;
import com.nucleusteq.assessmentPlatform.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<String> createReport(@RequestBody ReportDto reportDto) {
        String result = reportService.createReport(reportDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<ReportDto>> findByEmailId(@PathVariable String email) {
        List<ReportDto> reportDto = reportService.findReportByEmailId(email);
        if (reportDto != null) {
            return ResponseEntity.ok(reportDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportDto>> getAllReports() {
        List<ReportDto> reportDtos = reportService.getAllReport();
        return ResponseEntity.ok(reportDtos);
    }
}
