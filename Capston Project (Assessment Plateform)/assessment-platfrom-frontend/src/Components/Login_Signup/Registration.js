import { useNavigate } from 'react-router-dom';
import React, { useState } from 'react';
import axios from 'axios';
import './Registration.css';
import 'bootstrap/dist/css/bootstrap.min.css';
const Registration = () => {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [mobileNumber, setMobileNumber] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    
    const redirect = () => {
        navigate('/')
    }
    const handleRegistration = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/users/save', {
                firstName,
                lastName,
                mobileNumber,
                email,
                password
            });

            console.log('Registration successful!', response.data);

        } catch (error) {
            console.error('Registration failed:', error);
        }
    };


    return (
        <div className="registration-container">
            <form className="registration-form" onSubmit={handleRegistration}>

                <div className='form-content'>
                    <h2 className='form-title'>Registration</h2>
                    <div className='form-group mt-3'>
                        <input

                            type="text"
                            className="form-control mt-1"
                            name="firstName"
                            placeholder="first Name"
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                            required
                        />
                    </div>
                    <br />

                    <div className="form-group mt-3">
                        <input
                            type="text"
                            className="form-control mt-1"
                            name="lastName"
                            placeholder="Last name"
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                            required
                        />
                    </div>
                    <br />
                    <div className="form-group mt-3">
                        <input
                            type="tel"
                            className="form-control mt-1"
                            name="mobileNumber"
                            placeholder="Mobile Number"
                            value={mobileNumber}
                            onChange={(e) => setMobileNumber(e.target.value)}
                            required
                        />
                    </div>
                    <br />
                    <div className="form-group mt-3">
                        <input
                            type="text"
                            className="form-control mt-1"
                            name="email"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <br />
                    <div className="form-group mt-3">
                        <input
                            type="password"
                            className="form-control mt-1"
                            name="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <br />
                    <div className="d-grid gap-2 mt-3">
                        <button type="submit" className="btn btn-primary">
                            Submit
                        </button>
                    </div>
                </div>
                <div className="text-center my-3">
                    Already registered?{" "}
                    <span className="link-primary" style={{cursor:'pointer'}} onClick={redirect}>
                        Login
                    </span>
                </div>
            </form>

        </div>
    )
}

export default Registration;
