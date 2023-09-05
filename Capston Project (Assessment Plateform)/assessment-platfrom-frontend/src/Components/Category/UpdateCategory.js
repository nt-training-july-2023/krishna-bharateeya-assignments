import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';
import './UpdateCategory.css'
import Sidebar from '../AdminHome/Sidebar';
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';
const UpdateCategory = () => {
    const navigate = useNavigate();
    const { categoryId } = useParams();

    const [category, setCategory] = useState({
        categoryName: '',
        description: ''
    });

    const [isSubmitting, setIsSubmitting] = useState(false);
    const [error, setError] = useState(null);

    const onInputChange = (e) => {
        setCategory({ ...category, [e.target.name]: e.target.value });
    };


    useEffect(() => {
        loadCategory();
    }, []);

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        setIsSubmitting(true);

        try {
            const response = await axios.put(`http://localhost:8080/category/update/${categoryId}`, category);
            console.log(response.data);
            toast.success('Category Updated Successfully');
            navigate('/categoryHome');
        } catch (error) {
            setError(error.response?.data?.message || 'An error occurred. Please try again.');
        } finally {
            setIsSubmitting(false);
        }
    };

    const loadCategory = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/category/${categoryId}`);
            setCategory(response.data);
        } catch (error) {
            setError('Failed to load category');
        }
    };

    const userRole = localStorage.getItem('userRole');
    if (userRole !== 'admin') {
      return (
        <UnauthorizedAccess/>
      );
    }

    return (
        <div className="update-category-wrapper">


            <div className="update-category-container ">

                <div className='update-category-siderbar-column'>
                    <Sidebar />
                </div>

                <div className='update-category-column'>


                    <form className='update-category-form' onSubmit={handleFormSubmit}>
                        <h2 className="category-title">Update Category</h2>
                        <div className='update-category-form-content'>
                            <div className="update-category-form-group">
                                <input
                                    type="text"
                                    className="update-category-form-control"
                                    id="categoryName"
                                    name="categoryName"
                                    value={category.categoryName}
                                    onChange={onInputChange}
                                    required
                                />
                            </div>
                            <div className='update-category-form-group'>

                                <textarea
                                    className="update-category-form-control"
                                    id="description"
                                    name="description"
                                    value={category.description}
                                    onChange={onInputChange}
                                    rows={3}
                                />
                            </div>
                        </div>
                        <div className="update-button-container">
                            <button
                                type="submit"
                                className="update-category-submit-button"
                                disabled={isSubmitting}
                            >
                                {isSubmitting ? 'Updating...' : 'Update'}
                            </button>
                            <button
                                type="button"
                                className="update-category-cancel-button"
                                onClick={() => navigate("/categoryHome")}
                            >
                                Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    );
};

export default UpdateCategory;
