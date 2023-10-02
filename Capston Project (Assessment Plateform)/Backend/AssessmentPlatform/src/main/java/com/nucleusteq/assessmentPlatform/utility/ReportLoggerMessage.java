package com.nucleusteq.assessmentPlatform.utility;

/**
 * This class contains the constant strings related to report.
 */
public class ReportLoggerMessage {

    /**
     * Log message indicating that a request has been received to generate a
     * new report.
     */
    public static final String CREATE_REPORT_REQUEST =
            "Request received to generate a new report.";

    /**
     * Log message indicating that a report has been generated successfully.
     */
    public static final String REPORT_GENERATED_SUCCESSFULLY =
            "Report Generated Successfully.";

    /**
     * Log message indicating that a request has been received to find reports
     * by Email Id.
     */
    public static final String FIND_REPORT_BY_EMAIL_REQUEST =
            "Request received to find reports by Email Id:";

    /**
     * Log message indicating that reports have been retrieved successfully.
     */
    public static final String REPORTS_RETRIEVED_SUCCESSFULLY =
            "Reports Retrieved Successfully.";

    /**
     * Log message indicating that a request has been received to get all
     * reports.
     */
    public static final String GET_ALL_REPORTS_REQUEST =
            "Received a request to get all reports.";

    /**
    * Log message indicating that all reports have been retrieved successfully.
    */
    public static final String ALL_REPORTS_RETRIEVED_SUCCESSFULLY =
            "All Reports Retrieved Successfully.";
    /**
     * Constructor to prevent he object creation.
     */
    private ReportLoggerMessage() {
    }
}
