import React from 'react';
import { useNavigate } from 'react-router-dom';

const UserHome = () => {
    const navigate = useNavigate();
    const handleLogout = () => {
        localStorage.removeItem('IsLoggedIn');
        localStorage.removeItem('userRole');
        navigate('/');
      };

  return (
    <div>
      <h1>User Home</h1>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default UserHome;
