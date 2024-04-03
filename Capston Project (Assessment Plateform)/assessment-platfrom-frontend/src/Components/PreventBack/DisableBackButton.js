import React, { useEffect } from "react";
import { useLocation, useParams } from "react-router-dom";

const DisableBackButton = () => {
  const location = useLocation();
  const { quizId } = useParams();

  useEffect(() => {
    const handlePopstate = () => {
      window.history.pushState(null, "", window.location.href);
    };

    if (
      location.pathname === `/userQuestion/${quizId}` 
    ) {
      window.history.pushState(null, "", window.location.href);
      window.addEventListener("popstate", handlePopstate);

      return () => {
        window.removeEventListener("popstate", handlePopstate);
      };
    }
  }, [location.pathname, quizId]);

  return null; 
};

export default DisableBackButton;
