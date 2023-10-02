package com.nucleusteq.assessmentPlatform.utility;

/**
 * This class contains the constant strings related to validations.
 */
final public class ValidationMessage {
    /**
     * Validation message for an empty first name field.
     */
    public static final String FIRST_NAME_EMPTY =
            "First name cannot be empty.";

    /**
     * Validation message for an empty last name field.
     */
    public static final String LAST_NAME_EMPTY =
            "Last name cannot be empty.";

    /**
     * Validation message for an empty mobile number field.
     */
    public static final String MOBILE_NUMBER_EMPTY =
            "Mobile number cannot be empty.";

    /**
     * Validation message for an invalid mobile number format.
     */
    public static final String MOBILE_NUMBER_PATTERN =
            "Mobile number should be exactly 10 digit integers.";

    /**
     * Validation message for an empty email field.
     */
    public static final String EMAIL_EMPTY =
            "Email cannot be empty.";

    /**
     * Validation message for an invalid email domain.
     */
    public static final String EMAIL_DOMAIN_INVALID =
            "Email domain is not valid.";

    /**
     * Validation message for an empty password field.
     */
    public static final String PASSWORD_EMPTY =
            "Password cannot be empty.";

    /**
     * Validation message for a password that doesn't meet the minimum length
     * requirement.
     */
    public static final String PASSWORD_SIZE =
            "Password must be at least 4 characters long.";

    /**
     * Validation message for an empty category name field.
     */
    public static final String CATEGORY_NAME_EMPTY =
            "Category name cannot be empty.";

    /**
     * Validation message for an empty category description field.
     */
    public static final String DESCRIPTION_EMPTY =
            "Description cannot be empty.";

    /**
     * Validation message for an empty quiz name field.
     */
    public static final String QUIZ_NAME_EMPTY =
            "Quiz Name cannot be empty.";

    /**
     * Validation message for an empty quiz description field.
     */
    public static final String QUIZ_DESCRIPTION_EMPTY =
            "Quiz Description cannot be empty.";

    /**
     * Validation message for a quiz time that is less than the minimum allowed
     * time.
     */
    public static final String TIME_MINIMUM =
            "Time should be a minimum of 1 minute.";

    /**
     * Validation message for a null category in a quiz.
     */
    public static final String CATEGORY_NULL =
            "Category cannot be null.";

    /**
     * Validation message for an empty question text field.
     */
    public static final String QUESTION_TEXT_EMPTY =
            "Question Text cannot be empty.";

    /**
     * Validation message for a null question options field.
     */
    public static final String OPTIONS_NULL =
            "Question Options cannot be null.";

    /**
     * Validation message for a null quiz object.
     */
    public static final String QUIZ_NULL =
            "Quiz Object cannot be null.";

    /**
     * Validation message for an empty user name field.
     */
    public static final String USER_NAME_EMPTY =
            "User Name cannot be empty.";

    /**
     * Validation message for an empty user email ID field.
     */
    public static final String USER_EMAIL_ID_EMPTY =
            "User Email Id cannot be empty.";

    /**
     * Validation message for an empty report category name field.
     */
    public static final String REPORT_CATEGORY_NAME_EMPTY =
            "Category Name cannot be empty.";

    /**
     * Validation message for an empty report quiz name field.
     */
    public static final String REPORT_QUIZ_NAME_EMPTY =
            "Quiz Name cannot be empty.";

    /**
     * Validation message for null total marks in a report.
     */
    public static final String TOTAL_MARKS_NULL =
            "Total Marks cannot be empty.";

    /**
     * Validation message for null obtained marks in a report.
     */
    public static final String MARKS_OBTAINED_NULL =
            "Obtained Marks cannot be empty.";

    /**
     * Validation message for null wrong answers in a report.
     */
    public static final String WRONG_ANSWERS_NULL =
            "Wrong Answers cannot be empty.";

    /**
     * Validation message for null total questions marks in a report.
     */
    public static final String TOTAL_QUESTIONS_NULL =
            "Total Questions Marks cannot be empty.";

    /**
     * Validation message for null attempted questions in a report.
     */
    public static final String ATTEMPTED_QUESTIONS_NULL =
            "Attempted Questions cannot be empty.";

    /**
     * Validation message for an empty date and time field in a report.
     */
    public static final String DATE_AND_TIME_EMPTY =
            "Date And Time cannot be empty.";
    /**
     * Constructor for preventing the object creation of this class.
     */
    private ValidationMessage(){ 
    }
}
