package com.org.project.automation.pageobjects;

import com.org.project.automation.webdriver.BasePage;
import com.org.project.automation.webdriver.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class SearchPage extends BasePage {
    public SearchPage(EventFiringWebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//input[@name=\"q\"]")
    private WebElement searchInput;

    @FindBy(how = How.XPATH, using = "//div[@class=\"FPdoLc tfB0Bf\"]//input[@value=\"Google Search\"]")
    private WebElement googleSearchButton;

    public void enter_searchInput(String value) {
        clearAndSendKeys(searchInput, value);
    }

    public void click_googleSearchInput() {
//        Actions actions = new Actions(WebDriverManager.driver);
//        actions.moveToElement(googleSearchButton).click();
        googleSearchButton.click();
    }

}
