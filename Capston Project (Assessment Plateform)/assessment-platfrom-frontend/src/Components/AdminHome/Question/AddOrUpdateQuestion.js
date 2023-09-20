import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Sidebar from '../Sidebar';
import './AddOrUpdateQuestion.css';
import UnauthorizedAccess from '../../UnauthrizedAccess/UnauthorizedAccess';
import { GetQuizzes, AddQuestions, UpdateQuestions, GetQuestionsById, GetQuizById } from '../../../ApiService/ApiService';

const AddOrUpdateQuestion = () => {
    const { questionId } = useParams();
    const { quizId } = useParams();
    console.log("fdfdfdfd", quizId);
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
    const [allFieldsFilled, setAllFieldsFilled] = useState(false);
    const [isSubmitting, setIsSubmitting] = useState(false);

    const [questionTextError, setQuestionTextError] = useState('');
    const [optionsError, setOptionsError] = useState('');
    const [correctOptionError, setCorrectOptionError] = useState('');
    const [uniqueOptionError, setuniqueOptionError] = useState('');

    useEffect(() => {
        GetQuizzes()
            .then((data) => setQuizzes(data))
            .catch((error) => console.error('Error fetching quizzes:', error));
        console.log("fsdfdsf", quizzes);

        if (quizId) {
            setSelectedQuiz(quizId);
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
                    const areOptionsFilled = Object.values(questionData.options).every((option) => option.trim() !== '');
                    setAllFieldsFilled(areOptionsFilled);

                })
                .catch((error) =>
                    console.error(
                        error.response?.data?.message ||
                        'An error occurred. Please try again.'
                    )
                );
        }
    }, [questionId]);

    const areOptionsUnique = () => {
        const optionsArray = [options.optionOne.toLowerCase(), options.optionTwo.toLowerCase(), options.optionThree.toLowerCase(), options.optionFour.toLowerCase()];
        const uniqueOptions = new Set(optionsArray);
        return optionsArray.length === uniqueOptions.size;
    };
    const validateNotEmpty = (value) => {
        if (typeof value === 'string' && !value.trim()) {
            return 'This field is required';
        }
        return '';
    };

    const handleQuizChange = async (event) => {
        const selectedQuizId = event.target.value;

        try {
            const quizObject = await GetQuizById(selectedQuizId);
            setSelectedQuiz(selectedQuizId);
            setSelectedQuizObject(quizObject);
        } catch (error) {
            console.error(
                error.response?.data?.message ||
                'An error occurred. Please try again.'
            );
        }
    };


    const handleQuestionFieldChange = (field, value) => {

        setOptions((prevOptions) => ({
            ...prevOptions,
            [field]: value,
        }));

        const areAllFieldsFilled = Object.values(options).every((option) => option.trim() !== '');
        setAllFieldsFilled(areAllFieldsFilled);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const questionTextError = validateNotEmpty(questionText);
        const optionsError = Object.values(options).some((option) => !option.trim())
            ? 'All options are required' : '';

        if (!areOptionsUnique()) {
            console.log("Options must be unique");
            return false;
        }
        const correctOptionError = validateNotEmpty(correctOption);

        setQuestionTextError(questionTextError);
        setOptionsError(optionsError);
        setCorrectOptionError(correctOptionError);

        if (questionTextError || optionsError || correctOptionError) {
            return;
        }

        const questionData = {
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
                await UpdateQuestions(questionId, questionData);
            } else {
                await AddQuestions(questionData);
            }

            navigate('/question');
        } catch (error) {
            console.error('Error:', error);
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
                                    className='add-update-question-field'
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
                            </div>
                            <div className={`form-group ${questionTextError ? 'has-error' : ''}`}>
                                <label htmlFor='question-text'>Question Text:</label>
                                <input
                                    type='text'
                                    id='question-text'
                                    className='add-update-question-field'
                                    value={questionText}
                                    onChange={(e) => setQuestionText(e.target.value)}
                                />
                                {questionTextError && <p className='error-message'>{questionTextError}</p>}
                            </div>
                            <div className={`form-group ${optionsError ? 'has-error' : ''}`}>
                                <label htmlFor='option-one'>Option One:</label>
                                <input
                                    type='text'
                                    id='option-one'
                                    className='add-update-question-field'
                                    value={options.optionOne}
                                    onChange={(e) => handleQuestionFieldChange('optionOne', e.target.value)}
                                />
                                <label htmlFor='option-two'>Option Two:</label>
                                <input
                                    type='text'
                                    id='option-two'
                                    className='add-update-question-field'
                                    value={options.optionTwo}
                                    onChange={(e) => handleQuestionFieldChange('optionTwo', e.target.value)}
                                />
                                <label htmlFor='option-three'>Option Three:</label>
                                <input
                                    type='text'
                                    id='option-three'
                                    className='add-update-question-field'
                                    value={options.optionThree}
                                    onChange={(e) => handleQuestionFieldChange('optionThree', e.target.value)}
                                />
                                <label htmlFor='option-four'>Option Four:</label>
                                <input
                                    type='text'
                                    id='option-four'
                                    className='add-update-question-field'
                                    value={options.optionFour}
                                    onChange={(e) => handleQuestionFieldChange('optionFour', e.target.value)}
                                />
                                {optionsError && <p className='error-message'>{optionsError}</p>}
                            </div>
                            <div className={`form-group ${correctOptionError ? 'has-error' : ''}`}>
                                <label htmlFor='correct-option'>Correct Option:</label>
                                <select
                                    id='correct-option'
                                    className='add-update-question-field'
                                    value={correctOption}
                                    onChange={(e) => setCorrectOption(e.target.value)}
                                    disabled={!allFieldsFilled}
                                >
                                    <option value='' disabled>-- Select an Option --</option>
                                    <option value={options.optionOne}>{options.optionOne}</option>
                                    <option value={options.optionTwo}>{options.optionTwo}</option>
                                    <option value={options.optionThree}>{options.optionThree}</option>
                                    <option value={options.optionFour}>{options.optionFour}</option>
                                </select>
                                {!allFieldsFilled && correctOptionError && (
                                    <p className='error-message'>{correctOptionError}</p>
                                )}
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
                                onClick={() => navigate('/question')}
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
