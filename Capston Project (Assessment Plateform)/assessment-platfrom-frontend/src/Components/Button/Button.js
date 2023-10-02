import React from 'react'

const Button = (props) => {

    const { onClick, className, type, children } = props;

    return (
        <button type={type} className={className} onClick={onClick}>
            {children}
        </button>
    )
}

export default Button;
