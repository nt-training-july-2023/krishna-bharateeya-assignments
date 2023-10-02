package com.nucleusteq.assessmentPlatform.utility;

/**
 * This class contains a collection of constant strings related to- Quiz.
 */
public class QuizLoggerMessage {

    /**
     * Log message indicating that a request has been received to save a new
     * quiz.
     */
    public static final String ADD_QUIZ_REQUEST =
            "Received a request to save a new quiz.";

    /**
     * Log message indicating that a quiz has been created successfully.
     */
    public static final String QUIZ_CREATED_SUCCESSFULLY =
            "Quiz Created Successfully.";

    /**
     * Log message indicating that a request has been received to update a quiz.
     */
    public static final String UPDATE_QUIZ_REQUEST =
            "Received a request to update quiz.";

    /**
     * Log message indicating that a quiz has been updated successfully.
     */
    public static final String QUIZ_UPDATED_SUCCESSFULLY =
            "Quiz Updated Successfully.";

    /**
     * Log message indicating that a request has been received to delete a quiz.
     */
    public static final String DELETE_QUIZ_REQUEST =
            "Received a request to delete quiz.";

    /**
     * Log message indicating that a quiz has been deleted successfully.
     */
    public static final String DELETE_QUIZ_REQUEST_SUCCESSFULLY =
            "Quiz deleted Successfully.";

    /**
     * Log message indicating that a request has been received to get a quiz by
     * its ID.
     */
    public static final String GET_QUIZ_BY_ID_REQUEST =
            "Received a request to get quiz by id:";

    /**
     * Log message indicating that a request has been received to get all
     * quizzes.
     */
    public static final String GET_ALL_QUIZZES_REQUEST =
            "Received a request to get all quiz.";

    /**
     * Log message indicating that a request has been received to get all
     * questions by quiz ID.
     */
    public static final String GET_ALL_QUESTIONS_BY_QUIZ_REQUEST =
            "Received a request to get all question by quizId:";

    /**
     * Log message indicating that a list of questions has been retrieved
     * successfully.
     */
    public static final String LIST_OF_QUESTIONS_RETRIEVED_SUCCESSFULLY =
            "List of Questions Retrieved Successfully.";
    /**
     * constructor to prevent the object creation.
     */
    private QuizLoggerMessage() {
    }
}
