package com.nucleusteq.assessmentPlatform.utility;

/**
 * This class contains constant log messages.
 */
public final class CategoryLoggerMessages {

    /**
     * Log message for receiving a request to save a new category.
     */
    public static final String SAVE_CATEGORY_REQUEST =
            "Received a request to save a new category.";

    /**
     * Log message indicating successful category creation.
     */
    public static final String CATEGORY_CREATED_SUCCESSFULLY =
            "Category Created Successfully.";

    /**
     * Log message for receiving a request to get all categories.
     */
    public static final String GET_ALL_CATEGORIES_REQUEST =
            "Received a request to get all categories.";

    /**
     * Log message indicating successful retrieval of all categories.
     */
    public static final String ALL_CATEGORIES_RETRIEVED_SUCCESSFULLY =
            "All categories retrieved successfully.";

    /**
     * Log message for receiving a request to get all quizzes by Category Id.
     */
    public static final String GET_ALL_QUIZZES_BY_CATEGORY_REQUEST =
            "Received a request to get all quizzes by Category Id: ";

    /**
     * Log message for receiving a request to get a Category by Id.
     */
    public static final String GET_CATEGORY_BY_ID_REQUEST =
            "Received a request to get Category by Id: ";

    /**
     * Log message indicating successful retrieval of a category.
     */
    public static final String CATEGORY_RETRIEVED_SUCCESSFULLY =
            "Category retrieved successfully.";

    /**
     * Log message for receiving a request to update a Category.
     */
    public static final String UPDATE_CATEGORY_REQUEST =
            "Received a request to update Category for Id: ";

    /**
     * Log message indicating successful category update.
     */
    public static final String CATEGORY_UPDATED_SUCCESSFULLY =
            "Category Updated Successfully.";

    /**
     * Log message for receiving a request to delete a Category.
     */
    public static final String DELETE_CATEGORY_REQUEST =
            "Received a request to delete Category for Id: ";

    /**
     * Log message indicating successful category deletion.
     */
    public static final String CATEGORY_DELETED_SUCCESSFULLY =
            "Category Deleted Successfully.";
    /**
     * Constructor to prevent the object creation.
     */
    private CategoryLoggerMessages() {
    }
}
