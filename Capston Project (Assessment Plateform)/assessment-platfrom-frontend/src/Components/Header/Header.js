import React from 'react';

const Header = ({ headerClass,heading, buttonText, buttonClass, onButtonClick }) => {
  return (
    <div className={headerClass}>
      <h3>{heading}</h3>
      {buttonText && (
        <center>
          <button className={buttonClass} onClick={onButtonClick}>
            {buttonText}
          </button>
        </center>
      )}
    </div>
  );
};

export default Header;
