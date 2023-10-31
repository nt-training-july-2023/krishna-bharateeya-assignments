import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './CategoryHome.css';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import { toast } from "react-toastify";
import Button from '../../Components/Button/Button';
import { LoadCategories, DeleteCategory, EnableCategory, DisableCategory } from '../../ApiService/ApiService';
import Sidebar from '../../Components/SideBar/Sidebar';
import NoDataMessage from '../../Components/NoDataMessage/NoDataMessage';

const CategoryHome = () => {

    const [categories, setCategories] = useState([]);
    const [categoryStatus, setCategoryStatus] = useState({});
    const userRole = localStorage.getItem('userRole');

    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
        console.log("form Category useeffects :");
        clearLocaleStorage();
        const result = await LoadCategories();
        const categoryStatusObj = {};
        if (userRole === 'admin') {
            result.forEach((category) => {
                categoryStatusObj[category.categoryId] = category.enabled;
            });
            setCategoryStatus(categoryStatusObj);
        }
        const filteredCategories = (userRole === 'user')
            ? result.filter(category => category.enabled)
            : result;

        setCategories(filteredCategories);
        setCategoryStatus(categoryStatusObj);
    };
    const clearLocaleStorage = () => {
        localStorage.removeItem("timerInSeconds");
        localStorage.removeItem("selectedAnswers");
        localStorage.removeItem("userName");
        localStorage.removeItem("categoryName");
        localStorage.removeItem("quizName");
        localStorage.removeItem("reloadAttempts");
        localStorage.removeItem("totalQuestion");
        localStorage.removeItem("totalMarks");
        localStorage.removeItem("userEmail");
        localStorage.removeItem("correctAnswersCount");
        localStorage.removeItem("wrongAnswers");
        localStorage.removeItem("attemptedQuestions");
    };

    const deleteCategories = async (id) => {
        try {
            await DeleteCategory(id);;
            loadCategories();

        } catch (error) {
            Swal.fire('Error', error.response.data.message, 'error');
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


    const handleToggle = async (categoryId) => {
        try {
            // loadCategories();
            if (categoryStatus[categoryId]) {
                await DisableCategory(categoryId);
            } else {
                await EnableCategory(categoryId);
            }
            setCategoryStatus((prevCategoryStatus) => ({

                ...prevCategoryStatus,
                [categoryId]: !prevCategoryStatus[categoryId],
            }));
            toast.success(
                categoryStatus[categoryId]
                    ? 'Category disabled successfully'
                    : 'Category enabled successfully'
            );
            
        } catch (error) {
            const errorMessage =
                error.response?.data?.message || 'An error occurred. Please try again.';
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: errorMessage,
            });
        }
    };



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
                                <h3>Put Your Efforts to the Test !</h3>
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
                            {categories.length === 0 ? (
                                <NoDataMessage message="No Categories found." />
                            ) : (
                                <div className="table-wrapper">
                                    <table className="category-table">
                                        <thead className="category-table-secondary">
                                            <tr>
                                                <th scope="col">S.No.</th>
                                                <th scope="col">Name</th>
                                                <th scope="col">Description</th>
                                                <th scope="col">Actions</th>
                                                {userRole === 'admin' && (
                                                    <th scope="col">Publish</th>
                                                )}
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

                                                    {userRole === 'admin' && (
                                                        <td>
                                                            <label className="toggle-switch">
                                                                <input
                                                                    type="checkbox"
                                                                    checked={categoryStatus[category.categoryId]}
                                                                    onChange={() => handleToggle(category.categoryId)}
                                                                />
                                                                <span className="slider round"></span>
                                                            </label>
                                                        </td>
                                                    )}

                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );

}
export default CategoryHome;
