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

    @Test
    public void testAllArgsConstructor() {
        
        int reportId = 123;
        String userName = "Krishna kumar";
        String userEmailId = "krishna@nucleusteq.com";
        String categoryName = "Category";
        String quizName = "Quiz";
        Integer totalMarks = 100;
        int marksObtained = 85;
        int totalQuestions = 20;
        int attemptedQuestions = 18;
        String dateAndTime = "10-07-2023 14:30:00";
        int wrongAnswers = 3;

        Report report = new Report(reportId, userName, userEmailId, categoryName, quizName, totalMarks, marksObtained, totalQuestions, attemptedQuestions, dateAndTime, wrongAnswers);

        assertEquals(reportId, report.getReportId());
        assertEquals(userName, report.getUserName());
        assertEquals(userEmailId, report.getUserEmailId());
        assertEquals(categoryName, report.getCategoryName());
        assertEquals(quizName, report.getQuizName());
        assertEquals(totalMarks, report.getTotalMarks());
        assertEquals(marksObtained, report.getMarksObtained());
        assertEquals(totalQuestions, report.getTotalQuestions());
        assertEquals(attemptedQuestions, report.getAttemptedQuestions());
        assertEquals(dateAndTime, report.getDateAndTime());
        assertEquals(wrongAnswers, report.getWrongAnswers());
    }
}
