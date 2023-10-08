import React from 'react';
import './UnauthorizedAccess.css';

const UnauthorizedAccess = () => {
  return (
    <div className="unauthorized-access-container">
      <div className="unauthorized-access-content">
        <h3 className="unauthorized-access-title">Unauthorized Access</h3>
        <p className="unauthorized-access-message">
          Sorry, you do not have permission to access this page.
        </p>
      </div>
    </div>
  );
};

export default UnauthorizedAccess;
