import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './Components/Login_Signup/Login';
import Registration from './Components/Login_Signup/Registration';
import AdminHome from './Components/AdminHome/AdminHome';
import Category from './Components/Category/CategoryHome';
import AddCategory from './Components/Category/AddCategory';
import UserHome from './Components/UserHome/UserHome';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import UpdateCategory from './Components/Category/UpdateCategory';


function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userRole, setUserRole] = useState('');
  

  useEffect(() => {
    const status = localStorage.getItem('IsLoggedIn') === 'true';
    const role = localStorage.getItem('userRole') || '';
    setIsLoggedIn(status);
    setUserRole(role);
  }, []);


  return (
    <div>

      <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={true}
        closeOnClick={true}
        pauseOnHover={true}
        draggable={true}
        limit={5}
        role="status"
        theme="colored"
      />

      <Router>
        <Routes>
          <Route path="/login" element={isLoggedIn ? <HomeRedirect userRole={userRole} /> : <Login />} />
          <Route path="/registration" element={<Registration />} />
          {isLoggedIn && userRole === 'admin' && (
            <>
              <Route path="/adminHome" element={<AdminHome />} />
              <Route path="/categoryHome" element={<Category />} />
              <Route path="/categoryHome/addCategory" element={<AddCategory />} />
              <Route path="/categoryHome/updateCategory/:categoryId" element={<UpdateCategory />} />
            </>
          )}
          {isLoggedIn && userRole === 'user' && <Route path="/userHome" element={<UserHome />} />}
          <Route path="/*" element={isLoggedIn ? <HomeRedirect userRole={userRole} /> : <Navigate to="/login" />} />
        </Routes>
      </Router>
    </div>
  );
}

function HomeRedirect({ userRole }) {
  if (userRole === 'admin') {
    return <Navigate to="/adminHome" />;
  } else if (userRole === 'user') {
    return <Navigate to="/userHome" />;
  } else {
    return <Navigate to="/login" />;
  }
}

export default App;
