package com.org.project.automation.apitests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

public class CustomTestNGListeners implements ITestListener, ISuiteListener, IInvokedMethodListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomTestNGListeners.class);

    // This belongs to ISuiteListener and will execute before the suite starts
    public void onStart(ISuite arg0) {
        LOGGER.info("About to begin executing suite {}", arg0.getName());
    }

    // This belongs to ISuiteListener and will execute, once the suite is finished
    public void onFinish(ISuite arg0) {
        LOGGER.info("About to end executing suite {}", arg0.getName());
    }

    // This belongs to ITestListener and will execute before starting of Test set/batch
    public void onStart(ITestContext arg0) {
        LOGGER.info("About to begin executing test {}", arg0.getName());
    }

    // This belongs to ITestListener and will execute, once the Test set/batch is finished
    public void onFinish(ITestContext arg0) {
        LOGGER.info("Completed executing test {}", arg0.getName());
    }

    // This belongs to ITestListener and will execute only when the test is pass
    public void onTestSuccess(ITestResult arg0) {
        // This is calling the printTestResults method
        printTestResults(arg0);
    }

    // This belongs to ITestListener and will execute only on the event of fail test
    public void onTestFailure(ITestResult arg0) {
// This is calling the printTestResults method
        printTestResults(arg0);
    }

    // This belongs to ITestListener and will execute before the main test start (@Test)
    public void onTestStart(ITestResult arg0) {
        LOGGER.info("The execution of the main test starts now");
    }

    // This belongs to ITestListener and will execute only if any of the main test(@Test) gets skipped
    public void onTestSkipped(ITestResult arg0) {
        printTestResults(arg0);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    /**
     * This is the method which will be executed in case of test pass or fail
     * This will provide the information on the test
     *
     * @param result
     */
    private void printTestResults(ITestResult result) {
        LOGGER.info("Test method resides in {}", result.getTestClass().getName());
        if (result.getParameters().length != 0) {
            String params = null;
            for (Object parameter : result.getParameters()) {
                params += parameter.toString() + ",";
            }
            LOGGER.info("Test Method had the following parameters : {}", params);
        }

        String status = null;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "Pass";
                break;
            case ITestResult.FAILURE:
                status = "Failed";
                break;
            case ITestResult.SKIP:
                status = "Skipped";
        }
        LOGGER.info("Test status: {}", status);
    }


    // This belongs to IInvokedMethodlistener and will execute before every method including @Before @After @Test

    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
        String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());
        LOGGER.info(textMsg);
    }

    // This belongs to IInvokedMethodlistener listener and will execute after every method including @Before @After @Test

    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
        String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());
        LOGGER.info(textMsg);
    }

    //This will return method names to the calling function

    private String returnMethodName(ITestNGMethod method) {
        return method.getRealClass().getSimpleName() + "." + method.getMethodName();
    }


}
