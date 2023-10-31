//orginal
import { BrowserRouter as Router, Route, Routes,Navigate } from 'react-router-dom';
import Login from './Pages/Login_Signup/Login';
import Registration from './Pages/Login_Signup/Registration';
import AdminHome from './Pages/AdminHome/AdminHome';
import UserHome from './Pages/UserHome/UserHome';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import CategoryHome from './Pages/Category/CategoryHome';
import TestReport from './Pages/Report/TestReport';


import AddOrUpdateCategory from './Pages/Category/AddOrUpdateCategory';
import AddOrUpdateQuiz from './Pages/Quiz/AddOrUpdateQuiz';
import QuestionHome from './Pages/Question/QuestionHome';
import AddOrUpdateQuestion from './Pages/Question/AddOrUpdateQuestion';

import QuizHome from './Pages/Quiz/QuizHome';
import UserTest from './Pages/UserHome/UserTest';
import React, { useState} from "react";

function App() {
  
  const[isRefresh, setIsRefresh]=useState(false)
   function setTrue() {
   setIsRefresh(true);
  }
  
  return (
    <div>
       <ToastContainer
        position="bottom-right"
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


          <Route exact path="/adminHome" element={<PrivateRoute Component={AdminHome} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />

          <Route exact path="/userHome" element={<PrivateRoute Component={UserHome} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />

         
          <Route exact path="/categoryHome" element={<PrivateRoute Component={CategoryHome} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/question/:quizId" element={<PrivateRoute Component={QuestionHome} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />

          <Route exact path="/addCategory" element={<PrivateRoute Component={AddOrUpdateCategory} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/updateCategory/:categoryId" element={<PrivateRoute Component={AddOrUpdateCategory} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />

         
          <Route exact path="/quiz" element={<PrivateRoute Component={QuizHome} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/quiz/:categoryId" element={<PrivateRoute Component={QuizHome} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />

          <Route exact path="/add-quiz" element={<PrivateRoute Component={AddOrUpdateQuiz} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/add-quiz/:categoryId" element={<PrivateRoute Component={AddOrUpdateQuiz} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/update-quiz/:quizId" element={<PrivateRoute Component={AddOrUpdateQuiz} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          
          <Route exact path="/question" element={<PrivateRoute Component={QuestionHome} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/question/:quizId" element={<PrivateRoute Component={QuestionHome} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/add-question" element={<PrivateRoute Component={AddOrUpdateQuestion} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/add-question/:quizId" element={<PrivateRoute Component={AddOrUpdateQuestion} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/update-question/:questionId" element={<PrivateRoute Component={AddOrUpdateQuestion} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          
          <Route exact path="/userQuestion/:quizId" element={<PrivateRoute Component={UserTest} isLoggedIn={localStorage.getItem('IsLoggedIn')}  isRefresh={isRefresh} setTrue={setTrue}/>} />
          
          <Route exact path="/report" element={<PrivateRoute Component={TestReport} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          {/* <Route path="*" element={<UserHome/>} /> */}

        </Routes>
      </Router>
    </div>
  );
}

const PrivateRoute = ({ Component }) => {
  const isLoggedIn = localStorage.getItem('IsLoggedIn')
  return isLoggedIn ? <Component /> : <Navigate to="/" replace />;
}

export default App;



