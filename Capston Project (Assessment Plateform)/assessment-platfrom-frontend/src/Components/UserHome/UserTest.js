import React, { useState, useEffect } from "react";
import { Link, useParams } from 'react-router-dom';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import { GetQuestionsByQuizId, GetUserByEmail, CreateReport } from "../../ApiService/ApiService";
const UserTest = () => {

    const { quizId } = useParams();
    const [questions, setQuestions] = useState([]);
    const userRole = localStorage.getItem('userRole');
    const email = localStorage.getItem('email');

    const [totalQuestions, setTotalQuestions] = useState(0);
    const [totalMarks, setTotalMarks] = useState(0);
    // const [correctAnswers, setCorrectAnswers] = useState(0);
    // const [wrongAnswers, setWrongAnswers] = useState(0);
    const [userName, setUserName] = useState('');
    const [userEmailId, setUserEmailId] = useState('');
    const [quizName, setQuizName] = useState('');
    const [quizTime, setQuizTime] = useState(0);
    const [categoryName, setCategoryName] = useState('');
    const [remainingTime, setRemainingTime] = useState(0);

    const [selectedAnswers, setSelectedAnswers] = useState({});
    const [submitted, setSubmitted] = useState(false);
    useEffect(() => {
        loadQuestionsData();
    }, [quizId]);

    const loadQuestionsData = async () => {
        try {

            const data = await GetQuestionsByQuizId(quizId);
            setQuestions(data);

            setTotalQuestions(data.length);
            setTotalMarks(data.length);
            setCategoryName(data[0].quiz.category.categoryName);
            setQuizName(data[0].quiz.quizName);
            setQuizTime(data[0].quiz.timeInMinutes);
        } catch (error) {
            console.error('Error fetching questions:', error);

        }
    };

    useEffect(() => {

        getUserDetails();
    }, [email]);

    const getUserDetails = async () => {

        const data = await GetUserByEmail(email);
        setUserName(data.firstName + data.lastName);
        setUserEmailId(data.email)
    };

    useEffect(() => {

        setRemainingTime(quizTime * 60);
        const timer = setInterval(() => {
            setRemainingTime((prevTime) => {
                if (prevTime > 0) {
                    return prevTime - 1;
                } else {

                    handleSubmit();
                    clearInterval(timer);
                    return 0;
                }
            });
        }, 1000);

        return () => clearInterval(timer);
    }, [quizTime]);




    const handleOptionChange = (questionId, selectedOption) => {
        if (!submitted) {
            setSelectedAnswers((prevSelectedAnswers) => ({
                ...prevSelectedAnswers,
                [questionId]: selectedOption,
            }));
        }
    };

    const handleSubmit = async () => {
        try {
            setSubmitted(true);
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
            console.log("User Answers:", result);
            Swal.fire('Answers Submitted!', 'Your answers have been submitted successfully.', 'success');
        } catch (error) {
            console.error('Error submitting answers:', error);
            Swal.fire('Error', 'An error occurred while submitting your answers.', 'error');
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
                        <h3> Subject Name {quizName}</h3>
                        <h2>Time Remaining: {Math.floor(remainingTime / 60)}:{remainingTime % 60}</h2>
                    </div>
                    <div className="question-card-body">
                        <div className="question-table-wrapper">
                            <table className='question-table'>
                                <tbody className='quiz-table-content'>
                                    {questions.map((question, index) => (
                                        <div className="question-card" key={question.questionId}>
                                            <div className="question-card-body">
                                                <h4>{index + 1}. {question.questionText}</h4>
                                                <label>
                                                    <input
                                                        type="radio"
                                                        name={`option${index}`}
                                                        value="optionOne"
                                                        checked={selectedAnswers[question.questionId] === question.options.optionOne}
                                                        onChange={() => handleOptionChange(question.questionId, question.options.optionOne)}
                                                    />
                                                    {question.options.optionOne}
                                                </label>
                                                <br />
                                                <label>
                                                    <input
                                                        type="radio"
                                                        name={`option${index}`}
                                                        value="optionTwo"
                                                        checked={selectedAnswers[question.questionId] === question.options.optionTwo}
                                                        onChange={() => handleOptionChange(question.questionId, question.options.optionTwo)}
                                                    />
                                                    {question.options.optionTwo}
                                                </label>
                                                <br />
                                                <label>
                                                    <input
                                                        type="radio"
                                                        name={`option${index}`}
                                                        value="optionThree"
                                                        checked={selectedAnswers[question.questionId] === question.options.optionThree}
                                                        onChange={() => handleOptionChange(question.questionId, question.options.optionThree)}
                                                    />
                                                    {question.options.optionThree}
                                                </label>
                                                <br />
                                                <label>
                                                    <input
                                                        type="radio"
                                                        name={`option${index}`}
                                                        value="optionFour"
                                                        checked={selectedAnswers[question.questionId] === question.options.optionFour}
                                                        onChange={() => handleOptionChange(question.questionId, question.options.optionFour)}
                                                    />
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
