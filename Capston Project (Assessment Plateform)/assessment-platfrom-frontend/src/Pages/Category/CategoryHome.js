import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import './CategoryHome.css';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import Button from '../../Components/Button/Button';
import { LoadCategories, DeleteCategory } from '../../ApiService/ApiService';
import Sidebar from '../../Components/SideBar/Sidebar';

const CategoryHome = () => {
    const [categories, setCategories] = useState([]);
    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
        const result = await LoadCategories();
        setCategories(result);
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
            confirmButtonText: 'Delete it!',
            cancelButtonText: 'Cancel',
            confirmButtonColor: '#d33',
        });

        if (result.isConfirmed) {
            deleteCategories(id);
            Swal.fire('Deleted!', 'Your quiz has been deleted.', 'success');
        }
    };

    const userRole = localStorage.getItem('userRole');
    return (
        <div className="category-wrapper">
            <div className="category-container">
                    <div className='category-sidebar-column'>
                        <Sidebar />
                    </div>
                <div className='category-column'>
                    <div className="category-card">
                        <div className="category-card-header">
                            {userRole === 'user' ? (
                                <h3>Test Categories</h3>
                            ) : (
                                <>
                                    <h3>Categories Home</h3>
                                    <center>
                                        <Link className="add-category-button" to="/addCategory">Add Category</Link>
                                    </center>
                                </>
                            )}
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
                                    <tbody className='category-tbody'>
                                        {categories.map((category, index) => (
                                            <tr key={index}>
                                                <td>{index + 1}</td>
                                                <td>{category.categoryName}</td>
                                                <td>{category.description}</td>
                                                <td>
                                                    {userRole === 'user' ? (
                                                        <Link className="start-test-button" to={`/quiz/${category.categoryId}`}>Quizzes</Link>
                                                    ) : (
                                                        <>
                                                            <Link className="button-show-related-quizzes" to={`/quiz/${category.categoryId}`}>Quizzes</Link>
                                                            <Link className="category-home-update-button" to={`/updateCategory/${category.categoryId}`}>Update</Link>
                                                            <Button className="category-home-delete-button" onClick={() => confirmDelete(category.categoryId)} children='Delete'></Button>
                                                        </>
                                                    )}
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
