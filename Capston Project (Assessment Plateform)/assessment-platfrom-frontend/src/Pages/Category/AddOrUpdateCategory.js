import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import './AddOrUpdateCategory.css';
import Sidebar from "../../Components/SideBar/Sidebar";
import { toast } from "react-toastify";
import UnauthorizedAccess from '../../Components/UnauthrizedAccess/UnauthorizedAccess';
import { LoadCategoryById, SaveCategory } from "../../ApiService/ApiService";
import InputField from "../../Components/InputField/InputField";
import Button from "../../Components/Button/Button";


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

        response = await SaveCategory(category, categoryId);
        toast.success(response.message);
      } else {
        response = await SaveCategory(category);
        toast.success(response.message);
      }
      navigate('/categoryHome');
    } catch (error) {

      const errorMessage =
        error.response?.data?.message || 'An error occurred. Please try again.';
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: errorMessage,
      });
    } finally {
      setIsSubmitting(false);
    }
  };

  const loadCategory = async () => {
    try {
      const response = await LoadCategoryById(categoryId);
      setCategory(response);
    } catch (error) {
      Swal.fire('Error', error.response.data.message, 'error');
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

              <div className={`category-form-group ${categoryNameError ? 'has-error' : ''}`}>
                {categoryNameError && <p className="category-error-message">{categoryNameError}</p>}
                <InputField
                  type="text"
                  className={`category-form-control ${categoryNameError ? 'has-error' : ''}`}
                  id="categoryName"
                  name="categoryName"
                  value={category.categoryName}
                  onChange={onInputChange}
                  placeholder="Category Name"
                />

              </div>
              <div className="category-form-group">
                {descriptionError && <p className="category-error-message">{descriptionError}</p>}
                <textarea
                  className={`category-form-control ${descriptionError ? 'category-error-field' : ''}`}
                  id="description"
                  name="description"
                  value={category.description}
                  onChange={onInputChange}
                  rows={3}
                  placeholder="Category Description"
                />

              </div>
            </div>
            <div className="add-category-button-container">
              <Button
                type="submit"
                className="add-category-submit-button"
                disabled={isSubmitting}
              >
                {isSubmitting ? (categoryId ? 'Updating...' : 'Adding...') : 'Submit'}
              </Button>
              <Button
                type="button"
                className="add-category-cancel-button"
                onClick={() => navigate('/categoryHome')}
              >
                Cancel
              </Button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddOrUpdateCategory;
