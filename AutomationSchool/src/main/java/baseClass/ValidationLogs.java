package baseClass;

import org.testng.asserts.SoftAssert;

public class ValidationLogs {
    protected static final String ERROR_MESSAGE = "An error occurred while running test %s with message - %s";
    private static final String AND_ACTUAL_LABEL = " AND Actual = ";
    private static final String ERRORS_TAG = " errors";
    private int ssCount;
    private SoftAssert softAssert = new SoftAssert();

    public ValidationLogs() {
    }

    public void logStringValidation(String testStepName, String expected, String actual) {
        if (actual != null) {
            actual = actual.trim();
        } else {
            actual = "";
        }

        expected = expected.trim();
        String failMsg;
        if (expected.equalsIgnoreCase(actual)) {
            failMsg = "***" + testStepName.toUpperCase() + " VALIDATION SUCCESS*** || Expected = " + expected + " AND Actual = " + actual;
            System.out.println(failMsg);
        } else {
            failMsg = "***" + testStepName.toUpperCase() + " VALIDATION FAILURE*** || Expected = " + expected.trim() + "AND Actual = " + actual;
            System.out.println(failMsg);
            throw new AssertionError(failMsg);
        }
    }
}
