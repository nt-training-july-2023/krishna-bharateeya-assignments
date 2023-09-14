package com.nucleusteq.assessmentPlatform.entity;

/**
 * Represents the answer options for a question.
 */
public class QuestionOptions {

    /**
     * The first answer option.
     */
    private String optionOne;

    /**
     * The second answer option.
     */
    private String optionTwo;

    /**
     * The third answer option.
     */
    private String optionThree;

    /**
     * The fourth answer option.
     */
    private String optionFour;

    /**
     * The correct answer option.
     */
    private String correctOption;

    /**
     * Constructs a new QuestionOption object with the provided answer options
     * and correct option.
     *
     * @param opOne      The first answer option.
     * @param opTwo      The second answer option.
     * @param opThree    The third answer option.
     * @param opFour     The fourth answer option.
     * @param corrOption The correct answer option.
     */
    public QuestionOptions(final String opOne, final String opTwo,
            final String opThree, final String opFour,
            final String corrOption) {
        optionOne = opOne;
        optionTwo = opTwo;
        optionThree = opThree;
        optionFour = opFour;
        correctOption = corrOption;
    }
    /**
     * No Argument Constructor.
     */
    public QuestionOptions() {
        super();
    }
    /**
     * Gets the first answer option.
     *
     * @return The first answer option.
     */
    public final String getOptionOne() {
        return optionOne;
    }

    /**
     * Gets the second answer option.
     *
     * @return The second answer option.
     */
    public final String getOptionTwo() {
        return optionTwo;
    }

    /**
     * Gets the third answer option.
     *
     * @return The third answer option.
     */
    public final String getOptionThree() {
        return optionThree;
    }

    /**
     * Gets the fourth answer option.
     *
     * @return The fourth answer option.
     */
    public final String getOptionFour() {
        return optionFour;
    }

    /**
     * Gets the correct answer option.
     *
     * @return The correct answer option.
     */
    public final String getCorrectOption() {
        return correctOption;
    }


}
