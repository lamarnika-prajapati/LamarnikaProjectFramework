package com.org.project.automation.listeners;

import com.org.project.automation.readers.ConfigFileReader;
import org.apache.commons.net.ntp.TimeStamp;
import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CustomJUnitListener extends RunListener {

    Logger LOGGER = LoggerFactory.getLogger(CustomJUnitListener.class);
    private String executionStatus = "Passed";
    private String failureStackTrace;
    private String failureReason;
    long testStartedAt;
    long testEndedAt;

    /**
     * Celled before any tests have been run. This may be called on an arbitrary thread
     *
     * @param description describes the tests to be run
     * @throws Exception
     */
    @Override
    public void testRunStarted(Description description) throws Exception {
        LOGGER.info("No of tests to execute: {}", description.testCount());
    }

    /**
     * Called when all tests have finished. This may be called on an arbitrary thread
     *
     * @param result the summary of the test run, including all the tests that failed
     * @throws Exception
     */
    @Override
    public void testRunFinished(Result result) throws Exception {
        LOGGER.info("No of tests executed: {}", result.getRunCount());

    }

    /**
     * This method is executed when an atomic test is about to be started
     *
     * @param description the description of the test that is about to be run
     *                    (generally a class or method name)
     * @throws Exception
     */
    @Override
    public void testStarted(Description description) throws Exception {
        LOGGER.info("Test is starting: {}", description.getMethodName());
        testStartedAt = System.currentTimeMillis();
        executionStatus = "Passed";
        failureReason = "NA";
        failureStackTrace = "NA";
    }

    /**
     * This method is executed when an atomic test is finished, whether the test succeeds or fails.
     *
     * @param description the description of the test that just executed
     * @throws Exception
     */
    @Override
    public void testFinished(Description description) throws Exception {
        LOGGER.info("Test is finished: {}", description.getMethodName());
        ConfigFileReader configFileReader = new ConfigFileReader();
        testEndedAt = System.currentTimeMillis();
        long time = new Date().getTime();

        TestCaseInfo testCaseInfo = new TestCaseInfo().setTestCaseTitle(description.getMethodName())
                .setTestSuite(description.getClassName())
                .setTestExecutionStatus(executionStatus)
                .setTestFailureReason(failureReason)
                .setTestExecutionOn(new TimeStamp(time).toString()).setTestExecutionTimeStamp(time)
                .setTestExecutionTotalTime(testEndedAt - testStartedAt)
                .setTestFailStackTrace(failureStackTrace)
                .setTestExecutionBuild(configFileReader.getBuildName());

        if (configFileReader.isElasticSearchReportActive())
            ElasticHelper.saveTestExecutionStatus(testCaseInfo);
    }


    /**
     * This method is executed when an atomic test fails, or when a listener throws an exception
     *
     * @param failure describes the test that failed and the exception that was thrown
     * @throws Exception
     */
    @Override
    public void testFailure(Failure failure) throws Exception {
        LOGGER.error("Test FAILED: {}", failure.getDescription().getMethodName());
        executionStatus = "Failed";
        failureReason = failure.getException().toString();
        failureStackTrace = failure.getTrace();
    }

    /**
     * This method is executed when an atomic test flags that it assumes a condition that is false
     *
     * @param failure describes the test that failed and the
     *                {@Link org.junit.AssumptionViolatedException} that was thrown
     */
    public void testAssumptionFailure(Failure failure) {
        LOGGER.error("Failed: {}", failure.getDescription().getMethodName());
        executionStatus = "Failed";
        failureReason = failure.getException().toString();
    }

    /**
     * This method is executed when a test will not be executed. generally when a test method is annotated
     * with {@Link org.junit.Ignore}
     *
     * @param description describes the test that will not be executed
     * @throws Exception
     */

    public void testIgnored(Description description) throws Exception {
        /*super.testIgnored(description);
        Ignore ignore = description.getAnnotation(Ignore.class);
        String ignoreMessage = String.format(
                "@Ignore test method '%s()': '%s'",
                description.getMethodName(), ignore.value());
        LOGGER.warn(ignoreMessage);*/
        LOGGER.warn("Ignored: {}",description.getMethodName());
        executionStatus = "Ignored";
        failureReason = "Test case is ignored";
    }


}
