import React from 'react';
import { useNavigate } from 'react-router-dom';
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';

const UserHome = () => {
    const navigate = useNavigate();
    const handleLogout = () => {
        localStorage.removeItem('IsLoggedIn');
        localStorage.removeItem('userRole');
        navigate('/');
      };
      const userRole = localStorage.getItem('userRole');
      if (userRole !== 'user') {
        return (
          <UnauthorizedAccess/>
        );
      }
  return (
    <div>
      <h1>User Home</h1>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default UserHome;
