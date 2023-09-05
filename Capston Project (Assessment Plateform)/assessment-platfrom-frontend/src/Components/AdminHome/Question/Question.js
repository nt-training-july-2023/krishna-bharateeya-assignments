import React from 'react'
import Sidebar from '../Sidebar'
import './Question.css'
import UnauthorizedAccess from '../../UnauthrizedAccess/UnauthorizedAccess';
const Question = () => {

  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'admin') {
    return (
      <UnauthorizedAccess/>
    );
  }
  return (
    <div className='question-wrapper'>
      <div className='question-container'>

        <div className='question-sidebar-column'>
            <Sidebar/>
        </div>
        <div className='question-column'>

          <h1>this is question component</h1>
        </div>

      </div>
    </div>
  );
}

export default Question;
