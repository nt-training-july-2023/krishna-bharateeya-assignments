import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './AdminHome.css';
import Sidebar from './Sidebar';
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';

const AdminHome = () => {
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadUsers();
  }, []);

  const handleLogout = () => {
    console.log("logout function called");
    localStorage.removeItem('IsLoggedIn');
    localStorage.removeItem('userRole');
    navigate("/");
  };

  const loadUsers = async () => {
    try {
      const result = await axios.get('http://localhost:8080/users/get/all');
      setUsers(result.data);
    } catch (error) {
      if (error.response) {
        console.error('Server Error:', error.response.data);
      }
    }
  };

  
  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'admin') {
    return (     
        <UnauthorizedAccess/>
    );
  }

  return (
    <div className="admin-home-wrapper">
      <div className='admin-main-container'>
        <div className='siderbar-column'>
          <Sidebar/>
        </div>
        <div className='admin-column'>
          <div className="admin-home-container">
            <div className="admin-home-card">
              <div className="admin-home-card-header">
                <h3>Welcome To Admin Dashboard</h3>
                <center>
                  <button className="admin-logout-button" onClick={handleLogout}>Logout</button>
                </center>
              </div>
              <div className="admin-home-card-body">
                <div className="admin-home-table-wrapper">
                  <table className="admin-home-table">
                    <thead className="admin-home-table-secondary">
                      <tr>
                        <th scope="col">S.No.</th>
                        <th scope="col">Full Name</th>
                        <th scope="col">Mobile Number</th>
                        <th scope="col">User Role</th>
                        <th scope="col">Email</th>
                      </tr>
                    </thead>
                    <tbody>
                      {users.map((user, index) => (
                        <tr key={index}>
                          <td>{index + 1}</td>
                          <td>{user.firstName + ' ' + user.lastName}</td>
                          <td>{user.mobileNumber}</td>
                          <td>{user.userRole}</td>
                          <td>{user.email}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminHome;
