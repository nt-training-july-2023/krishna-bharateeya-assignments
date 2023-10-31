import React, { useEffect } from 'react';

const VisibilityChangeHandler = ({ submit }) => {
  useEffect(() => {
    const handleVisibilityChange = () => {
      if (document.hidden) {
        console.log("someting");
        submit();
        console.log('Tab switched away');
      }
    };

    document.addEventListener('visibilitychange', handleVisibilityChange);

    return () => {
      document.removeEventListener('visibilitychange', handleVisibilityChange);
    };
  }, [submit]);

  return null; 
};

export default VisibilityChangeHandler;
