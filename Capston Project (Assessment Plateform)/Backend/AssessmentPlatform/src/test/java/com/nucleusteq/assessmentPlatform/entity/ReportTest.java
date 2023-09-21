//package com.nucleusteq.assessmentPlatform.entity;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class ReportTest {
//
//   Report report;
//   
//   @BeforeEach
//   void init() {
//       report=new Report();
//   }
//   
//   @Test
//   void testGettersAndSetters() {
//       assertEquals(0,report.getReportId());
//       assertEquals(0, report.getWrongAnswers());
//       assertEquals(0, report.getTotalMarks());
//       assertEquals(0, report.getObtainedMark());
//       
//   }
//   
//   @Test
//   void testAllGettersAndSetters() {
//       Report allParaReport =new Report();
//       allParaReport.setReportId(11);
//       allParaReport.setTotalMark(100);
//       allParaReport.setObtainedMark(45);
//       allParaReport.setWrongAnswers(7);
//       
//       assertEquals(11,allParaReport.getReportId());
//       assertEquals(7, allParaReport.getWrongAnswers());
//       assertEquals(100, allParaReport.getTotalMarks());
//       assertEquals(45, allParaReport.getObtainedMark());
//       
//   }
//   
//   @Test
//   void allArgConstructor() {
//       Report allParaReport =new Report(
//               103,
//               100,
//               67,
//               7
//               );
//       assertEquals(103,allParaReport.getReportId());
//       assertEquals(100, allParaReport.getTotalMarks());
//       assertEquals(67, allParaReport.getObtainedMark());
//       assertEquals(7, allParaReport.getWrongAnswers());
//   }
//
//}
