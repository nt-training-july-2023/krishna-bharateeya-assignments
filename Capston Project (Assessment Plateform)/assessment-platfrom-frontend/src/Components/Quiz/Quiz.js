import React from 'react'
import Sidebar from '../AdminHome/Sidebar';
import './Quiz.css'
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';
const Quiz = () => {

  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'admin') {
    return (
    <UnauthorizedAccess/>
    );
  }
  return (

    <div className='quiz-wrapper'>
      <div className='quiz-container'>

        <div className='quiz-sidebar-column'>
            <Sidebar/>
        </div>
        <div className='quiz-column'>

        <h1>this is quiz page</h1>
        </div>

      </div>
    </div>
  );
}

export default Quiz;
