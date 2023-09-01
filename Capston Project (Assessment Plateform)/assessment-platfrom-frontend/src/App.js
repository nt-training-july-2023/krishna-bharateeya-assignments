import React, { useState, useEffect } from 'react';
// import { BrowserRouter as Router, Routes, Route, useNavigate ,Outlet} from 'react-router-dom';
import { BrowserRouter as Router, Route, Routes, Route as RouteElement, Outlet } from 'react-router-dom';
import Login from './Components/Login_Signup/Login';
import Registration from './Components/Login_Signup/Registration';
import AdminHome from './Components/AdminHome/AdminHome';
import Category from './Components/Category/CategoryHome';
import AddCategory from './Components/Category/AddCategory';
import UserHome from './Components/UserHome/UserHome';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import UpdateCategory from './Components/Category/UpdateCategory';
import { Navigate } from 'react-router-dom';
import CategoryHome from './Components/Category/CategoryHome';
function App() {
  
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('role') !== null);


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
          
          <Route exact path="/" element={<Login />}/>
          <Route exact path="/registration" element={<Registration />}/>
          <Route path="/adminHome" element={<AdminHome />}/>
          <Route exact path="/userHome" element={<UserHome />}/>
          <Route exact path="/categoryHome" element={<CategoryHome />}/>
          <Route exact path="/addCategory" element={<AddCategory />}/>
          <Route exact path="/categoryHome/updateCategory/:categoryId" element={<UpdateCategory />}/>
          
        </Routes>
        
      </Router>
    </div>
  );
}

export default App;