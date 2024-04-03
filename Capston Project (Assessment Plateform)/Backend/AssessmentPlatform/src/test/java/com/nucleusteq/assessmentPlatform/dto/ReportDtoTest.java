package com.nucleusteq.assessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportDtoTest {

    private ReportDto reportDto;

    @BeforeEach
    public void setUp() {
        reportDto = new ReportDto();
    }

    @Test
    public void testReportDtoProperties() {
        reportDto.setReportId(1);
        reportDto.setUserName("Krishna kumar");
        reportDto.setUserEmailId("Krishna@nucleusteq.com");
        reportDto.setCategoryName("Math");
        reportDto.setQuizName("Math Quiz");
        reportDto.setTotalMarks(20);
        reportDto.setMarksObtained(15);
        reportDto.setWrongAnswers(2);
        reportDto.setTotalQuestions(10);
        reportDto.setAttemptedQuestions(8);
        reportDto.setDateAndTime("2023-09-23 10:00:00");

        assertEquals(1, reportDto.getReportId());
        assertEquals("Krishna kumar", reportDto.getUserName());
        assertEquals("Krishna@nucleusteq.com", reportDto.getUserEmailId());
        assertEquals("Math", reportDto.getCategoryName());
        assertEquals("Math Quiz", reportDto.getQuizName());
        assertEquals(20, reportDto.getTotalMarks());
        assertEquals(15, reportDto.getMarksObtained());
        assertEquals(2, reportDto.getWrongAnswers());
        assertEquals(10, reportDto.getTotalQuestions());
        assertEquals(8, reportDto.getAttemptedQuestions());
        assertEquals("2023-09-23 10:00:00", reportDto.getDateAndTime());
    }

}
