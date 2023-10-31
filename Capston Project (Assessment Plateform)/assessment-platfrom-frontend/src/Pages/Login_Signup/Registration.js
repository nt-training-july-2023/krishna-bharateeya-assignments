import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './Registration.css';
import 'react-toastify/dist/ReactToastify.css';
import Swal from 'sweetalert2'; 
import 'sweetalert2/dist/sweetalert2.min.css';
import { toast } from 'react-toastify';
import { RegistrationService } from '../../ApiService/ApiService';
import Button from '../../Components/Button/Button';
import InputField from '../../Components/InputField/InputField';
const Registration = () => {
    const [firstName, setFirstName] = useState('');
    const [firstNameError, setFirstNameError] = useState('');

    const [lastName, setLastName] = useState('');
    const [lastNameError, setLastNameError] = useState('');

    const [mobileNumber, setMobileNumber] = useState('');
    const [mobileNumberError, setMobileNumberError] = useState('');

    const [email, setEmail] = useState('');
    const [emailError, setEmailError] = useState('');

    const [password, setPassword] = useState('');
    const [passwordError, setPasswordError] = useState('');

    const [confirmPassword, setConfirmPassword] = useState('');
    const [confirmPasswordError, setConfirmPasswordError] = useState('');

    const navigate = useNavigate();

    useEffect(() => {
        const isLoggedIn = localStorage.getItem('IsLoggedIn');
        const userRole = localStorage.getItem('userRole');

        if (isLoggedIn === 'true') {
            if (userRole === 'admin') {
                navigate('/adminHome');
            } else if (userRole === 'user') {
                navigate('/userHome');
            }
        }
    }, [navigate]);

    const redirect = () => {
        navigate('/');
    };

    const validateNotEmpty = (value) => {
        if (!value.trim()) {
            return 'This field is required';
        }
        return '';
    };

    const validateNumeric = (value) => {
        const numericPattern = /^[0-9]*$/;
        if (!numericPattern.test(value)) {
            return 'Please enter a valid numeric value';
        }
        return '';
    };

    const validateMobileNumberLength = (number) => {

        if (number.length < 10) {
            return 'Please enter 10 digits mobile number';
        }
        if (number.length > 10) {
            return 'Please enter 10 digits mobile number only';
        }
        return '';
    };
    const validateEmail = (value) => {
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (!emailPattern.test(value)) {
            return 'Please enter a valid email address';
        }
        return '';
    };

    const validateEmailDomainName = (email) => {
        if (!email.endsWith('@nucleusteq.com')) {
            return 'Email must end with @nucleusteq.com';
        }
        const emailRegex = /^[a-zA-Z][a-zA-Z0-9._-]*@nucleusteq\.com$/;

        if (!emailRegex.test(email)) {
          return 'Email must start with a letter and end with @nucleusteq.com';
        }
        return '';
    };


    const handleFirstNameChange = (value) => {
        setFirstName(value);
        setFirstNameError(validateNotEmpty(value));
    };

    const handleLastNameChange = (value) => {
        setLastName(value);
        setLastNameError(validateNotEmpty(value));
    };

    const handleMobileNumberChange = (value) => {
        setMobileNumber(value);
        setMobileNumberError(validateNotEmpty(value) || validateNumeric(value) || validateMobileNumberLength(value));
    };

    const handleEmailChange = (value) => {
        setEmail(value);
        setEmailError(validateNotEmpty(value) || validateEmail(value) || validateEmailDomainName(value));
    };


    const validatePassword = (password) => {
        if (password.length < 4) {
            return 'Password should be at least 4 characters';
        }
        if (password !== confirmPassword) {
            return 'Passwords do not match';
        }
        return '';
    };


    const handlePasswordChange = (value) => {
        setPassword(value);
        setPasswordError(validateNotEmpty(value) || validatePasswordLength(value));
        if (confirmPassword) {
            setConfirmPasswordError(validatePassword(confirmPassword));
        }
    };


    const validatePasswordLength = (password) => {
        if (password.length < 4) {
            return 'Password should be at least 4 characters';
        }
        return '';
    };
    const handleConfirmPasswordChange = (value) => {
        setConfirmPassword(value);
        setConfirmPasswordError(validatePasswordsMatch(password, value) || validateNotEmpty(value));
    };


    const validatePasswordsMatch = (password, confirmPassword) => {
        if (password !== confirmPassword) {
            return 'Passwords do not match';
        }
        return '';
    };



    const handleRegistration = async (e) => {
        e.preventDefault();

        const firstNameError = validateNotEmpty(firstName);
        const lastNameError = validateNotEmpty(lastName);
        const mobileNumberError = validateNotEmpty(mobileNumber) || validateNumeric(mobileNumber);
        const emailError = validateNotEmpty(email) || validateEmail(email) || validateEmailDomainName(email);
        const passwordError = validateNotEmpty(password) || validatePasswordLength(password);
        const confirmPasswordError = validatePasswordsMatch(password, confirmPassword) || validateNotEmpty(confirmPassword);

        setFirstNameError(firstNameError);
        setLastNameError(lastNameError);
        setMobileNumberError(mobileNumberError);
        setEmailError(emailError);
        setPasswordError(passwordError);
        setConfirmPasswordError(confirmPasswordError);

        if (
            firstNameError ||
            lastNameError ||
            mobileNumberError ||
            emailError ||
            passwordError ||
            confirmPasswordError
        ) {
            toast.error('Please enter correct information before submitting.');
            return;
        }

        try {
            const userData = {
                firstName,
                lastName,
                mobileNumber,
                email,
                password,
            };

            const response = await RegistrationService(userData);
            toast.success(response.message);
            navigate('/');
        } catch (error) {
            if (error.response) {
                Swal.fire('Login Failed', error.response.data.message, 'error');
            } else {
                Swal.fire('Login Failed', 'The server is currently unavailable. Please try again later.', 'error');
            }
        }
    };

    return (
        <div className="registration-container">
            <form className="registration-form" onSubmit={handleRegistration}>
                <div className="registration-form-content">
                    <h2 className="registration-form-title">Registration</h2>

                    <div className='main-column'>
                        <div className='col1'>
                            <div className={`registration-form-group`}>
                                {/* {firstNameError && <p className="registration-error-message">{firstNameError}</p>} */}
                                <InputField
                                    type="text"
                                    className={`registration-form-control`}
                                    name="firstName"
                                    placeholder="First Name"
                                    value={firstName}
                                    onChange={(e) => handleFirstNameChange(e.target.value)}

                                />
                                <div className='registration-error-message'>{emailError}</div>
                            </div>

                            <div className={`registration-form-group`}>
                                {/* {mobileNumberError && <p className="registration-error-message">{mobileNumberError}</p>} */}
                                <InputField
                                    type="tel"
                                    className={`registration-form-control`}
                                    name="mobileNumber"
                                    placeholder="Mobile Number"
                                    value={mobileNumber}
                                    onChange={(e) => handleMobileNumberChange(e.target.value)}

                                />
                                <div className='login-error-message'>{mobileNumberError}</div>
                            </div>

                            <div className={`registration-form-group`}>
                                {/* {passwordError && <p className="registration-error-message">{passwordError}</p>} */}
                                <InputField
                                    type="password"
                                    className={`registration-form-control`}
                                    k name="password"
                                    placeholder="Password"
                                    value={password}
                                    onChange={(e) => handlePasswordChange(e.target.value)}

                                />
                                <div className='login-error-message'>{passwordError}</div>
                            </div>

                        </div>

                        <div className='col2'>

                            <div className={`form-group`}>
                                {/* {lastNameError && <p className="registration-error-message">{lastNameError}</p>} */}
                                <InputField
                                    type="text"
                                    className={`registration-form-control`}
                                    name="lastName"
                                    placeholder="Last name"
                                    value={lastName}
                                    onChange={(e) => handleLastNameChange(e.target.value)}

                                />
                                <div className='login-error-message'>{lastNameError}</div>
                            </div>

                            <div className={`registration-form-group`}>
                                {/* {emailError && <p className="registration-error-message">{emailError}</p>} */}
                                <InputField
                                    type="text"
                                    className={`registration-form-control`}
                                    name="email"
                                    placeholder="Email"
                                    value={email}
                                    onChange={(e) => handleEmailChange(e.target.value)}

                                />
                                <div className='login-error-message'>{emailError}</div>
                            </div>


                            <div className={`registration-form-group`}>
                                {/* {confirmPasswordError && <p className="registration-error-message">{confirmPasswordError}</p>} */}
                                <InputField
                                    type="password"
                                    className={`registration-form-control`}
                                    name="confirmPassword"
                                    placeholder="Confirm Password"
                                    value={confirmPassword}
                                    onChange={(e) => handleConfirmPasswordChange(e.target.value)}

                                />
                                <div className='login-error-message'>{confirmPasswordError}</div>
                            </div>
                        </div>

                    </div>
                    <div className="registration-submit">
                        <Button onClick={handleRegistration} className="button" type="submit" children='Submit'></Button>
                    </div>
                </div>
                <div className="registration-text-center">
                    Already registered?{' '}
                    <span
                        className="registration-link-primary"
                        style={{ cursor: 'pointer' }}
                        onClick={redirect}
                    >
                        Login
                    </span>
                </div>
            </form>
        </div>
    );
};

export default Registration;
