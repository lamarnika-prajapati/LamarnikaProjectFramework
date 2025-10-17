package com.org.project.automation.pageobjects;

import com.org.project.automation.webdriver.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ResultPage extends BasePage {

    public ResultPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "top_nav")
    private WebElement topNavBar;

    public Boolean isTopNavBarVisible() {
        return isDisplayed(topNavBar);
    }
}
