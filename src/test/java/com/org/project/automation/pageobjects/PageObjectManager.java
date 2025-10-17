package com.org.project.automation.pageobjects;

import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * This class helps in managing page objects at one place
 */
public class PageObjectManager {
    private EventFiringWebDriver driver;
    private SearchPage searchPage;
    private ResultPage resultPage;

    public PageObjectManager(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public SearchPage getSearchPage() {
        return (searchPage == null) ? searchPage = new SearchPage(driver) : searchPage;
    }

    public ResultPage getResultPage() {
        return (resultPage == null) ? resultPage = new ResultPage(driver) : resultPage;
    }

}
