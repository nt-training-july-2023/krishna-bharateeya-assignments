import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import './Category.css';


const CategoryHome = () => {
    const [categories, setCategories] = useState([]);

    const { categoryId } = useParams();

    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
        const result = await axios.get('http://localhost:8080/category');
        setCategories(result.data);
    };

    const deleteCategories = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/category/delete/${id}`);
            loadCategories();

        } catch (error) {
            console.log(error.response.data.message || 'An error occurred. Please try again.');
        }
    };

    return (
        <div className="wrapper">
            <div className="container">
                <div className="card">
                    <div className="card-header">
                        <h3>Manage Categories</h3>
                        <center>
                            <Link className="button btn-user mx-3" to="/addCategory">Add Category</Link>
                        </center>
                    </div>
                    <div className="card-body">
                        <div className="table-wrapper">
                            <table className="table">
                                <thead className="table-secondary">
                                    <tr>
                                        <th scope="col">S.No.</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Description</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {categories.map((category, index) => (
                                        <tr key={index}>
                                            <td>{index + 1}</td>
                                            <td>{category.categoryName}</td>
                                            <td>{category.description}</td>
                                            <td>
                                                <Link className="button button-edit" to={`/categoryHome/updateCategory/${category.categoryId}`}>Update</Link>
                                                <button className="button button-delete" onClick={() => deleteCategories(category.categoryId)}>Delete</button>
                                            </td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
    
    
}

export default CategoryHome;
