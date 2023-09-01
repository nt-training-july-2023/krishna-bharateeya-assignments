import React from 'react';
import { useNavigate } from 'react-router-dom';
import './AdminHome.css';
const AdminHome = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('IsLoggedIn');
    localStorage.removeItem('userRole');
    navigate('/');
  };

  const handleCategoryClick = () => {
    navigate('/categoryHome');
  };
  const handleShowAllUsers = () => {
    // Handle show all users logic
  };

  const handleManageQuiz = () => {
    // Handle manage quiz logic
  };

  const handleManageQuestions = () => {
    // Handle manage questions logic
  };

  const handleGenerateReport = () => {
    // Handle generate report logic
  };

  return (
    <div className="admin-dashboard">
      <div className='admin-homeContainer'>
      <h1>Welcome to the Admin Dashboard</h1>
      <button className="admin-logout-button" onClick={handleLogout}>Logout</button>
      <div className="admin-buttons">
        <div className="admin-column">
        <button className="admin-button" onClick={handleCategoryClick}>Manage Category</button>
          <button className="admin-button" onClick={handleManageQuiz}>Manage Quiz</button>
        </div>
        <div className="admin-column">
          
          <button className="admin-button" onClick={handleManageQuestions}>Manage Questions</button>
          <button className="admin-button" onClick={handleGenerateReport}>Generate Report</button>
        </div>
        
      </div>
      </div>
    </div>
  );
};

export default AdminHome;
