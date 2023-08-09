import './App.css';
import React from 'react';
const RegistrationPage = () => {
  return (
<div className='class-body'>
       <div class="container">
        <div class="title">Registeration</div>
        <form>

            <div class="main-content">
                <div class="input-box">
                    <label for="username">First Name:</label>
                    <input type="text" id="username" name="F_name" placeholder="Enter your first name" required
                        class="input" />
                </div>

                <div class="input-box">
                    <label for="username">Last Name:</label>
                    <input type="text" id="username" name="L_name" placeholder="Enter your last name" required
                        class="input" />
                </div>


                <div class="input-box">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email" required class="input" />
                </div>
                <div class="input-box">
                    <label for="mobile-number">Mobile Number:</label>
                    <input type="tel" id="mobile-number" name="mobile-number" placeholder="Enter your mobile number"
                        required />
                </div>
                <div class="input-box">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password" required />
                </div>
                <div class="input-box">
                    <label for="confirm-password">Confirm Password:</label>
                    <input type="password" id="confirm-password" name="confirm-password"
                        placeholder="Confirm your password" required />
                </div>
            </div>

            <div>
                <div class="gender-category">
                    <span class="gender-title"> Gender</span>
                    <label><input type="radio" name="gender" value="male" required />Male</label>
                    <label><input type="radio" name="gender" value="female" required />Female</label>
                    <label><input type="radio" name="gender" value="other" required />Prefer not to say</label>
                </div>


            </div>

            <div class="button-container">
                <button type="submit" onclick="validateForm()">Register</button>
            </div>
        </form>
    </div>
    </div>
    
  )
}

export default RegistrationPage
