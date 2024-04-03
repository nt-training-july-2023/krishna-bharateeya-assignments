import './Sidebar.css'
import React, { useState, useEffect } from 'react';
import Swal from 'sweetalert2';
import { FaQuora, FaBars, FaRegChartBar, FaQuestionCircle, FaPowerOff, FaThList, FaHome } from "react-icons/fa";
import { NavLink, useNavigate } from 'react-router-dom';
import './Sidebar.css';
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';
import CustomLogo from '../../Assets/Logo/logo.png'
const Sidebar = ({ children }) => {
  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => setIsOpen(!isOpen);
  const navigate = useNavigate();
  const userRole = localStorage.getItem('userRole');
  const handleLogout = () => {
    navigate('/');
    toast.success("Logout successfully.");
    localStorage.removeItem('IsLoggedIn');
    localStorage.removeItem('userRole');
    localStorage.removeItem('email');
  };

  const handleLogoutConfirmation = () => {
    Swal.fire({
      text: "Confirm Logout?",
      icon: "warning",
      showCancelButton: true,
      showConfirmButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        handleLogout();
      }
    });
  }
  const menuItem = [
    {
      path: userRole === 'admin' ? "/adminHome" : "/userHome",
      name: "Home",
      icon: <FaHome />
    },
    {
      path: "/categoryHome",
      name: "Category",
      icon: <FaThList />
    },

    {
      path: "/quiz",
      name: "Quiz",
      icon: <FaQuora />
    },

    {
      path: "/question",
      name: "Question",
      icon: <FaQuestionCircle />
    },

    {
      path: "/report",
      name: "Report",
      icon: <FaRegChartBar />
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

  const toggleButton = isOpen ? null : toggle;
  return (
    <div className="container">
      <div style={{ width: isOpen ? "155px" : "50px" }} className={`sidebar ${isOpen ? 'open' : ''}`}>
        <div className="top_section">
          <img src={CustomLogo} alt="Custom Logo" style={{ display: isOpen ? "block" : "none" }} className="home-logo" />
          <div style={{ marginLeft: isOpen ? "40px" : "0px" }} className="bars">
            <FaBars onClick={toggleButton} />
          </div>
        </div>
        {
          menuItem.map((item, index) => (
            (item.name === "Question" && userRole !== "admin") ? null : (

              <NavLink
                to={item.path}
                key={index}
                className="link"
                activeClassName="active"
                onClick={() => {
                  if (item.name === "Logout") {
                    handleLogoutConfirmation();
                  }
                }}
              >
                <div className="icon">{item.icon}</div>
                <div style={{ display: isOpen ? "block" : "none" }} className="link_text">{item.name}</div>
              </NavLink>
            )
          ))
        }
        <div className={`sidebar-logout-button ${isOpen ? 'open' : 'closed'}`}>
          <NavLink
            className="link"
            onClick={handleLogoutConfirmation}
          >
            <div className="icon">
              <div className='powerOff-icon'>
                < FaPowerOff />
              </div>
              <div style={{ display: isOpen ? "block" : "none" }} className="link_text">Logout</div>
            </div>
          </NavLink>
        </div>
      </div>
      <main>{children}</main>
    </div>
  );
};

export default Sidebar;