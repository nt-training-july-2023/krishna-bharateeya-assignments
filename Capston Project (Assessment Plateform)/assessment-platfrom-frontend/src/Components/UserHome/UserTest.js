import React, { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import './UserTest.css'
import 'sweetalert2/dist/sweetalert2.min.css';
import { GetQuestionsByQuizId, GetUserByEmail, CreateReport } from "../../ApiService/ApiService";
const UserTest = () => {

    const { quizId } = useParams();
    const [questions, setQuestions] = useState([]);
    const userRole = localStorage.getItem('userRole');
    const email = localStorage.getItem('email');

    const [totalQuestions, setTotalQuestions] = useState(0);
    const [totalMarks, setTotalMarks] = useState(0);
    const [userName, setUserName] = useState('');
    const [userEmailId, setUserEmailId] = useState('');
    const [quizName, setQuizName] = useState('');
    const [categoryName, setCategoryName] = useState('');
    const [timeInSeconds, setTimeInSeconds] = useState(0);
    const [selectedAnswers, setSelectedAnswers] = useState({});
    const [submitted, setSubmitted] = useState(false);
    const [countdownComplete, setCountdownComplete] = useState(false);
    const [reloadAttempts, setReloadAttempts] = useState(0);
    const [isRunning, setIsRunning] = useState(true);

    const navigate = useNavigate();

    useEffect(() => {
        loadQuestionsData();
        showUserInformation();
    }, [quizId]);

    useEffect(() => {

        getUserDetails();
    }, [email]);

    useEffect(() => {

        const handleCountdown = () => {
            if (timeInSeconds > 0) {
                setTimeInSeconds((prevTime) => prevTime - 1);
            } else {
                // console.log("remaining time :",timeInSeconds);
                // handleSubmit();
                if (!countdownComplete) {

                    setCountdownComplete(true);
                    console.log("remaining time:", timeInSeconds);
                    handleSubmit();
                }
            }
        };

        const countdownInterval = setInterval(handleCountdown, 1000);
        return () => clearInterval(countdownInterval);

    }, [timeInSeconds, countdownComplete]);

    const formattedTime = new Date(timeInSeconds * 1000).toISOString().substr(11, 8);

    useEffect(() => {
        const confirmBeforeUnload = (e) => {
            if (!countdownComplete) {
                if (reloadAttempts < 2) {
                    e.preventDefault();
                    e.returnValue = "Your have unsaved changes. Are you sure you want to leave?";
                    setReloadAttempts(reloadAttempts + 1);
                } else {
                    handleSubmit();
                    e.preventDefault();
                }
            }
        };

        window.addEventListener("beforeunload", confirmBeforeUnload);

        return () => {
            window.removeEventListener("beforeunload", confirmBeforeUnload);
        };
    }, [reloadAttempts, countdownComplete]);


    const loadQuestionsData = async () => {
        try {

            const data = await GetQuestionsByQuizId(quizId);
            setQuestions(data);

            setTotalQuestions(data.length);
            setTotalMarks(data.length);
            setCategoryName(data[0].quiz.category.categoryName);
            setQuizName(data[0].quiz.quizName);

            const timerInMinutes = data[0].quiz.timeInMinutes;
            const timerInSeconds = timerInMinutes * 60;
            setTimeInSeconds(timerInSeconds);
        } catch (error) {
            console.error('Error fetching questions:', error);

        }
    };



    const getUserDetails = async () => {

        const data = await GetUserByEmail(email);
        setUserName(data.firstName + data.lastName);
        setUserEmailId(data.email)
    };


    const handleOptionChange = (questionId, selectedOption) => {
        if (!submitted) {
            setSelectedAnswers((prevSelectedAnswers) => ({
                ...prevSelectedAnswers,
                [questionId]: selectedOption,
            }));
        }
    };

    const handleSubmit = async (e) => {

        if (e) {
            e.preventDefault();
        }

        setSubmitted(true);
        try {
            let correctAnswersCount = 0;

            for (const question of questions) {
                const questionId = question.questionId;
                const selectedOption = selectedAnswers[questionId];
                if (selectedOption === question.options.correctOption) {
                    correctAnswersCount++;
                }
            }

            const marksObtained = correctAnswersCount;
            const totalAttemptedQuestions = (Object.keys(selectedAnswers).length);
            const wrongAnswers = totalAttemptedQuestions - marksObtained;

            const payload = {
                userName,
                userEmailId,
                categoryName,
                quizName,
                totalMarks,
                marksObtained,
                wrongAnswers,
                totalQuestions,
                attemptedQuestions: totalAttemptedQuestions,
                dateAndTime: new Date().toLocaleString(),
            };

            const result = await CreateReport(payload);
            navigate('/categoryHome');
            console.log("User Answers:", result);
            Swal.fire('Answers Submitted!', 'Your answers have been submitted successfully.', 'success');
        } catch (error) {
            console.error('Error submitting answers:', error);
            Swal.fire('Error', 'An error occurred while submitting your answers.', 'error');
        }
    };



    const showUserInformation = () => {
        Swal.fire({
            title: `Information & Instructions`,
            width: 900,
            padding: '3em',
            background: '#f5f5f5',
            color: 'black',
            backdrop: `
            rgba(0,0,123,0.4)
            left top
            no-repeat
          `,

            html: `
            <div style="text-align: left; font-family:sans-serif ;line-height: 1.6">
            
              <ul>
              <li>Please do not reload  the page.</li>
              <li>The examination will comprise of Objective type Multiple Choice Questions.</li>
              <li>The duration of examination, will be different based on the course.</li>
              <li>There will be <Strong>NO NEGATIVE MARKING</Strong> for the wrong answers.</li>
              <li>The Time remaining is shown in the Right Top Corner of the screen.</li>
              <li>Mark your answers before the deadline.</li>
              </ul>
              
            </div>
          `
        });
    }
    if (userRole !== 'user') {
        return <UnauthorizedAccess />;
    }

    return (
        <div className='user-test-wrapper'>
            <div className='user-test-container'>
                <div className='user-test-main-card'>
                    <div className='user-test-card-header-main'>
                        <h2>Time Remaining: {formattedTime}</h2>
                        {quizName}
                        <h3>
                            <button className="submit-answers-button" onClick={handleSubmit} disabled={submitted}>Submit Answers</button>
                        </h3>
                    </div>
                    <div className="user-test-card-body">
                        <form onSubmit={handleSubmit} disabled={submitted}>
                            <div className="user-test-table-wrapper">
                                <table className='user-test-table'>
                                    <tbody className='user-test-table-content'>
                                        {questions.map((question, index) => (
                                            <div className="user-test-card" key={question.questionId}>
                                                <div className="user-test-card-body">
                                                    <h4>{index + 1}. {question.questionText}</h4>
                                                    <label>
                                                        <input
                                                            type="radio"
                                                            name={`option${index}`}
                                                            value="optionOne"
                                                            onChange={() => handleOptionChange(question.questionId, question.options.optionOne)}
                                                            checked={selectedAnswers[question.questionId] === question.options.optionOne}
                                                        />
                                                        {question.options.optionOne}
                                                    </label>
                                                    <br />
                                                    <label>
                                                        <input
                                                            type="radio"
                                                            name={`option${index}`}
                                                            value="optionTwo"
                                                            onChange={() => handleOptionChange(question.questionId, question.options.optionTwo)}
                                                            checked={selectedAnswers[question.questionId] === question.options.optionTwo}
                                                        />
                                                        {question.options.optionTwo}
                                                    </label>
                                                    <br />
                                                    <label>
                                                        <input
                                                            type="radio"
                                                            name={`option${index}`}
                                                            value="optionThree"
                                                            onChange={() => handleOptionChange(question.questionId, question.options.optionThree)}
                                                            checked={selectedAnswers[question.questionId] === question.options.optionThree}
                                                        />
                                                        {question.options.optionThree}
                                                    </label>
                                                    <br />
                                                    <label>
                                                        <input
                                                            type="radio"
                                                            name={`option${index}`}
                                                            value="optionFour"
                                                            onChange={() => handleOptionChange(question.questionId, question.options.optionFour)}
                                                            checked={selectedAnswers[question.questionId] === question.options.optionFour}
                                                        />
                                                        {question.options.optionFour}
                                                    </label>
                                                </div>
                                            </div>
                                        ))}
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};
export default UserTest;
