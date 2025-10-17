package com.org.project.automation.listeners;

import com.org.project.automation.readers.ConfigFileReader;

public class TestCaseInfo {

    private String testCaseTitle;
    private String testCaseDescription;
    private String testExecutionStatus;
    private String testSuite;
    private String testFailureScreenshot;
    private String testExecutionOn;
    private String testFailureReason;
    private String testFailStackTrace;
    private String testExecutionRemark;
    private String testExecutionBuild;
    private String browserName;
    private String operatingSystem;
    private String testEnvironment;
    private Long testExecutionTimeStamp;
    private Long testExecutionTotalTime;

    public TestCaseInfo() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        this.testExecutionBuild = configFileReader.getBuildName();
        this.browserName = configFileReader.getBrowser().toString();
        this.operatingSystem = configFileReader.getOS().toString();
        this.testEnvironment = configFileReader.getTestEnvironment().toString();
    }

    /**
     * Method to get test execution time
     *
     * @return test execution time
     */

    public Long getTestExecutionTotalTime() {
        return testExecutionTotalTime;
    }

    /**
     * Method to set test execution time
     *
     * @param testExecutionTotalTime
     * @return object
     */

    public TestCaseInfo setTestExecutionTotalTime(Long testExecutionTotalTime) {
        this.testExecutionTotalTime = testExecutionTotalTime;
        return this;
    }


    /**
     * Method get test execution build name
     *
     * @return build name
     */

    public String getTestExecutionBuild() {
        return testExecutionBuild;
    }

    /**
     * Method set test execution build name
     *
     * @param testExecutionBuild
     * @return object
     */

    public TestCaseInfo setTestExecutionBuild(String testExecutionBuild) {
        this.testExecutionBuild = testExecutionBuild;
        return this;
    }

    /**
     * Method to get test fail stack trace
     *
     * @return failure stack trace
     */

    public String getTestFailStackTrace() {
        return testFailStackTrace;
    }


    /**
     * Method to set test fail stack trace
     *
     * @param testFailStackTrace
     * @return object
     */

    public TestCaseInfo setTestFailStackTrace(String testFailStackTrace) {
        this.testFailStackTrace = testFailStackTrace;
        return this;
    }

    /**
     * Method to get browser name
     *
     * @return browser name
     */

    public String getBrowserName() {
        return browserName;
    }

    /**
     * Method to get operating system name
     *
     * @return operating system name
     */

    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * Method to get test environment name
     *
     * @return test environment name
     */

    public String getTestEnvironment() {
        return testEnvironment;
    }

    /**
     * Method to get test execution time stamp
     *
     * @return test execution time stamp
     */

    public Long getTestExecutionTimeStamp() {
        return testExecutionTimeStamp;
    }

    /**
     * Method to set test execution time stamp
     *
     * @param testExecutionTimeStamp
     * @return test execution time stamp
     */

    public TestCaseInfo setTestExecutionTimeStamp(Long testExecutionTimeStamp) {
        this.testExecutionTimeStamp=testExecutionTimeStamp;
        return this;
    }

    /**
     * Method to get test case title
     *
     * @return test case title
     */

    public String getTestCaseTitle() {
        return testCaseTitle;
    }

    /**
     * Method to set test case title
     *
     * @param testCaseTitle
     * @return object
     */

    public TestCaseInfo setTestCaseTitle(String testCaseTitle) {
        this.testCaseTitle=testCaseTitle;
        return this;
    }

    /**
     * Method to get test case description
     *
     * @return test case description
     */

    public String getTestCaseDescription() {
        return testCaseDescription;
    }

    /**
     * Method to set test case description
     *
     * @param testCaseDescription
     * @return object
     */

    public TestCaseInfo setTestCaseDescription(String testCaseDescription) {
        this.testCaseDescription=testCaseDescription;
        return this;
    }

    /**
     * Method to get test execution status
     *
     * @return test execution status
     */

    public String getTestExecutionStatus() {
        return testExecutionStatus;
    }

    /**
     * Method to set test execution status
     *
     * @param testExecutionStatus
     * @return object
     */

    public TestCaseInfo setTestExecutionStatus(String testExecutionStatus) {
        this.testExecutionStatus=testExecutionStatus;
        return this;
    }

    /**
     * Method to get test suite name
     *
     * @return test suite name
     */

    public String getTestSuite() {
        return testSuite;
    }

    /**
     * Method to set test suite name
     *
     * @param testSuiteName
     * @return object
     */

    public TestCaseInfo setTestSuite(String testSuiteName) {
        this.testSuite=testSuite;
        return this;
    }

    /**
     * Method to get test failure screenshot path
     *
     * @return screenshot path
     */

    public String getTestFailureScreenshot() {
        return testFailureScreenshot;
    }

    /**
     * Method to set test failure screenshot path
     *
     * @param testFailureScreenshot
     * @return object
     */

    public TestCaseInfo setTestFailureScreenshot(String testFailureScreenshot) {
        this.testFailureScreenshot=testFailureScreenshot;
        return this;
    }


    /**
     * Method to get test case execution timestamp
     *
     * @return test case execution timestamp
     */

    public String getTestExecutionOn() {
        return testExecutionOn;
    }

    /**
     * Method to set test case execution timestamp
     *
     * @param testExecutionOn
     * @return object
     */

    public TestCaseInfo setTestExecutionOn(String testExecutionOn) {
        this.testExecutionOn=testExecutionOn;
        return this;
    }

    /**
     * Method to get test failure reason
     *
     * @return test failure reason
     */

    public String getTestFailureReason() {
        return testFailureReason;
    }

    /**
     * Method to set test failure reason
     *
     * @param testFailureReason
     * @return object
     */

    public TestCaseInfo setTestFailureReason(String testFailureReason) {
        this.testFailureReason=testFailureReason;
        return this;
    }

    /**
     * Method to get test execution mark
     *
     * @return test execution mark
     */

    public String getTestExecutionRemark() {
        return testExecutionRemark;
    }

    /**
     * Method to set test execution mark
     *
     * @param testExecutionRemark
     * @return object
     */

    public TestCaseInfo setTestExecutionRemark(String testExecutionRemark) {
        this.testExecutionRemark=testExecutionRemark;
        return this;
    }


}
