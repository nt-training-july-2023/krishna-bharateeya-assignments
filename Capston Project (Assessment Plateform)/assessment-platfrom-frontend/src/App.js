import './App.css';
import Login from './Components/Login_Signup/Login';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Registration from './Components/Login_Signup/Registration';
import UserHome from './Components/UserHome/UserHome';
import AdminHome from './Components/AdminHome/AdminHome'
import { Navigate } from 'react-router-dom';
import React from 'react';
import Category from './Components/Category/CategoryHome';
import AddCategory from './Components/Category/AddCategory';

function App() {
  const isLoggedIn = localStorage.getItem('IsLoggedIn') === 'true';
  const userRole = localStorage.getItem('userRole');

  return (
    <Router>
      <Routes>

        <Route
          path="/"
          element={
            isLoggedIn && userRole ? (
              userRole === 'admin' ? (
                <Navigate to="/adminHome" />
              ) : (
                <Navigate to="/userHome" />
              )
            ) : (
              <Login />
            )
          }
        />
        <Route path="/registration" element={<Registration />} />
        {isLoggedIn && userRole === 'admin' && <Route path="/adminHome" element={<AdminHome />} />}
        {isLoggedIn && userRole === 'admin' && <Route path="/categoryHome" element={<Category />} />}
        {isLoggedIn && userRole === 'admin' && <Route path="/categoryHome/addCategory" element={<AddCategory />} />}
        {isLoggedIn && userRole === 'user' && <Route path="/userHome" element={<UserHome />} />}


      </Routes>
    </Router>
  );

}

export default App;
