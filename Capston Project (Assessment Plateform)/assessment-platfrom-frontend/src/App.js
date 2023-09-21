import { BrowserRouter as Router, Route, Routes,Navigate } from 'react-router-dom';
import Login from './Components/Login_Signup/Login';
import Registration from './Components/Login_Signup/Registration';
import AdminHome from './Components/AdminHome/AdminHome';
import UserHome from './Components/UserHome/UserHome';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import CategoryHome from './Components/Category/CategoryHome';
import Report from './Components/Report/Report';


import AddOrUpdateCategory from './Components/Category/AddOrUpdateCategory';
import AddOrUpdateQuiz from './Components/Quiz/AddOrUpdateQuiz';
import QuestionHome from './Components/AdminHome/Question/QuestionHome';
import AddOrUpdateQuestion from './Components/AdminHome/Question/AddOrUpdateQuestion';
import QuizHome from './Components/Quiz/QuizHome';
import UserTest from './Components/UserHome/UserTest';


function App() {

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
          
          <Route exact path="/userQuestion/:quizId" element={<PrivateRoute Component={UserTest} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          {/* <Route exact path="/userQuestion/:quizId"  Component={UserTest} /> */}

          <Route exact path="/report" element={<PrivateRoute Component={Report} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          
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



