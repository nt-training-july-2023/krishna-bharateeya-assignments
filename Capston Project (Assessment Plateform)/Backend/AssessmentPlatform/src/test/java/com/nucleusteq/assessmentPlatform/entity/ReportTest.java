package com.nucleusteq.assessmentPlatform.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportTest {
    
    private Report report;

    @BeforeEach
    public void setUp() {
        report = new Report();
    }

    @Test
    public void testReportProperties() {
        report.setReportId(1);
        report.setUserName("Krishna kumar");
        report.setUserEmailId("krishna@nucleusteq.com");
        report.setCategoryName("Math");
        report.setQuizName("Math Quiz");
        report.setTotalMarks(20);
        report.setMarksObtained(15);
        report.setTotalQuestions(10);
        report.setAttemptedQuestions(8);
        report.setDateAndTime("2023-09-23 10:00:00");
        report.setWrongAnswers(2);


        assertEquals(1, report.getReportId());
        assertEquals("Krishna kumar", report.getUserName());
        assertEquals("krishna@nucleusteq.com", report.getUserEmailId());
        assertEquals("Math", report.getCategoryName());
        assertEquals("Math Quiz", report.getQuizName());
        assertEquals(20, report.getTotalMarks());
        assertEquals(15, report.getMarksObtained());
        assertEquals(10, report.getTotalQuestions());
        assertEquals(8, report.getAttemptedQuestions());
        assertEquals("2023-09-23 10:00:00", report.getDateAndTime());
        assertEquals(2, report.getWrongAnswers());
    }


}
