import React, { useState, useEffect } from "react";
import './TestReport.css'
import { GetAllReport, GetReportByEmail } from '../../ApiService/ApiService';
import Sidebar from "../../Components/SideBar/Sidebar";
import NoDataMessage from "../../Components/NoDataMessage/NoDataMessage";

const Report = () => {

  const userRole = localStorage.getItem('userRole');
  const email = localStorage.getItem('email');
  const [reports, setReports] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    loadReports();
  }, [email, searchQuery]);

  const loadReports = async () => {
    try {
      let data = [];
      if (userRole === 'user') {
        data = await GetReportByEmail(email);
      } else {
        data = await GetAllReport();
      }

      data.sort((a, b) => {
        const dateA = new Date(a.dateAndTime).getTime();
        const dateB = new Date(b.dateAndTime).getTime();
        return dateB - dateA;
      });

      const filteredReports = data.filter((report) =>
        report.userName.toLowerCase().includes(searchQuery.toLowerCase()) ||
        report.userEmailId.toLowerCase().includes(searchQuery.toLowerCase()) ||
        report.quizName.toLowerCase().includes(searchQuery.toLowerCase()) ||
        report.dateAndTime.toLowerCase().includes(searchQuery.toLowerCase())
      );


      setReports(filteredReports);

    } catch (error) {
      console.error('Error fetching questions:', error);

    }
  };

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };



  return (

    <div className='report-wrapper'>
      <div className='report-container'>
        <div className='report-sidebar-column'>
          <Sidebar />
        </div>
        <div className='report-column'>
          <div className="report-card-header">

            <h3>Reports</h3>
            <input
              type="text"
              placeholder="Search reports..."
              value={searchQuery}
              onChange={handleSearchChange}
            />
          </div>
          <table className='report-table'>
            {reports.length === 0 ? (
              <NoDataMessage message="No Report found." />
            ) : (
              <>

                <thead >
                  <tr>
                    <th>Sr. No.</th>
                    <th>User Name</th>
                    <th>User Email</th>
                    <th>Quiz Name</th>
                    <th>Total Marks</th>
                    <th>Marks Obtained</th>
                    <th>Wrong Answers</th>
                    <th>Attempted Questions</th>
                    <th>Total Questions</th>
                    <th>Date </th>
                    <th>Time</th>
                  </tr>
                </thead>

                <tbody>
                  {reports.map((report, index) => (
                    <tr key={index} className='report-row'>
                      <td>{index + 1}</td>
                      <td>{report.userName}</td>
                      <td>{report.userEmailId}</td>
                      <td>{report.quizName}</td>
                      <td>{report.totalMarks}</td>
                      <td>{report.marksObtained}</td>
                      <td>{report.wrongAnswers}</td>
                      <td>{report.attemptedQuestions}</td>
                      <td>{report.totalQuestions}</td>
                      <td>{report.dateAndTime.split(",")[0]}</td>
                      <td>{report.dateAndTime.split(",")[1]}</td>
                    </tr>
                  ))}
                </tbody>
              </>
            )}
          </table>
        </div>
      </div>
    </div>


  )
}

export default Report
