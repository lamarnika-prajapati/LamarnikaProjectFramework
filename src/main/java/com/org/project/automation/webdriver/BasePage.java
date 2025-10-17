package com.org.project.automation.webdriver;

import org.openqa.selenium.WebElement;

public class BasePage implements AlertHelper, AssertHelper, DropDownHelper, FrameHelper, JavaScriptHelper, VerificationHelper, WaitHelper, WindowHelper, JQueryHelper {

    protected void clearAndSendKeys(WebElement webElement, String value) {
        webElement.clear();
        webElement.sendKeys(value);
    }

}
