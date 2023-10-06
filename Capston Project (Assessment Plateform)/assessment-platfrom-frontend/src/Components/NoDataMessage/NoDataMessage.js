import React from 'react';
import './NoDataMessage.css'
const NoDataMessage = ({ message }) => {
  return (
    <div className="no-data-message">
      <p>{message}</p>
    </div>
  );
};

export default NoDataMessage;