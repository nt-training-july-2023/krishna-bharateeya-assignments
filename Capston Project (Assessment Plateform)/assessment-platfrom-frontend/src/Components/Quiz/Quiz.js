import React, { useState, useEffect } from 'react';
import Sidebar from '../AdminHome/Sidebar';
import './Quiz.css'
import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

const Quiz = () => {

  const [quizzes, setQuizzes] = useState([]);
  const { categoryId } = useParams();

  useEffect(() => {
    loadQuizzes();
  }, []);

  const loadQuizzes = async () => {
    const result = await axios.get('http://localhost:8080/quizzes');
    setQuizzes(result.data);
  };

  const deleteQuiz = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/quizzes/${id}`);
      loadQuizzes();

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
      deleteQuiz(id);
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

    <div className='quiz-wrapper'>
      <div className='quiz-container'>

        <div className='quiz-sidebar-column'>
          <Sidebar />
        </div>
        <div className='quiz-column'>

          <div className="quiz-main-card">
            <div className="quiz-card-header-main">
              <h3>Quiz Home</h3>
              <center>
                <Link className="add-quiz-button" to="/add-quiz">Add Quiz</Link>
              </center>
            </div>
            <div className="category-card-body">
              <div className="table-wrapper">
                <table className="quiz-table">

                  <tbody className='quiz-table-content'>
                    {quizzes.map((quiz, index) => (
                      <div className="quiz-card" key={index}>
                        <div className="quiz-card-header">
                          <h3>{quiz.quizName}</h3>
                          <p>{quiz.quizDescription}</p>
                        </div>

                        <div className="quiz-card-body">
                          <div className="quiz-card-detail">
                            <p><strong>Category Name: </strong>{quiz.category.categoryName}</p>

                          </div>

                          <div className="quiz-card-detail">
                            <p><strong>Time:</strong> {quiz.timeInMinutes} minutes</p>
                          </div>
                        </div>
                        <div className="quiz-card-footer">
                          <button className="button button-update-time" to={`/categoryHome/updateCategory/${quiz.quizId}`}>Add/View Questions</button>
                          <Link className="button button-update-quiz" to={`/update-quiz/${quiz.quizId}`}>Update Quiz</Link>
                          <button className="button button-delete-quiz" onClick={() => confirmDelete(quiz.quizId)}>Delete Quiz</button>

                        </div>
                      </div>
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

export default Quiz;
