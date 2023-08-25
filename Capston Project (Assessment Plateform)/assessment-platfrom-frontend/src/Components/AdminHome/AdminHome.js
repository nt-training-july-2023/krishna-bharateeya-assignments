import React from 'react';
import { useNavigate } from 'react-router-dom';
const AdminHome = () => {

  const navigate = useNavigate();
  const handleLogout = () => {

    localStorage.removeItem('IsLoggedIn');
    localStorage.removeItem('userRole');

    // navigate('/')

    window.location.replace('/');
  };
const CategoryHome= ()=>{
  navigate('/categoryHome')
}

  return (
    <div className="admin-dashboard">
      <h1>Welcome to the Admin Dashboard</h1>
      <button onClick={CategoryHome}> Category</button>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default AdminHome;
