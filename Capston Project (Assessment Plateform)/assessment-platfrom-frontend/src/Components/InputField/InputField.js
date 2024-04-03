import React from 'react'
const InputField = (props) => {

    const { type, name, placeholder, value, onChange, className, errorMessage } = props;
    return (
        <div className={`form-group ${errorMessage ? 'has-error' : ''}`}>
            {errorMessage && <p className="login-error-message">{errorMessage}</p>}
            <input
                type={type}
                className={className}
                name={name}
                placeholder={placeholder}
                value={value}
                onChange={onChange}
            />
        </div>
    )
}

export default InputField;
