import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './AdminHome.css';
import UnauthorizedAccess from '../../Components/UnauthrizedAccess/UnauthorizedAccess';
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';
import Swal from 'sweetalert2';
import { LoadUsers } from '../../ApiService/ApiService';
import Sidebar from '../../Components/SideBar/Sidebar';

const AdminHome = () => {
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadUsers();
  }, []);

  const handleLogout = () => {
    toast.success("Logout successfully.");
    localStorage.removeItem('IsLoggedIn');
    localStorage.removeItem('userRole');
    localStorage.removeItem('email');
    navigate("/");
  };

  const handleLogoutConfirmation = () => {
    Swal.fire({
      text: "Confirm Logout?",
      icon: "warning",
      showCancelButton: true,
      showConfirmButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        handleLogout();
      }
    });
  }
  const loadUsers = async () => {
    try {
      const result = await LoadUsers();
      setUsers(result);
    } catch (error) {
      if (error.response) {
        Swal.fire('Error', error.response.data.message, 'error');
      }
    }
  };


  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'admin') {
    return (
      <UnauthorizedAccess />
    );
  }

  return (
    <div className="admin-home-wrapper">
      <div className='admin-main-container'>
        <div className='siderbar-column'>
          <Sidebar />
        </div>
        <div className='admin-column'>
          <div className="admin-home-container">
            <div className="admin-home-card">
              <div className="admin-home-card-header">
                <h3>Welcome To Admin Dashboard</h3>
                <center>
                  <button className="admin-logout-button" onClick={handleLogoutConfirmation}>Logout</button>
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
