import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';

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
            navigate('/manageCategory');
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

    return (
        <div className="wrapper">
            <div className="category-container ">
                <div className="card">
                    <div className="card-header">
                        <h3>Update Category</h3>
                    </div>
                    <div className="card-body">
                        <center>
                            <div className="card">
                                <div className="card-body">
                                    <form onSubmit={handleFormSubmit}>
                                        {error && <div className="error">{error}</div>}
                                        <div className="mb-4">
                                            <label htmlFor="name">Category Name</label>
                                            <input
                                                type="text"
                                                className="form-control"
                                                id="categoryName"
                                                name="categoryName"
                                                value={category.categoryName}
                                                onChange={onInputChange}
                                                required
                                            />
                                        </div>
                                        <div className="mb-4">
                                            <label htmlFor="description">Category Description</label>
                                            <textarea
                                                className="form-control"
                                                id="description"
                                                name="description"
                                               
                                                value={category.description}
                                                onChange={onInputChange}
                                                rows={3}
                                            />
                                        </div>
                                        <div className="d-grid gap-2">
                                            <button
                                                type="submit"
                                                className="btn btn-primary my-3"
                                                disabled={isSubmitting}
                                            >
                                                {isSubmitting ? 'Updating...' : 'Update'}
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </center>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default UpdateCategory;
