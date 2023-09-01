import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './Login.css';
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';

const Login = () => {
    const [email, setEmail] = useState('');
    const [emailError, setEmailError] = useState('');

    const [password, setPassword] = useState('');
    const [passwordError, setPasswordError] = useState('');

    const [response, setResponse] = useState({
        status: false,
        userRole: '',
        message: '',
      });

    const navigate = useNavigate();

    const redirect = () => {
        navigate('/registration');
    };

    const validateNotEmpty = (value) => {
        if (!value.trim()) {
            return 'This field is required';
        }
        return '';
    };


    const handleEmailChange = (value) => {
        setEmail(value);
        setEmailError(validateNotEmpty(value));
    };

    const handlePasswordChange = (value) => {
        setPassword(value);
        setPasswordError(validateNotEmpty(value));
    };

    const handleLogin = async (e) => {
        e.preventDefault();
    
        const emailValidation = validateNotEmpty(email);
        const passwordValidation = validateNotEmpty(password);
    
        setEmailError(emailValidation);
        setPasswordError(passwordValidation);
    
        if (emailValidation || passwordValidation) {
            return;
        }
    
        try {
            const response = await axios.post('http://localhost:8080/users/login', {
                email,
                password
            });
            localStorage.setItem('IsLoggedIn', response.data.status);
            localStorage.setItem('userRole', response.data.role);
            
            if (response.data.role === 'admin') {
                navigate('/adminHome');
            } else if (response.data.role === 'user') {
                navigate('/userHome');
            }
    
            localStorage.setItem('IsLoggedIn', response.data.status);
            localStorage.setItem('userRole', response.data.role);
    
            toast.success(response.data.message);
            console.log('Login successful!', response.data);
        } catch (error) {
            toast.error(error.response);
            console.error('Login failed:', error);
        }
    };
    



    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleLogin}>
                <h2 className="form-title">Login</h2>
                <div className="form-content">

                    <div className={`form-group ${emailError ? 'has-error' : ''}`}>
                        {emailError && <p className="error-message">{emailError}</p>}
                        <input
                            type="text"
                            className={`form-control ${emailError ? 'error-field' : ''}`}
                            name="username"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => handleEmailChange(e.target.value)}

                        />
                    </div>
                    <br />
                    <div className={`form-group ${passwordError ? 'has-error' : ''}`}>
                        {passwordError && <p className="error-message">{passwordError}</p>}
                        <input
                            type="password"
                            className={`form-control ${passwordError ? 'error-field' : ''}`}
                            name="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => handlePasswordChange(e.target.value)}

                        />
                    </div>
                    <div className="login-submit">
                        <button type="submit" className="button">
                            Login
                        </button>
                    </div>
                </div>

                <div className="text-center">
                    Don't have an account?{' '}
                    <span className="link-primary" onClick={redirect}>
                        Register here
                    </span>
                </div>
            </form>

        </div>
    );
};

export default Login;
