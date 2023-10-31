import React, { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import './UserTest.css'
import 'sweetalert2/dist/sweetalert2.min.css';
import { GetQuestionsByQuizId, GetUserByEmail, CreateReport } from "../../ApiService/ApiService";
import NoDataMessage from "../../Components/NoDataMessage/NoDataMessage";
import DisableBackButton from "../../Components/PreventBack/DisableBackButton";
import VisibilityChangeHandler from "../../Components/VisibilityChange/VisibilityChangeHandler";

const UserTest = () => {

    const { quizId } = useParams();
    const [questions, setQuestions] = useState([]);
    const userRole = localStorage.getItem('userRole');
    const email = localStorage.getItem('email');

    const [quizName, setQuizName] = useState('');
    const [timeInSeconds, setTimeInSeconds] = useState(0);
    const [selectedAnswers, setSelectedAnswers] = useState({});
    const [submitted, setSubmitted] = useState(false);
    const [countdownComplete, setCountdownComplete] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        const handleVisibilityChange = () => {
            if (document.visibilityState === 'hidden' && !submitted) {
                setTimeout(() => {
                    handleSubmit();
                }, 500);
            }
        };
    
        document.addEventListener('visibilitychange', handleVisibilityChange);
    
        return () => {
            document.removeEventListener('visibilitychange', handleVisibilityChange);
        };
    }, []);
    


    useEffect(() => {

        const storedSelectedAnswers = localStorage.getItem("selectedAnswers");
        if (storedSelectedAnswers) {
            setSelectedAnswers(JSON.parse(storedSelectedAnswers));
        }

        const storedTimer = localStorage.getItem("timerInSeconds");
        if (storedTimer) {
            const timerInSeconds = parseInt(storedTimer);
            setTimeInSeconds(timerInSeconds);
        }

        loadQuestionsData();
    }, [quizId]);

    useEffect(() => {
        getUserDetails();
    }, [email]);

    useEffect(() => {

        const handleCountdown = () => {
            if (timeInSeconds > 0) {
                setTimeInSeconds((prevTime) => prevTime - 1);
                localStorage.setItem("timerInSeconds", (timeInSeconds - 1).toString());
            } else {
                if (!countdownComplete) {

                    setCountdownComplete(true);
                    if (questions.length > 0)
                        handleSubmit();
                }
            }
        };

        const countdownInterval = setInterval(handleCountdown, 1000);
        return () => clearInterval(countdownInterval);

    }, [timeInSeconds, countdownComplete]);

    const formattedTime = new Date(timeInSeconds * 1000).toISOString().substr(11, 8);


    useEffect(() => {

        const prevCount = parseInt(localStorage.getItem('reloadAttempts')) || 0;
        const newCount = prevCount + 1;
        localStorage.setItem('reloadAttempts', newCount.toString());
        if (localStorage.getItem('reloadAttempts') >= 3 && localStorage.getItem('reloadAttempts') <= 5) {
            Swal.fire({
                title: 'Warning',
                text: 'If you refresh the page again, the test will be submitted',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Submit Test',
                cancelButtonText: 'Cancel',
                customClass: {
                    actions: 'my-actions',
                    cancelButton: 'order-1 right-gap',
                    confirmButton: 'order-2',
                    denyButton: 'order-3',
                },
            }).then((result) => {
                if (result.isConfirmed) {
                    handleSubmit();
                }
            });
        } else if (localStorage.getItem('reloadAttempts') > 5) {
            handleSubmit();
        }
    }, []);


    const loadQuestionsData = async () => {
        try {
            const storedTimer = localStorage.getItem("timerInSeconds");
            const data = await GetQuestionsByQuizId(quizId);
            if (data.length > 0) {
                setQuestions(data);

                setQuizName(data[0].quiz.quizName);
                localStorage.setItem('quizName', data[0].quiz.quizName);
                localStorage.setItem('categoryName', data[0].quiz.category.categoryName);
                localStorage.setItem('totalQuestion', data.length);
                localStorage.setItem('totalMarks', data.length);
                const timerInMinutes = data[0].quiz.timeInMinutes;
                const timerInSeconds = timerInMinutes * 60;
                if (!storedTimer) {
                    setTimeInSeconds(timerInSeconds);
                    localStorage.setItem("timerInSeconds",
                        timerInSeconds.toString());
                }
            }
        } catch (error) {
            Swal.fire('Error', error, 'error');
        }
    };



    const getUserDetails = async () => {

        const data = await GetUserByEmail(email);
        localStorage.setItem('userName', data.firstName + " " + data.lastName);
        localStorage.setItem('userEmail', data.email);
    };

    const handleOptionChange = (questionId, selectedOption) => {

        if (!submitted) {
            setSelectedAnswers((prevSelectedAnswers) => {
                const updatedSelectedAnswers = {
                    ...prevSelectedAnswers,
                    [questionId]: selectedOption,
                };

                localStorage.setItem("selectedAnswers", JSON.stringify(updatedSelectedAnswers));

                calculateMarks(updatedSelectedAnswers);

                return updatedSelectedAnswers;
            });
        }

    };

    const calculateMarks = (updatedSelectedAnswers) => {
        let correctAnswersCount = 0;
        for (const question of questions) {
            const questionId = question.questionId;
            const selectedOption = updatedSelectedAnswers[questionId];
            if (selectedOption === question.options.correctOption) {
                correctAnswersCount += 1;
            }
        }

        const marksObtained = correctAnswersCount;
        const totalAttemptedQuestions = Object.keys(updatedSelectedAnswers).length;
        const wrongAnswers = totalAttemptedQuestions - marksObtained;

        localStorage.setItem("attemptedQuestions", totalAttemptedQuestions.toString());
        localStorage.setItem("correctAnswersCount", correctAnswersCount.toString());
        localStorage.setItem("wrongAnswers", wrongAnswers.toString());
    };


    const handleSubmit = async (e) => {

        if (e) {
            e.preventDefault();
        }
        localStorage.setItem("submitted",'true');
        setSubmitted(true);
        try {

            const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: true };
            const time = new Date().toLocaleString('en-US', options);

            const payload = {
                userName: localStorage.getItem('userName'),
                userEmailId: localStorage.getItem('userEmail'),
                categoryName: localStorage.getItem('categoryName'),
                quizName: localStorage.getItem('quizName'),
                totalMarks: localStorage.getItem('totalQuestion'),
                marksObtained: localStorage.getItem('correctAnswersCount'),
                wrongAnswers: localStorage.getItem('wrongAnswers'),
                totalQuestions: localStorage.getItem('totalMarks'),
                attemptedQuestions: localStorage.getItem('attemptedQuestions'),
                dateAndTime: time,
            };
            localStorage.removeItem("timerInSeconds");
            localStorage.removeItem("selectedAnswers");
            localStorage.removeItem("userName");
            localStorage.removeItem("categoryName");
            localStorage.removeItem("quizName");
            localStorage.removeItem("reloadAttempts");
            localStorage.removeItem("totalQuestion");
            localStorage.removeItem("totalMarks");
            localStorage.removeItem("userEmail");
            localStorage.removeItem("correctAnswersCount");
            localStorage.removeItem("wrongAnswers");
            localStorage.removeItem("attemptedQuestions");

            const result = await CreateReport(payload);
            
            navigate('/userHome');
            Swal.fire('Answers Submitted!', 'Your answers have been submitted successfully.', 'success');
        } catch (error) {
            Swal.fire('Error', 'An error occurred while submitting your answers.', 'error');
        }
    };

    const handleConfirmSubmit = () => {
        Swal.fire({
            title: 'Confirmation',
            text: 'Are you sure you want to submit the test?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Submit',
            cancelButtonText: 'Cancel',
        }).then((result) => {
            if (result.isConfirmed) {
                handleSubmit();
            }
        });
    };


    if (userRole !== 'user') {
        return <UnauthorizedAccess />;
    }

    return (
        <div className='user-test-wrapper'>
            <DisableBackButton />
            <div className='user-test-card-header-main'>
                <h2>Time Remaining: {formattedTime}</h2>
                <h2>{quizName}</h2>
                <h3>Attempted Questions : {localStorage.getItem('attemptedQuestions')||0} / {localStorage.getItem('totalQuestion')}</h3>
                <h3>
                    {questions.length > 0 ? (
                        <button className="submit-answers-button" onClick={handleConfirmSubmit} disabled={submitted}>
                            Submit Test
                        </button>
                    ) : (
                        <Link className="exit-button" to="/userHome">Exit</Link>
                    )}
                </h3>
            </div>
            <div className="user-test-card-body">
                <form onSubmit={handleSubmit} disabled={submitted}>
                    <div className="user-test-table-wrapper">
                        <table className='user-test-table'>
                            {questions.length === 0 ? (
                                <NoDataMessage message="No Question found." />
                            ) : (
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
                            )}
                        </table>
                    </div>
                </form>
            </div>
        </div>
    );
};
export default UserTest;
