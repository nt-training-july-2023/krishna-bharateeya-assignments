import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Login.css'

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();
    const redirect = () => {
        navigate('/registration')
    }
    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/users/login', {
                email,
                password
            });

            if(response.data.role==='admin')
                navigate('/adminHome')
            if(response.data.role==='user')
                navigate('/userHome')
              
            console.log('Login successful!', response.data);

        } catch (error) {
            console.error('Login failed:', error);
        }
    };

    return (
        <div className="login-container">
            <form className="login-form" onSubmit={handleLogin}>
                <h2 className='form-title'>Login</h2>
                <div className='form-content'>

                    <div className='form-group mt-3'>
                        <input
                            type="text"
                            className="form-control mt-1"
                            name="username"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <br />
                    <div className='form-group mt-3'>
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
                    <div className="d-grid gap-2 mt-3">
                        <button type="submit" className="btn btn-primary">
                            Login
                        </button>
                    </div>
                </div>
               

                <div className="text-center my-3">
                Don't have an account?{" "}
                    <span className="link-primary" style={{cursor:'pointer'}} onClick={redirect}>
                    Register here
                    </span>
                </div>
            </form>

        </div>
    );
};

export default Login;
