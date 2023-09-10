import React, { useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import './AddCategory.css';
import Sidebar from "../AdminHome/Sidebar";
import Swal from 'sweetalert2';


import UnauthorizedAccess from "../UnauthrizedAccess/UnauthorizedAccess"
const AddCategory = () => {

    const [categoryName, setCategoryName] = useState('');
    const [categoryNameError, setCategoryNameError] = useState('');

    const [description, setDescription] = useState('');
    const [descriptionError, setDescriptionError] = useState('');

    const [formData, setFormData] = useState({
        categoryName: '',
        description: ''
    });

    const validateNotEmpty = (value) => {
        if (!value.trim()) {
            return 'This field is required';
        }
        return '';
    };

    const handleCategoryNameChange = (value) => {
        setCategoryName(value);
        setCategoryNameError(validateNotEmpty(value));
    };

    const handleDescriptionChange = (value) => {
        setDescription(value);
        setDescriptionError(validateNotEmpty(value));
    };

    const navigate = useNavigate();
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleFormSubmit = async (event) => {
        event.preventDefault();

        const categoryNameChangeValidation = validateNotEmpty(categoryName);
        const descriptionChangeValidation = validateNotEmpty(description);

        setCategoryNameError(categoryNameChangeValidation);
        setDescriptionError(descriptionChangeValidation);

        if (categoryNameChangeValidation || descriptionChangeValidation) {
            return;
        }

        setIsSubmitting(true);

        try {
            const response = await axios.post('http://localhost:8080/category/save', {
                categoryName,
                description
            });
            console.log(response.data);
            navigate("/categoryHome");
        } catch (error) {
            console.error("An error occurred:", error);
        } finally {
            setIsSubmitting(false);
        }
    };

    const userRole = localStorage.getItem('userRole');
    if (userRole !== 'admin') {
        return (
            <UnauthorizedAccess/>
        );
    }
    return (

        <div className="add-category-wrapper">

            <div className="add-category-container">

                <div className='add-category-siderbar-column'>
                    <Sidebar />
                </div>
                <div className='add-category-column'>
                    <form onSubmit={handleFormSubmit} className="add-category-form">
                        <h2 className="category-title">Add Category</h2>
                        <div className="add-category-form-content">
                            <div className={`form-group ${categoryNameError ? 'has-error' : ''}`}>
                                {categoryNameError && <p className="error-message">{categoryNameError}</p>}
                                <div className={`category-form-container ${categoryNameError ? 'error-field' : ''}`}>
                                    <input
                                        type="text"
                                        className="category-form-control"
                                        id="categoryName"
                                        name="name"
                                        placeholder="Category Name"
                                        value={categoryName}
                                        onChange={(e) => handleCategoryNameChange(e.target.value)}
                                    />
                                </div>
                            </div>

                            <div className={`form-group ${descriptionError ? 'has-error' : ''}`}>
                                {descriptionError && <p className="error-message">{descriptionError}</p>}
                                <div className={`category-form-container ${descriptionError ? 'error-field' : ''}`}>
                                    <textarea
                                        type="text"
                                        className="category-form-control"
                                        id="description"
                                        name="description"
                                        placeholder="Category Description"
                                        value={description}
                                        onChange={(e) => handleDescriptionChange(e.target.value)}
                                    />
                                </div>
                            </div>


                            <div className="button-container">
                                <button
                                    type="submit"
                                    className="category-submit-button"
                                    disabled={isSubmitting}
                                >
                                    {isSubmitting ? 'Adding...' : 'Add'}
                                </button>
                                <button
                                    type="button"
                                    className="category-cancel-button"
                                    onClick={() => navigate("/categoryHome")}
                                >
                                    Cancel
                                </button>
                            </div>
                        </div>



                    </form>
                </div>
            </div>
        </div>

    );
}

export default AddCategory;
