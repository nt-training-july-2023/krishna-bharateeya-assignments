import './App.css';
import Login from './Components/Login_Signup/Login';

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Registration from './Components/Login_Signup/Registration';
import UserHome from './Components/UserHome/UserHome';
import AdminHome from './Components/AdminHome/AdminHome'
function App() {
  return (
    <Router>
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/registration" element={<Registration />} />
      <Route path="/adminHome" element={<AdminHome/>}/>
      <Route path="/userHome" element={<UserHome/>}/>
    </Routes>
  </Router>
  );
}

export default App;
