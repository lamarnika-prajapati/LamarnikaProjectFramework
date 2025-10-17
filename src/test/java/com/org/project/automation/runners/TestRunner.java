package com.org.project.automation.runners;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberCustomRunner.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "classpath:com.org.project.automation.stepdefinitions",
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"},
        tags = {"@utilities"},
            dryRun = false
)

public class TestRunner {
}
