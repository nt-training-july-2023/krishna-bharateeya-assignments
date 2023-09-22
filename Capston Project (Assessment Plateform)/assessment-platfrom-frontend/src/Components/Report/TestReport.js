import React, { useState, useEffect } from "react";
import './TestReport.css'
import Sidebar from '../AdminHome/Sidebar';
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';
import { GetAllReport, GetReportByEmail } from '../../ApiService/ApiService';

const Report = () => {

  const userRole = localStorage.getItem('userRole');
  const email = localStorage.getItem('email');
  const [reports, setReports] = useState([]);
  useEffect(() => {
    loadReports();
  }, [email]);

  const loadReports = async () => {
    try {
      let data = [];
      if (userRole === 'user') {
        data = await GetReportByEmail(email);
      } else {
        data = await GetAllReport();
      }
      console.log("fdsfdsfsg", data);
      setReports(data);

    } catch (error) {
      console.error('Error fetching questions:', error);

    }
  };

  return (
    <div className='report-wrapper'>
      <div className='report-container'>

        <div className='report-sidebar-column'>
          <Sidebar />
        </div>
        <div className='report-column'>

          <div className='report-cards'>
            {reports.map((report, index) => (
              <div key={index} className='report-card'>
                <h3>Report {index + 1}</h3>
                <p>User Name: {report.userName}</p>
                <p>User Email: {report.userEmailId}</p>
                <p>Category Name: {report.categoryName}</p>
                <p>Quiz Name: {report.quizName}</p>
                <p>Total Marks: {report.totalMarks}</p>
                <p>Marks Obtained: {report.marksObtained}</p>
                <p>Wrong Answers: {report.wrongAnswers}</p>
                <p>Total Questions: {report.totalQuestions}</p>
                <p>Attempted Questions: {report.attemptedQuestions}</p>
                <p>Date and Time: {report.dateAndTime}</p>
              </div>
            ))}
          </div>
        </div>

      </div>
    </div>


  )
}

export default Report
