import React from 'react';

const UserHome = () => {
  const handleLogout = () => {
    
    localStorage.removeItem('IsLoggedIn');
    localStorage.removeItem('userRole');

    
    window.location.replace('/');
  };

  return (
    <div>
      <h1>User Home</h1>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default UserHome;
