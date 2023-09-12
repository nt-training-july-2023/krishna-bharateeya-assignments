import Sidebar from '../AdminHome/Sidebar';
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';
import './AddOrUpdateQuiz.css'
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate, useParams } from "react-router-dom";

const AddOrUpdateQuiz = () => {
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
    }
    return '';
  };

  useEffect(() => {
    axios
      .get('http://localhost:8080/category')
      .then((response) => setCategories(response.data))
      .catch((error) =>
        toast.error(
          error.response?.data?.message ||
            'An error occurred when fetching categories. Please try again.'
        )
      );

    if (quizId) {
      axios
        .get(`http://localhost:8080/quizzes/${quizId}`)
        .then((response) => {
          const quizData = response.data;
          setSelectedCategory(quizData.category.categoryId);
          setQuizName(quizData.quizName);
          setQuizDescription(quizData.quizDescription);
          setQuizTime(quizData.timeInMinutes);
          setSelectedCategoryObject(quizData.category);
        })
        .catch((error) =>
          toast.error(
            error.response?.data?.message ||
              'An error occurred when fetching quiz details. Please try again.'
          )
        );
    }
  }, [quizId]);

  const handleQuizNameChange = (event) => {
    const value = event.target.value;
    setQuizName(value);
    setQuizNameError(validateNotEmpty(value));
  };
  const handleQuizTimeChange = (event) => {
    const value = event.target.value;
    setQuizTime(value);
    setQuizTimeError(validateNotEmpty(value));
  };
  const handleQuizDescriptionChange = (event) => {
    const value = event.target.value;
    setQuizDescription(value);
    setQuizDescriptionError(validateNotEmpty(value));
  };
      
  const handleCategoryChange = (event) => {
    const categoryId = event.target.value;

    axios
      .get(`http://localhost:8080/category/${categoryId}`)
      .then((response) => {
        const categoryObject = response.data;
        setSelectedCategoryObject(categoryObject);
        setSelectedCategory(categoryId);
        setSelectedCategoryError('');
      })
      .catch((error) => {
        setSelectedCategoryObject(null);
        setSelectedCategory('');
        setSelectedCategoryError(
          error.response?.data?.message ||
            'An error occurred when selecting a category. Please try again.'
        );
        toast.error(selectedCategoryError);
      });
  };

  const handleSubmit = (event) => {
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

    if (quizId) {
      axios
        .put(`http://localhost:8080/quizzes/${quizId}`, payload)
        .then(() => {
          toast.success('Quiz updated successfully');
          navigate('/quiz');
        })
        .catch((error) =>
          toast.error(
            error.response?.data?.message ||
              'An error occurred when updating the quiz. Please try again.'
          )
        );
    } else {
      axios
        .post('http://localhost:8080/quizzes', payload)
        .then(() => {
          toast.success('Quiz Added successfully');
          navigate('/quiz');
        })
        .catch((error) =>
          toast.error(
            error.response?.data?.message ||
              'An error occurred when adding. Please try again.'
          )
        );
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
            <div className="add-Update-quiz-form-content">
              <div className="form-groups">
                <label htmlFor="category-select">Category:</label>
                <select
                  className={`add-Update-field ${
                    selectedCategoryError ? 'has-error' : ''
                  }`}
                  value={selectedCategory}
                  onChange={handleCategoryChange}
                >
                  <option value="">--Select a category--</option>
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
                  <p className="error-message">{selectedCategoryError}</p>
                )}
              </div>
              <div
                className={`form-group ${
                  quizNameError ? 'has-error' : ''
                }`}
              >
                <label htmlFor="quiz-name">Quiz Name:</label>
                <input
                  type="text"
                  className="add-Update-field"
                  value={quizName}
                  onChange={handleQuizNameChange}
                />
                {quizNameError && (
                  <p className="error-message">{quizNameError}</p>
                )}
              </div>
              <div
                className={`form-group ${
                  quizTimeError ? 'has-error' : ''
                }`}
              >
                <label htmlFor="quiz-time">Quiz Time:</label>
                <input
                  type="text"
                  className="add-Update-field"
                  value={quizTime}
                  onChange={handleQuizTimeChange}
                />
                {quizTimeError && (
                  <p className="error-message">{quizTimeError}</p>
                )}
              </div>
              <div
                className={`form-group ${
                  quizDescriptionError ? 'has-error' : ''
                }`}
              >
                <label htmlFor="quiz-desc">Quiz Description:</label>
                <textarea
                  type="text"
                  value={quizDescription}
                  onChange={handleQuizDescriptionChange}
                  className="add-Update-field"
                  rows="2"
                />
                {quizDescriptionError && (
                  <p className="error-message">{quizDescriptionError}</p>
                )}
              </div>
              <div className="add-Update-button-container">
                <button
                  type="submit"
                  className="add-Update-quiz-submit-button"
                  disabled={isSubmitting}
                >
                  {isSubmitting ? (quizId ? 'Updating...' : 'Adding...') : 'Submit'}
                </button>
                <button
                  type="button"
                  className="add-Update-quiz-cancel-button"
                  onClick={() => navigate('/quiz')}
                >
                  Cancel
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddOrUpdateQuiz;
