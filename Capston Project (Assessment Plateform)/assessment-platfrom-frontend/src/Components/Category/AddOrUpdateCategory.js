import React, { useState, useEffect  } from "react";
import axios from 'axios';
import { useNavigate,useParams } from "react-router-dom";
import './AddOrUpdateCategory.css';
import Sidebar from "../AdminHome/Sidebar";
import {  toast } from "react-toastify";
import UnauthorizedAccess from "../UnauthrizedAccess/UnauthorizedAccess"



const AddOrUpdateCategory = () => {
  const { categoryId } = useParams();
  const navigate = useNavigate();

  const initialCategory = {
    categoryName: '',
    description: '',
  };

  const [category, setCategory] = useState(initialCategory);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const [categoryNameError, setCategoryNameError] = useState('');
  const [descriptionError, setDescriptionError] = useState('');

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setCategory({ ...category, [name]: value });
  
    if (name === 'categoryName') {
      setCategoryNameError('');
    } else if (name === 'description') {
      setDescriptionError('');
    }
  };
  

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);

    // Validate individual fields
    if (!category.categoryName.trim()) {
      setCategoryNameError('Category Name is required');
    } else {
      setCategoryNameError('');
    }

    if (!category.description.trim()) {
      setDescriptionError('Category Description is required');
    } else {
      setDescriptionError('');
    }

    if (!category.categoryName.trim() || !category.description.trim()) {
      toast.error('Fields Are Mandatory!!');
      setIsSubmitting(false);
      return;
    }

    try {
      let response;
      if (categoryId) {
        response = await axios.put(
          `http://localhost:8080/category/update/${categoryId}`,
          category
        );
        toast.success(response.data);
      } else {
        response = await axios.post('http://localhost:8080/category/save', category);
        toast.success('Category Added Successfully');
      }
      navigate('/categoryHome');
    } catch (error) {
      toast.error(
        error.response?.data?.message || 'An error occurred. Please try again.'
      );
    } finally {
      setIsSubmitting(false);
    }
  };

  const loadCategory = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/category/${categoryId}`);
      setCategory(response.data);
    } catch (error) {
      toast.error('Failed to load category');
    }
  };

  useEffect(() => {
    if (categoryId) {
      loadCategory();
    }
  }, [categoryId]);

  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'admin') {
    return <UnauthorizedAccess />;
  }

  return (
    <div className="add-category-wrapper">
      <div className="add-category-container">
        <div className="category-sidebar-column">
          <Sidebar />
        </div>
        <div className="add-category-column">
          <form className="add-category-form" onSubmit={handleFormSubmit}>
            <h2 className="category-title">{categoryId ? 'Update Category' : 'Add Category'}</h2>
            <div className="add-category-form-content">
              <div className="category-form-group">
                <input
                  type="text"
                  className={`category-form-control ${categoryNameError ? 'has-error' : ''}`}
                  id="categoryName"
                  name="categoryName"
                  value={category.categoryName}
                  onChange={onInputChange}
                  placeholder="Category Name"
                />
                {categoryNameError && <p className="error-message">{categoryNameError}</p>}
              </div>
              <div className="category-form-group">
                <textarea
                  className={`category-form-control ${descriptionError ? 'has-error' : ''}`}
                  id="description"
                  name="description"
                  value={category.description}
                  onChange={onInputChange}
                  rows={3}
                  placeholder="Category Description"
                />
                {descriptionError && <p className="error-message">{descriptionError}</p>}
              </div>
            </div>
            <div className="add-category-button-container">
              <button
                type="submit"
                className="add-category-submit-button"
                disabled={isSubmitting}
              >
                {isSubmitting ? (categoryId ? 'Updating...' : 'Adding...') : 'Submit'}
              </button>
              <button
                type="button"
                className="add-category-cancel-button"
                onClick={() => navigate('/categoryHome')}
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

export default AddOrUpdateCategory;
