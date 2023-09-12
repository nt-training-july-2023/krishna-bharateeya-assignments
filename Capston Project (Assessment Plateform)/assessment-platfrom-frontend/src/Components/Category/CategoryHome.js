import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import './CategoryHome.css';
import Sidebar from '../AdminHome/Sidebar';
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';


const CategoryHome = () => {
    const [categories, setCategories] = useState([]);
    const [relatedQuizzes, setRelatedQuizzes] = useState({});


    const { categoryId } = useParams();

    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
        const result = await axios.get('http://localhost:8080/category');
        setCategories(result.data);

        const quizzes = {};
        for (const category of result.data) {
            const quizResult = await axios.get(`http://localhost:8080/category/quizzes/${category.categoryId}`);
            quizzes[category.categoryId] = quizResult.data;
        }
        setRelatedQuizzes(quizzes);
    };

    const deleteCategories = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/category/delete/${id}`);
            loadCategories();

        } catch (error) {
            console.log(error.response.data.message || 'An error occurred. Please try again.');
        }
    };

    const confirmDelete = async (id) => {
        const result = await Swal.fire({
            title: 'Are you sure?',
            text: 'You won\'t be able to revert this!',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel',
            confirmButtonColor: '#d33',
        });

        if (result.isConfirmed) {
            deleteCategories(id);
            Swal.fire('Deleted!', 'Your quiz has been deleted.', 'success');
        }
    };


    const userRole = localStorage.getItem('userRole');
    if (userRole !== 'admin') {
        return (
            <UnauthorizedAccess />
        );
    }
    return (
        <div className="category-wrapper">
            <div className="category-container">

                <div className='category-sidebar-column'>

                    <Sidebar />
                </div>

                <div className='category-column'>
                    <div className="category-card">
                        <div className="category-card-header">
                            <h3>Manage Categories</h3>
                            <center>
                                <Link className="add-category-button" to="/addCategory">Add Category</Link>
                            </center>
                        </div>
                        <div className="category-card-body">
                            <div className="table-wrapper">
                                <table className="category-table">
                                    <thead className="category-table-secondary">
                                        <tr>
                                            <th scope="col">S.No.</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Description</th>
                                            <th scope="col">Related Quiz</th>
                                            <th scope="col">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {categories.map((category, index) => (
                                            <tr key={index}>
                                                <td>{index + 1}</td>
                                                <td>{category.categoryName}</td>
                                                <td>{category.description}</td>

                                                <div className="custom-select-container">
                                                    <select className="custom-select">
                                                        {relatedQuizzes[category.categoryId] &&
                                                            relatedQuizzes[category.categoryId].map((quiz, index) => (
                                                                <option key={index} className="custom-select-option">{quiz.quizName}</option>
                                                            ))
                                                        }
                                                    </select>
                                                    <div className="custom-select-arrow">&#9662;</div>
                                                </div>
                                                <td>
                                                    <Link className="button button-edit" to={`/updateCategory/${category.categoryId}`}>Update</Link>
                                                    <button className="button button-delete-quiz" onClick={() => confirmDelete(category.categoryId)}>Delete</button>


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
        </div>
    );


}

export default CategoryHome;
