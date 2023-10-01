import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Sidebar from '../Sidebar';
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import './AddOrUpdateQuestion.css';
import UnauthorizedAccess from '../../UnauthrizedAccess/UnauthorizedAccess';
import { GetQuizzes, AddQuestions, UpdateQuestions, GetQuestionsById, GetQuizById } from '../../../ApiService/ApiService';
const AddOrUpdateQuestion = () => {
    const { questionId } = useParams();
    const { quizId } = useParams();
    const navigate = useNavigate();

    const [quizzes, setQuizzes] = useState([]);
    const [selectedQuiz, setSelectedQuiz] = useState('');
    const [selectedQuizObject, setSelectedQuizObject] = useState(null);
    const [questionText, setQuestionText] = useState('');
    const [options, setOptions] = useState({
        optionOne: '',
        optionTwo: '',
        optionThree: '',
        optionFour: '',
    });
    const [correctOption, setCorrectOption] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);

    const [fieldErrors, setFieldErrors] = useState({
        selectedQuiz: '',
        questionText: '',
        optionOne: '',
        optionTwo: '',
        optionThree: '',
        optionFour: '',
        correctOption: '',
    });

    useEffect(() => {

        GetQuizzes()
            .then((data) => setQuizzes(data))
            .catch((error) => console.error('Error fetching quizzes:', error));

        if (quizId) {
            setSelectedQuiz(quizId);
            GetQuizById(quizId)
            .then((response) => {
                const quizData = response;
                setSelectedQuizObject(quizData);
            })
            .catch((error) =>
                console.error(
                    error.response?.data?.message ||
                    'An error occurred. Please try again.'
                )
            );
        }

        if (questionId) {
            GetQuestionsById(questionId)
                .then((response) => {
                    const questionData = response;
                    setSelectedQuiz(questionData.quiz.quizId);
                    setQuestionText(questionData.questionText);
                    setOptions({
                        optionOne: questionData.options.optionOne,
                        optionTwo: questionData.options.optionTwo,
                        optionThree: questionData.options.optionThree,
                        optionFour: questionData.options.optionFour,
                    });
                    setCorrectOption(questionData.options.correctOption);
                    setSelectedQuizObject(questionData.quiz);
                })
                .catch((error) =>
                    console.error(
                        error.response?.data?.message ||
                        'An error occurred. Please try again.'
                    )
                );
        }
    }, [questionId, quizId]);

    const areOptionsUnique = () => {
        const optionsArray = [
            options.optionOne.toLowerCase(),
            options.optionTwo.toLowerCase(),
            options.optionThree.toLowerCase(),
            options.optionFour.toLowerCase(),
        ];
        const uniqueOptions = new Set(optionsArray);
        return optionsArray.length === uniqueOptions.size;
    };

    const validateNotEmpty = (value, field) => {
        if (typeof value === 'string' && !value.trim()) {
            setFieldErrors((prevErrors) => ({
                ...prevErrors,
                [field]: 'This field is required',
            }));
            return false;
        }
        setFieldErrors((prevErrors) => ({
            ...prevErrors,
            [field]: '',
        }));
        return true;
    };

    const handleQuizChange = async (event) => {
        const selectedQuizId = event.target.value;

        try {
            const quizObject = await GetQuizById(selectedQuizId);
            setSelectedQuiz(selectedQuizId);
            setSelectedQuizObject(quizObject);
            setFieldErrors((prevErrors) => ({
                ...prevErrors,
                selectedQuiz: '',
            }));
        } catch (error) {
            console.error(
                error.response?.data?.message ||
                'An error occurred. Please try again.'
            );
            setFieldErrors((prevErrors) => ({
                ...prevErrors,
                selectedQuiz: 'An error occurred. Please try again.',
            }));
        }
    };

    const handleQuestionTextChange = (event) => {
        const value = event.target.value;
        setQuestionText(value);
        validateNotEmpty(value, 'questionText');
    };

    const handleOptionChange = (field, value) => {
        setOptions((prevOptions) => ({
            ...prevOptions,
            [field]: value,
        }));
        validateNotEmpty(value, field);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const isQuizValid = validateNotEmpty(selectedQuiz, 'selectedQuiz');
        const isQuestionTextValid = validateNotEmpty(questionText, 'questionText');
        const isOptionOneValid = validateNotEmpty(options.optionOne, 'optionOne');
        const isOptionTwoValid = validateNotEmpty(options.optionTwo, 'optionTwo');
        const isOptionThreeValid = validateNotEmpty(options.optionThree, 'optionThree');
        const isOptionFourValid = validateNotEmpty(options.optionFour, 'optionFour');
        const isCorrectOptionValid = validateNotEmpty(correctOption, 'correctOption');
        const areOptionsUniqueValid = areOptionsUnique();
        if (isQuizValid && isQuestionTextValid && isOptionOneValid && isOptionTwoValid
            && isOptionThreeValid && isOptionFourValid && isCorrectOptionValid && areOptionsUniqueValid) {

            const payload = {
                quiz: selectedQuizObject,
                questionText: questionText,
                options: {
                    optionOne: options.optionOne,
                    optionTwo: options.optionTwo,
                    optionThree: options.optionThree,
                    optionFour: options.optionFour,
                    correctOption: correctOption,
                },
            };

            try {
                if (questionId) {
                    console.log('Updating question with questionId:', questionId);
                    await UpdateQuestions(questionId, payload);
                    toast.success("Question Updated Successfully.");
                    navigate(`/question/${quizId}`)
                } else {
                    await AddQuestions(payload);
                    toast.success("Question Added Successfully.");
                    navigate('/question');
                }

            } catch (error) {
                console.error('Error:', error);
            }
        } else {
            if (!areOptionsUniqueValid) {
                setFieldErrors((prevErrors) => ({
                    ...prevErrors,
                    optionOne: 'Options must be unique',
                    optionTwo: 'Options must be unique',
                    optionThree: 'Options must be unique',
                    optionFour: 'Options must be unique',
                }));
            }
        }
    };

    const userRole = localStorage.getItem('userRole');
    if (userRole !== 'admin') {
        return <UnauthorizedAccess />;
    }

    return (
        <div className='add-update-question-wrapper'>
            <div className='add-update-question-container'>
                <div className='add-update-question-sidebar-column'>
                    <Sidebar />
                </div>
                <div className='add-update-question-column'>
                    <form onSubmit={handleSubmit} className='add-update-question-form'>
                        <h2 className='add-update-question-title'>
                            {questionId ? 'Update Question' : 'Add Question'}
                        </h2>
                        <div className='question-from-container'>
                            <div className='form-groups'>
                                <label htmlFor='category-select'>Select Quiz:</label>
                                <select
                                    id='quiz-select'
                                    className={`add-update-question-field ${fieldErrors.selectedQuiz ? 'question-error-field' : ''}`}
                                    value={selectedQuiz}
                                    onChange={handleQuizChange}
                                >
                                    <option value='' disabled>-- Select a Quiz --</option>
                                    {quizzes.map((quiz) => (
                                        <option key={quiz.quizId} value={quiz.quizId}>
                                            {quiz.quizName}
                                        </option>
                                    ))}
                                </select>
                                {fieldErrors.selectedQuiz && (
                                    <p className='question-error-message'>{fieldErrors.selectedQuiz}</p>
                                )}
                            </div>
                            <div className={`form-group ${fieldErrors.questionText ? 'has-error' : ''}`}>
                                <label htmlFor='question-text'>Question Text:</label>
                                <input
                                    type='text'
                                    id='question-text'
                                    className={`add-update-question-field ${fieldErrors.questionText ? 'question-error-field' : ''}`}
                                    value={questionText}
                                    onChange={handleQuestionTextChange}
                                />
                                {fieldErrors.questionText && <p className='question-error-message'>{fieldErrors.questionText}</p>}
                            </div>
                            <div className={`form-group ${fieldErrors.optionOne ? 'has-error' : ''}`}>
                                <label htmlFor='option-one'>Option One:</label>
                                <input
                                    type='text'
                                    id='option-one'
                                    className={`add-update-question-field ${fieldErrors.optionOne ? 'question-error-field' : ''}`}
                                    value={options.optionOne}
                                    onChange={(e) => handleOptionChange('optionOne', e.target.value)}
                                />
                                {fieldErrors.optionOne && <p className='question-error-message'>{fieldErrors.optionOne}</p>}
                            </div>
                            <div className={`form-group ${fieldErrors.optionTwo ? 'has-error' : ''}`}>
                                <label htmlFor='option-two'>Option Two:</label>
                                <input
                                    type='text'
                                    id='option-two'
                                    className={`add-update-question-field ${fieldErrors.optionTwo ? 'question-error-field' : ''}`}
                                    value={options.optionTwo}
                                    onChange={(e) => handleOptionChange('optionTwo', e.target.value)}
                                />
                                {fieldErrors.optionTwo && <p className='question-error-message'>{fieldErrors.optionTwo}</p>}
                            </div>
                            <div className={`form-group ${fieldErrors.optionThree ? 'has-error' : ''}`}>
                                <label htmlFor='option-three'>Option Three:</label>
                                <input
                                    type='text'
                                    id='option-three'
                                    className={`add-update-question-field ${fieldErrors.optionThree ? 'question-error-field' : ''}`}
                                    value={options.optionThree}
                                    onChange={(e) => handleOptionChange('optionThree', e.target.value)}
                                />
                                {fieldErrors.optionThree && <p className='question-error-message'>{fieldErrors.optionThree}</p>}
                            </div>
                            <div className={`form-group ${fieldErrors.optionFour ? 'has-error' : ''}`}>
                                <label htmlFor='option-four'>Option Four:</label>
                                <input
                                    type='text'
                                    id='option-four'
                                    className={`add-update-question-field ${fieldErrors.optionFour ? 'question-error-field' : ''}`}
                                    value={options.optionFour}
                                    onChange={(e) => handleOptionChange('optionFour', e.target.value)}
                                />
                                {fieldErrors.optionFour && <p className='question-error-message'>{fieldErrors.optionFour}</p>}
                            </div>
                            <div className={`form-group ${fieldErrors.correctOption ? 'has-error' : ''}`}>
                                <label htmlFor='correct-option'>Correct Option:</label>
                                <select
                                    id='correct-option'
                                    className={`add-update-question-field ${fieldErrors.correctOption ? 'question-error-field' : ''}`}
                                    value={correctOption}
                                    onChange={(e) => setCorrectOption(e.target.value)}
                                    disabled={!questionText || !options.optionOne || !options.optionTwo || !options.optionThree || !options.optionFour}
                                >
                                    <option value='' disabled>-- Select an Option --</option>
                                    <option value={options.optionOne}>{options.optionOne}</option>
                                    <option value={options.optionTwo}>{options.optionTwo}</option>
                                    <option value={options.optionThree}>{options.optionThree}</option>
                                    <option value={options.optionFour}>{options.optionFour}</option>
                                </select>
                                {!questionText || !options.optionOne || !options.optionTwo || !options.optionThree || !options.optionFour
                                    ? <p className='question-error-message'>{fieldErrors.correctOption}</p>
                                    : null
                                }
                            </div>
                        </div>
                        <div className='add-update-button-container'>
                            <button
                                type='submit'
                                className='add-question-submit-button'
                                disabled={isSubmitting}
                            >
                                {isSubmitting ? (questionId ? 'Updating...' : 'Adding...') : 'Submit'}
                            </button>
                            <button
                                type='button'
                                className='add-queston-cancel-button'
                                // onClick={() => navigate('/question')}
                                onClick={() => navigate(quizId ? `/question/${quizId}` : '/question')}
                            >
                                Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AddOrUpdateQuestion;
