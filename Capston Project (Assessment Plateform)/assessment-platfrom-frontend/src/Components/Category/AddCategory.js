import React, { useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

const AddCategory = () => {
    const [formData, setFormData] = useState({
        categoryName: '',
        description: ''
    });

    const navigate = useNavigate();
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleInputChange = (e) => {
        const { id, value } = e.target;
        setFormData((prevState) => ({ ...prevState, [id]: value }));
    };

    const handleFormSubmit = async (event) => {
        event.preventDefault();
        setIsSubmitting(true);

        try {
            const response = await axios.post('http://localhost:8080/category/save', formData);
            console.log(response.data);
            navigate("/categoryHome"); 

        } catch (error) {
            console.error("An error occurred:", error);
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="category-container">
            <div className="container">
                <div className="card" style={{ boxShadow: '3px 3px 5px 5px #939fd4', marginTop: '20px', minHeight: '60vh' }}>
                    <div className="card-header">
                        <h3>Add Category</h3>
                    </div>
                    <form onSubmit={handleFormSubmit} className="">
                        <div className="card-body">
                            <center>
                                <div className="card" style={{ width: '70%', height: '35vh' }}>
                                    <div className="card-body">
                                        <div className="form-floating my-3 mb-3">
                                            <input
                                                type="text"
                                                className="form-control"
                                                id="categoryName"
                                                name="name"
                                                placeholder="Category Name"
                                                value={formData.categoryName}
                                                onChange={handleInputChange}
                                                required
                                            />
                                            <label htmlFor="name">Category Name</label>
                                        </div>

                                        <div className="form-floating my-3 mb-3">
                                            <input
                                                type="text"
                                                className="form-control"
                                                id="description"
                                                name="description"
                                                placeholder="Category Description"
                                                value={formData.description}
                                                onChange={handleInputChange}
                                                required
                                            />
                                            <label htmlFor="description">Category Description</label>
                                        </div>

                                        <div className="d-grid gap-2">
                                            <button type="submit" className="button btn-user my-3 mb-3" disabled={isSubmitting}>
                                                {isSubmitting ? 'Adding...' : 'Add'}
                                            </button>
                                        </div>

                                        <div className="d-grid gap-2">
                                            <button type="button" className="button button-delete mb-3" onClick={() => navigate("/categoryHome")} >
                                                Cancel
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </center>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default AddCategory;
