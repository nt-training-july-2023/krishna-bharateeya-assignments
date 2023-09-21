// import './QuestionHome.css'
import React, { useState, useEffect } from "react";
// import UnauthorizedAccess from '../../UnauthrizedAccess/UnauthorizedAccess';
import { Link, useParams } from 'react-router-dom';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import { GetQuestionsByQuizId, GetUserByEmail } from "../../ApiService/ApiService";
const UserTest = () => {

    const { quizId } = useParams();
    const [questions, setQuestions] = useState([]);
    const userRole = localStorage.getItem('userRole');
    const email = localStorage.getItem('email');

    const [totalQuestions, setTotalQuestions] = useState(0);
    const [totalMarks, setTotalMarks] = useState(0);
    const [correctAnswers, setCorrectAnswers] = useState(0);
    const [wrongAnswers, setWrongAnswers] = useState(0);
    const [attemptedQuestions, setAttemptedQuestions] = useState(0);
    const [userName, setUserName] = useState('');
    const [userEmailId, setUserEmailId] = useState('');
    const [quizName, setQuizName] = useState('');
    const [categoryName, setCategoryName] = useState('');
    const [dateAndTime, setDateAndTime] = useState('');

    useEffect(() => {
        loadQuestionsData();
    }, [quizId]);

    // useEffect(() => {
    //     getUserDetails();
    // }, []); 
    // setUserEmailId(localStorage.getItem('email'));

    //   const getUserDetails = async () => {
    //     const result = await GetUserByEmail(email);
    //     setUserName(result.firstName +result.lastName);
    //     // setUserEmailId(result.email);

    //     console.log("name :",userName);
    //     // console.log("Email :",userEmailId);
    //   };

    useEffect(() => {
        // Move this logic inside the useEffect
        const getUserDetails = async () => {
            const result = await GetUserByEmail(email);
            setUserName();
            console.log("eshan :",userName)
            console.log("user Name :"+result.firstName + result.lastName)
        };

        getUserDetails(); // Call the function once inside the useEffect
    }, [email]);
    const loadQuestionsData = async () => {
        try {

            const data = await GetQuestionsByQuizId(quizId);

            setQuestions(data);
            console.log("fdfdsfsdfsdfsd", data[0].quiz)
            console.log("fdfdsfsdfsdfsd", data[0].quiz.quizName)
            const quiz=data[0].quiz.quizName;
            setTotalQuestions(data.length);
            setTotalMarks(data.length);
            setCategoryName(quiz.category.categoryName);
            setQuizName(quiz);

            console.log("total Question :", data.length);
            console.log("Total Mark :", data.length);
            // console.log("Category Name :", data[0].quiz.category.categoryName);
            console.log("Category Name :",categoryName);
            console.log("quiz Name", data[0].quiz.quizName)
        } catch (error) {
            console.error('Error fetching questions:', error);

        }
    };

    const handleOptionChange = (index, selectedOption) => {

        setAttemptedQuestions(attemptedQuestions + 1);

        if (selectedOption === questions[index].options.correctOption) {
            setCorrectAnswers(correctAnswers + 1);
        }
        if (selectedOption !== questions[index].options.correctOption) {
            setWrongAnswers(wrongAnswers + 1);
        }

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

    if (userRole !== 'user') {
        return <UnauthorizedAccess />;
    }
    return (
        <div className='question-wrapper'>
            <div className='question-container'>
                <div className='question-main-card'>
                    <div className='question-card-header-main'>
                        <h3>Please attempt the Question</h3>
                        <h2>Time : 90 Minuts </h2>
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
                                            </div>

                                        </div>
                                    ))}
                                </tbody>
                            </table>
                            <div className="question-card-footer">
                                <button className="submit-answers-button" onClick={handleSubmit}>Submit Answers</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
};

export default UserTest;
