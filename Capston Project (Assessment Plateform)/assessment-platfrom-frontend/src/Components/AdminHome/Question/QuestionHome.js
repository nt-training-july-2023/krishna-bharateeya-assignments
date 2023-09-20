import Sidebar from '../Sidebar'
import './QuestionHome.css'
import React, { useState, useEffect } from "react";
import UnauthorizedAccess from '../../UnauthrizedAccess/UnauthorizedAccess';
import { LoadQuestions, DeleteQuestion, GetQuestionsByQuizId } from '../../../ApiService/ApiService';
import { Link, useParams } from 'react-router-dom';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

const QuestionHome = () => {

  const { quizId } = useParams();
  const [questions, setQuestions] = useState([]);
  const userRole = localStorage.getItem('userRole');

  useEffect(() => {
    loadQuestionsData();
  }, [quizId]);

  const loadQuestionsData = async () => {
    try {
      let data = [];
      if (quizId) {
        data = await GetQuestionsByQuizId(quizId);
      } else {
        if (userRole !== 'admin') {
          // navigate(<UnauthorizedAccess />); 
          // return;
          return <UnauthorizedAccess />;
        } else {
          data = await LoadQuestions();
        }
      }
      setQuestions(data);

    } catch (error) {
      console.error('Error fetching questions:', error);

    }
  };

  const deleteQuestion = async (questionId) => {
    try {
      await DeleteQuestion(questionId);
      loadQuestionsData();
    } catch (error) {
      console.log(error.response || 'An error occurred. Please try again.');
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

  const handleOptionChange = (index, selectedOption) => {
    setUserAnswers({
      ...userAnswers,
      [index]: selectedOption,
    });
  };

  const handleSubmit = async () => {
    try {

      const answers = Object.values(userAnswers);

      console.log("User Answers:", answers);
      Swal.fire('Answers Submitted!', 'Your answers have been submitted successfully.', 'success');
    } catch (error) {
      console.error('Error submitting answers:', error);
      Swal.fire('Error', 'An error occurred while submitting your answers. Please try again.', 'error');
    }
  };
  return (
    <div className='question-wrapper'>
      <div className='question-container'>
        <div className='question-sidebar-column'>
          <Sidebar />
        </div>
        <div className='question-column'>
          <div className='question-main-card'>
            <div className='question-card-header-main'>
              {userRole === 'user' ? (
                <>
                  <h3>Please attempt the Question</h3>
                  <h2>Time : 90 Minuts </h2>
                </>
              ) : (
                <>
                  <h3>Question Home</h3>
                  <center>
                    <Link className='add-question-button' to={quizId ? `/add-question/${quizId}` : '/add-question'}>Add Question</Link>
                  </center>
                </>
              )}
            </div>
            <div className="question-card-body">
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
                          {userRole === 'admin' ? (
                            <>
                              <p><strong>Correct Option:</strong> {question.options.correctOption}</p>
                              <p>Category: {question.quiz.category.categoryName}</p>
                            </>
                          ) : ('')}
                        </div>
                        <div className="question-card-footer">
                          {userRole === 'admin' ? (
                            <>
                              <Link className="update-question-button" to={`/update-question/${question.questionId}`}>Update Question</Link>
                              <button className="delete-question-button" onClick={() => confirmDelete(question.questionId)}>Delete Question</button>
                            </>
                          ) : ('')}
                        </div>
                      </div>
                    ))}
                  </tbody>
                </table>
                <div className="question-card-footer">
                  {userRole === 'user' ? (
                    <button className="submit-answers-button" onClick={handleSubmit}>Submit Answers</button>
                  ) : ('')}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default QuestionHome;
