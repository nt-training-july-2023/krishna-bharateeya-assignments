package com.nucleusteq.assessmentPlatform.utility;

/**
 * This class contain the constant strings of question operations.
 */
public final class QuestionLoggerMessage {

    /**
     * Log message indicating that a request has been received to create a new
     * question.
     */
    public static final String ADD_QUESTION_REQUEST =
            "Received a request to create a new question.";

    /**
     * Log message indicating that a question has been created successfully.
     */
    public static final String QUESTION_CREATED_SUCCESSFULLY =
            "Question Created Successfully.";

    /**
     * Log message indicating that a request has been received to update a
     * question.
     */
    public static final String UPDATE_QUESTION_REQUEST =
            "Received a request to update a question.";

    /**
     * Log message indicating that a question has been updated successfully.
     */
    public static final String QUESTION_UPDATED_SUCCESSFULLY =
            "Question Updated Successfully.";

    /**
     * Log message indicating that a request has been received to delete a
     * question with a specific ID.
     */
    public static final String DELETE_QUESTION_REQUEST =
            "Received a request to delete a question with id:";

    /**
     * Log message indicating that a question has been deleted successfully.
     */
    public static final String QUESTION_DELETED_SUCCESSFULLY =
            "Question Deleted Successfully.";

    /**
     * Log message indicating that a request has been received to get a question
     * by its QuestionId.
     */
    public static final String GET_QUESTION_BY_ID_REQUEST =
            "Received a request to get a question by QuestionId:";

    /**
     * Log message indicating that a request has been received to retrieve all
     * questions.
     */
    public static final String GET_ALL_QUESTIONS_REQUEST =
            "Retrieving all questions.";

    /**
     * Log message indicating that a list of questions has been retrieved
     * successfully.
     */
    public static final String LIST_OF_QUESTIONS_RETRIEVED_SUCCESSFULLY =
            "List of Questions Retrieved Successfully.";
    /**
     * Constructor to prevent he object creation.
     */
    private QuestionLoggerMessage() {
    }
}
