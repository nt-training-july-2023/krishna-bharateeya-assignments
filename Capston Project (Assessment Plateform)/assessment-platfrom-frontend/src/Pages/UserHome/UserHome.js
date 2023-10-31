import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation, Link, useParams } from 'react-router-dom';
import UnauthorizedAccess from '../../Components/UnauthrizedAccess/UnauthorizedAccess';
import { GetUserByEmail } from '../../ApiService/ApiService';
import './UserHome.css';
import 'sweetalert2/dist/sweetalert2.min.css';
import Sidebar from '../../Components/SideBar/Sidebar';
const UserHome = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState('');
  const email = localStorage.getItem('email');

  useEffect(() => {
    clearLocaleStorage();
    getUserDetails();
  }, []);

  const getUserDetails = async () => {
    const result = await GetUserByEmail(email);
    setUser(result);
  };
  const clearLocaleStorage = () => {
    localStorage.removeItem("timerInSeconds");
    localStorage.removeItem("selectedAnswers");
    localStorage.removeItem("userName");
    localStorage.removeItem("categoryName");
    localStorage.removeItem("quizName");
    localStorage.removeItem("reloadAttempts");
    localStorage.removeItem("totalQuestion");
    localStorage.removeItem("totalMarks");
    localStorage.removeItem("userEmail");
    localStorage.removeItem("correctAnswersCount");
    localStorage.removeItem("wrongAnswers");
    localStorage.removeItem("attemptedQuestions");
    localStorage.removeItem("tabVisibility");
  };
  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'user') {
    return <UnauthorizedAccess />;
  }

  return (
    <div className="user-home-container">
      <div className='user-sidebar-column'>
        <Sidebar />
      </div>
      <div className="user-home-card">
        <div className="user-card-header">
          <h1 className="text-center">Welcome {user.firstName}</h1>
        </div>
        <div className="card-body">
          <center><h2>Candidate Information</h2></center>

          <p><strong>First Name :</strong> {user.firstName}</p>
          <p><strong>Last Name :</strong> {user.lastName}</p>
          <p><strong>Mobile No :</strong> {user.mobileNumber}</p>
          <p><strong>Email Id :</strong> {user.email}</p>
          <div class="instructions">
            <h2>Information & Instructions:</h2>
            <ul>
              <li>The examination will comprise of Objective type Multiple Choice Questions (MCQs).</li>
              <li>The duration of examination, will be different based on the course.</li>
              <li>The students just need to click on the Correct option from the options given with each question.</li>
              <li>There will be <strong>NO NEGATIVE MARKING</strong> for the wrong answers.</li>
              <li>The Time remaining is shown in the Right Top Corner of the screen.</li>
              <li>Once you start the test, you cannot leave without clicking the submit button.</li>
              <li>Ensure a stable internet connection for a smooth experience.</li>
            </ul>
            <div className="card-footer text-center">
              <Link className="continue-button" to={'/categoryHome'}>Continue</Link>
            </div>
          </div>
        </div>
      </div>
    </div>

  );
};

export default UserHome;
