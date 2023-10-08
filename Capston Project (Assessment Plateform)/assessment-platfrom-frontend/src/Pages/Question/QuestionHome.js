import Sidebar from '../../Components/SideBar/Sidebar';
import './QuestionHome.css'
import React, { useState, useEffect } from "react";
import UnauthorizedAccess from '../../Components/UnauthrizedAccess/UnauthorizedAccess';
import { LoadQuestions, DeleteQuestion, GetQuestionsByQuizId } from '../../ApiService/ApiService';
import { Link, useParams } from 'react-router-dom';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import Button from '../../Components/Button/Button';
import NoDataMessage from '../../Components/NoDataMessage/NoDataMessage';

const QuestionHome = () => {

  const { quizId } = useParams();
  const [questions, setQuestions] = useState([]);
  useEffect(() => {
    loadQuestionsData();
  }, [quizId]);

  const loadQuestionsData = async () => {
    try {
      let data = [];
      if (quizId) {
        data = await GetQuestionsByQuizId(quizId);
      } else {
        data = await LoadQuestions();
      }
      setQuestions(data);

    } catch (error) {
      Swal.fire('Error', error.response.data.message, 'error');
    }
  };

  const deleteQuestion = async (questionId) => {
    try {
      await DeleteQuestion(questionId);
      loadQuestionsData();
    } catch (error) {
      Swal.fire('Error', error.response.data.message, 'error');
    }
  };

  const confirmDelete = async (questionId) => {
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
      deleteQuestion(questionId);
      Swal.fire('Deleted!', 'Your quiz has been deleted.', 'success');
    }
  };

  const userRole = localStorage.getItem('userRole');
  if (userRole !== 'admin') {
    return <UnauthorizedAccess />;
  }
  return (
    <div className='question-wrapper'>
      <div className='question-container'>
        <div className='question-sidebar-column'>
          <Sidebar />
        </div>
        <div className='question-column'>
          <div className='question-main-card'>
            <div className='question-card-header-main'>

              <h3>Question Home</h3>
              <center>
                <Link className='add-question-button' to={quizId ? `/add-question/${quizId}` : '/add-question'}>Add Question</Link>
              </center>

            </div>
            <div className="question-card-body">
              {questions.length === 0 ? (
                <NoDataMessage message="No Questions found." />
              ) : (
                <div className="question-table-wrapper">
                  <table className='question-table'>
                    <tbody className='quiz-table-content'>
                      {questions.map((question, index) => (
                        <div className="question-card" key={question.questionId}>
                          <div className="question-card-header">
                            <h3>{question.quiz.quizName}</h3>
                          </div>
                          <div className="question-card-body">

                            <h4>{question.questionText}</h4>
                            <label>
                              <input type="radio" name={`option${index}`} value="optionOne" />
                              {question.options.optionOne}

                            </label>
                            <br />
                            <label>
                              <input type="radio" name={`option${index}`} value="optionTwo" />
                              {question.options.optionTwo}
                            </label>
                            <br />
                            <label>
                              <input type="radio" name={`option${index}`} value="optionThree" />
                              {question.options.optionThree}
                            </label>
                            <br />
                            <label>
                              <input type="radio" name={`option${index}`} value="optionFour" />
                              {question.options.optionFour}
                            </label>

                            <p><strong>Correct Option:</strong> {question.options.correctOption}</p>
                            <p>Category: {question.quiz.category.categoryName}</p>

                          </div>
                          <div className="question-card-footer">

                            <Link className="update-question-button" to={`/update-question/${question.questionId}`}>Update Question</Link>
                            <Button className="delete-question-button" onClick={() => confirmDelete(question.questionId)}>Delete Question</Button>

                          </div>
                        </div>
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
};

export default QuestionHome;