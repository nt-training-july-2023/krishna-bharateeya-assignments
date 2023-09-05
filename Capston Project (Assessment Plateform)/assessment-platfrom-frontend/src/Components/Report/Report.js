import React from 'react';
import './Report.css'
import Sidebar from '../AdminHome/Sidebar';
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';
const Report = () => {
  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'admin') {
    return (
     <UnauthorizedAccess/>
    );
  }
  return (
    <div className='report-wrapper'>
      <div className='report-container'>

        <div className='report-sidebar-column'>
          <Sidebar />
        </div>
        <div className='report-column'>

          <h1>this is Report page</h1>
        </div>

      </div>
    </div>


  )
}

export default Report
