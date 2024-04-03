import React from 'react';
import './NoDataMessage.css'
const NoDataMessage = ({ message }) => {
  return (
    <div className="no-data-message">
      <p className='ndm-para'>{message}</p>
    </div>
  );
};

export default NoDataMessage;