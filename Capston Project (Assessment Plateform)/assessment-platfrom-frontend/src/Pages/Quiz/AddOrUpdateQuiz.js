import Sidebar from '../../Components/SideBar/Sidebar';
import UnauthorizedAccess from '../../Components/UnauthrizedAccess/UnauthorizedAccess';
import './AddOrUpdateQuiz.css'
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import React, { useState, useEffect } from 'react';
import Button from '../../Components/Button/Button';
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate, useParams } from "react-router-dom";
import { CreateQuiz, UpdateQuiz, LoadCategories, LoadCategoryById, GetQuizById } from '../../ApiService/ApiService';
import InputField from '../../Components/InputField/InputField';
const AddOrUpdateQuiz = () => {
  const { categoryId } = useParams();
  const { quizId } = useParams();
  const navigate = useNavigate();

  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('');
  const [selectedCategoryObject, setSelectedCategoryObject] = useState(null);
  const [quizName, setQuizName] = useState('');
  const [quizDescription, setQuizDescription] = useState('');
  const [quizTime, setQuizTime] = useState(30);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const [quizNameError, setQuizNameError] = useState('');
  const [quizDescriptionError, setQuizDescriptionError] = useState('');
  const [quizTimeError, setQuizTimeError] = useState('');
  const [selectedCategoryError, setSelectedCategoryError] = useState('');

  const validateNotEmpty = (value) => {
    if (typeof value === 'string' && !value.trim()) {
      return 'This field is required';
    } else if (typeof value === 'number') {
      if (isNaN(value) || value === 0) {
        return 'Please enter minimum 1 minute time';
      }
    }
    return '';
  };


  useEffect(() => {
    fetchData();
  }, [quizId, categoryId]);

  const fetchData = async () => {
    try {
      LoadCategories()
        .then((categories) => setCategories(categories))
        .catch((error) => toast.error(error.response?.data?.message ));

      if (categoryId) {
        setSelectedCategory(categoryId);
        const categoryData = await LoadCategoryById(categoryId);
        setSelectedCategoryObject(categoryData);
      }

      if (quizId) {
        const quizData = await GetQuizById(quizId);
        setSelectedCategory(quizData.category.categoryId);
        setQuizName(quizData.quizName);
        setQuizDescription(quizData.quizDescription);
        setQuizTime(quizData.timeInMinutes);
        setSelectedCategoryObject(quizData.category);
      }
    } catch (error) {
      toast.error(error.response?.data?.message || 'An error occurred. Please try again.');
    }
  };



  const handleQuizNameChange = (event) => {
    const value = event.target.value;
    setQuizName(value);
    setQuizNameError(validateNotEmpty(value));
  };
  const handleQuizTimeChange = (event) => {
    const value = event.target.value;
    setQuizTime(Math.abs(parseInt(value, 10)));
    setQuizTimeError(validateNotEmpty(value));
  };
  const handleQuizDescriptionChange = (event) => {
    const value = event.target.value;
    setQuizDescription(value);
    setQuizDescriptionError(validateNotEmpty(value));
  };

  
  const handleCategoryChange = async (event) => {
    const categoryId = event.target.value;
  
    try {
      const categoryObject = await LoadCategoryById(categoryId);
      setSelectedCategoryObject(categoryObject);
      setSelectedCategory(categoryId);
      setSelectedCategoryError('');
    } catch (error) {
      setSelectedCategoryObject(null);
      setSelectedCategory('');
      setSelectedCategoryError(
        error.response?.data?.message 
      );
      Swal.fire('Error', selectedCategoryError, 'error');
    }
  };
  

  const handleSubmit = async (event) => {
    event.preventDefault();

    const nameError = validateNotEmpty(quizName);
    const descriptionError = validateNotEmpty(quizDescription);
    const timeError = validateNotEmpty(quizTime);
    const categoryError = !selectedCategory ? 'Please select a category' : '';

    setQuizNameError(nameError);
    setQuizDescriptionError(descriptionError);
    setQuizTimeError(timeError);
    setSelectedCategoryError(categoryError);

    if (nameError || descriptionError || timeError || categoryError) {
      toast.error('Fields Are Mandatory!!');
      return;
    }

    const payload = {
      quizName: quizName,
      quizDescription: quizDescription,
      timeInMinutes: quizTime,
      category: selectedCategoryObject,
    };


    try {
      let result;
      if (quizId) {
        result = await UpdateQuiz(quizId, payload);
        toast.success(result.message);
      } else {
        result = await CreateQuiz(payload);
        toast.success(result.message);
      }
      const navigationUrl = categoryId ? `/quiz/${categoryId}` : '/quiz';
      navigate(navigationUrl);
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

  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'admin') {
    return <UnauthorizedAccess />;
  }

  return (
    <div className="add-Update-quiz-wrapper">
      <div className="add-Update-quiz-container">
        <div className="add-Update-quiz-sidebar-column">
          <Sidebar />
        </div>
        <div className="add-Update-quiz-column">
          <form onSubmit={handleSubmit} className="add-Update-quiz-form">
            <h2 className="add-Update-quiz-title">
              {quizId ? 'Update Quiz' : 'Add Quiz'}
            </h2>
            <div className="add-Update-quiz-content">
              <div className="form-groups">
                <label htmlFor="category-select">Category:</label>
                <select
                  className={`add-Update-field ${selectedCategoryError ? 'a-u-q-error-field' : ''
                    }`}
                  value={selectedCategory}
                  onChange={handleCategoryChange}
                >
                  <option value="" disabled>--Select a category--</option>
                  {categories.map((category) => (
                    <option
                      key={category.categoryId}
                      value={category.categoryId}
                    >
                      {category.categoryName}
                    </option>
                  ))}
                </select>
                {selectedCategoryError && (
                  <p className='a-u-q-error-message'>{selectedCategoryError}</p>
                )}
              </div>
              <div
                className={`form-group ${quizNameError ? 'has-error' : ''
                  }`}
              >
                <label htmlFor="quiz-name">Quiz Name:</label>
                <InputField
                  type="text"
                  className={`add-Update-field ${quizNameError ? 'a-u-q-error-field' : ''}`}
                  value={quizName}
                  onChange={handleQuizNameChange}
                />
                {quizNameError && (
                  <p className='a-u-q-error-message'>{quizNameError}</p>
                )}
              </div>
              <div
                className={`form-group ${quizTimeError ? 'has-error' : ''
                  }`}
              >
                <label htmlFor="quiz-time">Quiz Time(in minutes):</label>
                <InputField
                  type="number"
                  className={`add-Update-field ${quizTimeError ? 'a-u-q-error-field' : ''}`}
                  value={quizTime}
                  onChange={handleQuizTimeChange}
                />
                {quizTimeError && (
                  <p className='a-u-q-error-message'>{quizTimeError}</p>
                )}
              </div>
              <div
                className={`form-group ${quizDescriptionError ? 'has-error' : ''
                  }`}
              >
                <label htmlFor="quiz-desc">Quiz Description:</label>
                <textarea
                  type="text"
                  value={quizDescription}
                  onChange={handleQuizDescriptionChange}
                  className={`add-Update-field ${quizDescriptionError ? 'a-u-q-error-field' : ''}`}
                  rows="2"
                />
                {quizDescriptionError && (
                  <p className='a-u-q-error-message'>{quizDescriptionError}</p>
                )}
              </div>
              <div className="add-Update-button-container">
                <Button
                  type="submit"
                  className="add-Update-quiz-submit-button"
                  disabled={isSubmitting}
                >
                  {isSubmitting ? (quizId ? 'Updating...' : 'Adding...') : 'Submit'}
                </Button>
                <Button
                  type="button"
                  className="add-Update-quiz-cancel-button"
                  onClick={() => navigate(categoryId ? `/quiz/${categoryId}` : '/quiz')}
                >
                  Cancel
                </Button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddOrUpdateQuiz;
