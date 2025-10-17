package com.org.project.automation.stepdefinitions;

import com.org.project.automation.pageobjects.SearchPage;
import com.org.project.automation.testbase.TestContext;
import com.org.project.automation.webdriver.BasePage;
import com.org.project.automation.webdriver.WebDriverManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleSearchSteps extends BasePage {

    SearchPage searchPage;
    TestContext testContext;
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleSearchSteps.class);


    public GoogleSearchSteps(TestContext context) {
        testContext = context;
        searchPage = testContext.getPageObjectManager().getSearchPage();
    }

    @Given("user navigates to \"(.*)\"")
    public void user_navigates_to(String url) {
        WebDriverManager.driver.get(url);
        LOGGER.info("Launching url: {}",url);
    }
    @When("input some \"(.*)\" keywords")
    public void inputSomeKeywords(String inputKeyword) {
        searchPage.enter_searchInput(inputKeyword);
        LOGGER.info("Entered value in google input: {}",inputKeyword);
    }

    @Then("click on search button")
    public void clickSearchButton() {
        searchPage.click_googleSearchInput();
        LOGGER.info("Clicked on Google search button");
    }
}
