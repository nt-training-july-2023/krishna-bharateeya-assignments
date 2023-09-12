import './Sidebar.css'
import React, { useState,useEffect } from 'react';

import {  FaQuora,  FaBars,  FaRegChartBar,  FaQuestionCircle,  FaPowerOff,  FaThList,  FaHome} from "react-icons/fa";
import { NavLink } from 'react-router-dom';
import './Sidebar.css';

const Sidebar = ({ children }) => {
  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => setIsOpen(!isOpen);

  // const handleLogout = () => {
  //   console.log("logout function classed");
  //   localStorage.removeItem('IsLoggedIn');
  //   localStorage.removeItem('userRole');
  // };

  const menuItem = [
    {
      path: "/adminHome",
      name: "Home",
      icon: <FaHome />
    },
    {
      path: "/categoryHome",
      name: "Category",
      icon: <FaThList />
    },
    {
      path: "/question",
      name: "Question",
      icon: <FaQuestionCircle />
    },
    {
      path: "/quiz",
      name: "Quiz",
      icon: <FaQuora />
    },
    {
      path: "/report",
      name: "Report",
      icon: <FaRegChartBar />
    },
    {
      path: "/",
      name: "Logout",
      icon: <FaPowerOff />,
      // onClick: handleLogout

    },
  ]

  useEffect(() => {
    const handleResize = () => {
      if (window.innerWidth <= 768) {
        setIsOpen(false);
      } else {
        setIsOpen(true);
      }
    };
    handleResize();
    window.addEventListener('resize', handleResize);
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);


  return (
    <div className="container">
      <div style={{ width: isOpen ? "155px" : "50px" }} className={`sidebar ${isOpen ? 'open' : ''}`}>
        <div className="top_section">
          <h1 style={{ display: isOpen ? "block" : "none" }} className="logo">Admin</h1>
          <div style={{ marginLeft: isOpen ? "40px" : "0px" }} className="bars">
            <FaBars onClick={toggle} />
          </div>
        </div>
        {
          menuItem.map((item, index) => (
            <NavLink to={item.path} key={index} className="link" activeClassName="active">
              <div className="icon">{item.icon}</div>
              <div style={{ display: isOpen ? "block" : "none" }} className="link_text">{item.name}</div>
            </NavLink>
          ))
        }
      </div>
      <main>{children}</main>
    </div>
  );
};

export default Sidebar;
