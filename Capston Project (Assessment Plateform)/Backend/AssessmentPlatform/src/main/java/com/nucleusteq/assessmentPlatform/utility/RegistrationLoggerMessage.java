package com.nucleusteq.assessmentPlatform.utility;

/**
 * This class contains the constant strings  related to user registration.
 */
public final class RegistrationLoggerMessage {

    /**
     * Log message indicating that a user has registered successfully.
     */
    public static final String REGISTERED_SUCCESSFULLY =
            "User registered successfully.";

    /**
     * Log message indicating that a login request has been received for a user.
     */
    public static final String LOGIN_REQUEST =
            "Received a login request for user: {}";

    /**
     * Log message indicating that a user has logged in successfully.
     */
    public static final String USER_LOGGED_IN_SUCCESSFULLY =
            "User {} logged in successfully.";

    /**
     * Log message indicating that a request has been received for a user's ID.
     */
    public static final String USER_ID_REQUEST =
            "Received a request for user id: {}";

    /**
     * Log message indicating that a user has been found successfully.
     */
    public static final String USER_FOUND_SUCCESSFULLY =
            "User {} found successfully.";

    /**
     * Log message indicating that a request has been received to get all users.
     */
    public static final String GET_ALL_USERS =
            "Received a request for Get all users.";

    /**
     * Log message indicating that all users have been retrieved successfully.
     */
    public static final String GET_ALL_USERS_SUCCESS =
            "All User retrieved successfully.";

    /**
     * Log message indicating that a request has been received to create a new
     * user.
     */
    public static final String SAVE_USER =
            "Received a request for create a new user.";

    /**
     * Log message indicating that a request has been received to get a user by
     * email.
     */
    public static final String USER_BY_EMAIL =
            "Received a request for get user by email.";

    /**
     * Log message indicating that a user has been retrieved successfully by
     * email.
     */
    public static final String USER_BY_EMAIL_SUCCESS =
            "User retrieved successfully.";
    /**
     * Constructor for preventing the object creation of this class.
     */
    private RegistrationLoggerMessage() {
    }

}
