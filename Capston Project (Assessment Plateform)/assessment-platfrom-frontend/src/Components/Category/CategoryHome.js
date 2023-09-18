import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import './CategoryHome.css';
import Sidebar from '../AdminHome/Sidebar';
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import { LoadCategories, LoadQuizzesForCategory, DeleteCategory } from '../../ApiService/ApiService';

const CategoryHome = () => {
    const [categories, setCategories] = useState([]);
    const [relatedQuizzes, setRelatedQuizzes] = useState({});
    const [isPopupVisible, setPopupVisible] = useState(false);
    const [selectedCategoryId, setSelectedCategoryId] = useState(null);


    const { categoryId } = useParams();

    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
        const result = await await LoadCategories();
        setCategories(result);

        const quizzes = {};
        for (const category of result) {
            const quizResult = await LoadQuizzesForCategory(category.categoryId);;
            quizzes[category.categoryId] = quizResult;
        }
        setRelatedQuizzes(quizzes);
    };

    const deleteCategories = async (id) => {
        try {
            await DeleteCategory(id);;
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
    const handleShowRelatedQuizzes = (categoryId) => {
        setSelectedCategoryId(categoryId);
        setPopupVisible(true);
    };

    const handleClosePopup = () => {
        setSelectedCategoryId(null);
        setPopupVisible(false);
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
                                                <button
                                                        className="button-show-related-quizzes"
                                                        onClick={() => handleShowRelatedQuizzes(category.categoryId)}
                                                    >
                                                        Quizzes
                                                    </button>
                                                    <Link className="category-home-update-button" to={`/updateCategory/${category.categoryId}`}>Update</Link>
                                                    <button className="category-home-delete-button" onClick={() => confirmDelete(category.categoryId)}>Delete</button>
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

            {selectedCategoryId !== null && (
                <div className="related-quizzes-popup">
                    <div className="related-quizzes-popup-content">
                        
                        <h3>Related Quizzes for Category</h3>
                        <ul>
                            {relatedQuizzes[selectedCategoryId].map((quiz, index) => (
                                <li key={index}>{quiz.quizName}</li>
                            ))}
                        </ul>
                        <button className="close-popup-button" onClick={handleClosePopup}>
                            Close
                        </button>
                    </div>
                </div>
            )}
        </div>
    );


}

export default CategoryHome;
