import { BrowserRouter as Router, Route, Routes,Navigate } from 'react-router-dom';
import Login from './Components/Login_Signup/Login';
import Registration from './Components/Login_Signup/Registration';
import AdminHome from './Components/AdminHome/AdminHome';
import UserHome from './Components/UserHome/UserHome';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import CategoryHome from './Components/Category/CategoryHome';
import Quiz from './Components/Quiz/Quiz';
import Question from './Components/AdminHome/Question/Question';
import Report from './Components/Report/Report';


import AddOrUpdateCategory from './Components/Category/AddOrUpdateCategory';
import AddOrUpdateQuiz from './Components/Quiz/AddOrUpdateQuiz';


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
          
          <Route exact path="/addCategory" element={<PrivateRoute Component={AddOrUpdateCategory} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/updateCategory/:categoryId" element={<PrivateRoute Component={AddOrUpdateCategory} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />

         
          <Route exact path="/quiz" element={<PrivateRoute Component={Quiz} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          
          <Route exact path="/add-quiz" element={<PrivateRoute Component={AddOrUpdateQuiz} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          <Route exact path="/update-quiz/:quizId" element={<PrivateRoute Component={AddOrUpdateQuiz} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />
          
          <Route exact path="/question" element={<PrivateRoute Component={Question} isLoggedIn={localStorage.getItem('IsLoggedIn')} />} />

          
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



