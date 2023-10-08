import React, { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './Login.css';
import Swal from 'sweetalert2'; 9
import 'sweetalert2/dist/sweetalert2.min.css';
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';
import Button from '../../Components/Button/Button';
import InputField from '../../Components/InputField/InputField';
import { LoginService } from '../../ApiService/ApiService';

const Login = () => {
    const [email, setEmail] = useState('');
    const [emailError, setEmailError] = useState('');

    const [password, setPassword] = useState('');
    const [passwordError, setPasswordError] = useState('');

    const navigate = useNavigate();

    useEffect(() => {
        const isLoggedIn = localStorage.getItem('IsLoggedIn');
        const userRole = localStorage.getItem('userRole');

        if (isLoggedIn === 'true') {
            if (userRole === 'admin') {
                navigate('/adminHome');
            } else if (userRole === 'user') {
                navigate('/categoryHome');
            }
        }
    }, [navigate]);

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

            const userData = {
                email,
                password,
            };
            const response = await LoginService(userData);
            localStorage.setItem('IsLoggedIn', true);
            localStorage.setItem('userRole', response.role);
            localStorage.setItem('email', response.email);

            if (response.role === 'admin') {
                navigate('/adminHome');
            } else if (response.role === 'user') {
                navigate('/userHome');
            }
            toast.success(response.message);
        } catch (error) {
            Swal.fire('Login Failed', error.response.data.message, 'error');
        }
    };

    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleLogin}>
                <h2 className="login-form-title">Login</h2>
                <div className="login-form-content">

                    <div className={`login-form-group ${emailError ? 'has-error' : ''}`}>
                        {emailError && <p className="login-error-message">{emailError}</p>}

                        <InputField
                            type="text"
                            className={`login-form-control ${emailError ? 'login-error-field' : ''}`}
                            name="username"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => handleEmailChange(e.target.value)}

                        />
                    </div>
                    <br />
                    <div className={`login-form-group ${passwordError ? 'has-error' : ''}`}>
                        {passwordError && <p className="login-error-message">{passwordError}</p>}
                        <InputField
                            type="password"
                            className={`login-form-control ${passwordError ? 'login-error-field' : ''}`}
                            name="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => handlePasswordChange(e.target.value)}

                        />
                    </div>
                    <div className="login-submit">
                        <Button onClick={handleLogin} className="button" type="submit" children='Login'></Button>
                    </div>
                </div>

                <div className="login-text-center">
                    Don't have an account?{' '}
                    <span className="login-link-primary" onClick={redirect}>
                        Register here
                    </span>
                </div>
            </form>

        </div>
    );
};

export default Login;
