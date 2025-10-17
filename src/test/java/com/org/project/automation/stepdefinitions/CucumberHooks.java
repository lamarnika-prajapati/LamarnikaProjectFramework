package com.org.project.automation.stepdefinitions;

import com.org.project.automation.readers.ConfigFileReader;
import com.org.project.automation.readers.ExcelReader;
import com.org.project.automation.testbase.TestContext;
import com.org.project.automation.webdriver.WebDriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class specifies cucumber hooks
 */
public class CucumberHooks extends WebDriverManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(CucumberHooks.class);
    TestContext testContext;
    ConfigFileReader configFileReader;
    ExcelReader excelFileReader;

    public CucumberHooks(TestContext context) {
        super();
        testContext = context;
        configFileReader=context.getFileReaderManager().getConfigReader();
        excelFileReader=context.getFileReaderManager().getExcelReader();
    }

    @Before("not @non_ui")
    public void beforeEachScenario(Scenario scenario){
        LOGGER.info("Executing beforeEachScenario for: {}", scenario.getName());
        getDriver();
    }
    @After("not @non_ui")
    public void afterEachScenario(Scenario scenario){
        LOGGER.info("Executing afterEachScenario for: {}", scenario.getName());
        if(scenario.isFailed()) captureScreenshot(scenario.getName());
        closeDriver();
    }

}
