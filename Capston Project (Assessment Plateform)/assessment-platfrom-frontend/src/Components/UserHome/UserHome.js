import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation, Link, useParams } from 'react-router-dom';
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';
import { GetUserByEmail} from '../../ApiService/ApiService';
import './UserHome.css';

const UserHome = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState('');
  const email=localStorage.getItem('email');
  const handleLogout = () => {
    localStorage.removeItem('IsLoggedIn');
    localStorage.removeItem('userRole');
    localStorage.removeItem('email');

    navigate('/');
  };

  useEffect(() => {
    getUserDetails();
  }, []);

  const getUserDetails = async () => {
    const result = await GetUserByEmail(email);
    setUser(result);
  };

  const userRole = localStorage.getItem('userRole');

  if (userRole !== 'user') {
    return <UnauthorizedAccess />;
  }

  return (
    <div className="user-home-container">
      <div className="user-home-card">
        <div className="card-header custom-header">
          <h1 className="text-center">Welcome {user.firstName}</h1>
        </div>
        <div className="card-body custom-body">
          <center><h2>Candidate Information</h2></center>
          
          <p><strong>First Name :</strong> {user.firstName}</p>
          <p><strong>Last Name :</strong> {user.lastName}</p>
          <p><strong>Mobile :</strong> {user.mobileNumber}</p>
          <p><strong>Email :</strong> {user.email}</p>

          <h2 className="mt-4">Information & Instructions:</h2>
          <ul>
            <li>The examination will comprise of Objective type Multiple Choice Questions (MCQs).</li>
            <li>The duration of examination, will be different based on the course.</li>
            <li>The students just need to click on the Correct option from the options given with each question.</li>
            <li>There will be NO NEGATIVE MARKING for the wrong answers.</li>
            <li>The Time remaining is shown in the Right Top Corner of the screen.</li>
            <li>Submit your answers before the deadline.</li>
          </ul>
        </div>
        <div className="card-footer text-center">
          <Link className="continue-button" to={'/categoryHome'}>Continue</Link>
        </div>
      </div>
    </div>

  );
};

export default UserHome;
