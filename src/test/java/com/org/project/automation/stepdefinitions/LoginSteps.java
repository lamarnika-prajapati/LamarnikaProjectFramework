package com.org.project.automation.stepdefinitions;

import com.org.project.automation.pageobjects.ResultPage;
import com.org.project.automation.pageobjects.SearchPage;
import com.org.project.automation.testbase.TestContext;
import com.org.project.automation.webdriver.AssertHelper;
import com.org.project.automation.webdriver.WebDriverManager;
import cucumber.api.java.en.Given;
import org.junit.Test;

public class LoginSteps implements AssertHelper {

    SearchPage searchPage;
    ResultPage resultPage;
    TestContext testContext;

    LoginSteps(TestContext context){
        testContext=context;
        searchPage=testContext.getPageObjectManager().getSearchPage();
        resultPage=testContext.getPageObjectManager().getResultPage();
    }

    @Given("I navigate to {string}")
    public void i_navigate_to(String url) {
        WebDriverManager.driver.get(url);
    }


    @Given("Search result page is displayed")
    public void search_result_page_is_displayed() {
        assertTrue("Top Navigation bar is not displayed",resultPage.isTopNavBarVisible());
    }

    @Given("Search {string}")
    public void search(String search_input) {
      searchPage.enter_searchInput(search_input);
      searchPage.click_googleSearchInput();
    }

    @Given("Search result page is not displayed")
    public void search_result_page_is_not_displayed() {
        assertTrue("Search result page is not displayed",resultPage.isTopNavBarVisible());
    }
}
