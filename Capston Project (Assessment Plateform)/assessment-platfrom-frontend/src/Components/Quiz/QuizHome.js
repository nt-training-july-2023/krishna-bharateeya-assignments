import React, { useState, useEffect } from 'react';
import Sidebar from '../AdminHome/Sidebar';
import './QuizHome.css'
// import UnauthorizedAccess from '../UnauthrizedAccess/UnauthorizedAccess';
import { useParams, Link } from 'react-router-dom';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import { GetQuizzes, DeleteQuiz, LoadQuizzesForCategory } from '../../ApiService/ApiService';
const QuizHome = () => {

  const [quizzes, setQuizzes] = useState([]);
  const { categoryId } = useParams();

  useEffect(() => {
    loadQuizzes();
  }, [categoryId]);

  const loadQuizzes = async () => {
    try {
      let data = [];
      if (categoryId) {
        data = await LoadQuizzesForCategory(categoryId);
      } else {
        data = await GetQuizzes();
      }
      setQuizzes(data);

    } catch (error) {
      console.error('Error fetching questions:', error);

    }
  };

  const deleteQuiz = async (id) => {
    try {
      await DeleteQuiz(id);
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

  return (

    <div className='quiz-wrapper'>
      <div className='quiz-container'>

        <div className='quiz-sidebar-column'>
          <Sidebar />
        </div>
        <div className='quiz-column'>

          <div className="quiz-main-card">
            <div className="quiz-card-header-main">
              {userRole === 'user' ? (
                <h3>Chosse any one quiz to start the exam.</h3>
              ) : (
                <>
                  <h3>Quiz Home</h3>
                  <center>
                    
                    <Link className='add-quiz-button' to={categoryId ? `/add-quiz/${categoryId}` : '/add-quiz'}>Add Quiz</Link>
                  </center>
                </>
              )}
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
                          {userRole === 'user' ? (
                            <Link className="start-quiz-button" to={`/userQuestion/${quiz.quizId}`}>Take Test</Link>
                          ) : (
                            <>
                              <Link className="button-add-view-question" to={`/question/${quiz.quizId}`}>View Questions</Link>
                              <Link className="button-update-quiz" to={`/update-quiz/${quiz.quizId}`}>Update Quiz</Link>
                              <button className="button-delete-quiz" onClick={() => confirmDelete(quiz.quizId)}>Delete Quiz</button>
                            </>
                          )}
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

export default QuizHome;
