package com.org.project.automation.webdriver;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface VerificationHelper {
    Logger LOGGER = LoggerFactory.getLogger(VerificationHelper.class);

    /**
     * This method returns true if element is displayed false otherwise
     *
     * @param element
     * @return true if web element is displayed false otherwise
     */
    default boolean isDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            LOGGER.info("element is displayed..{}", element.getText());
            return true;
        } catch (Exception e) {
            LOGGER.error("element is not displayed..{}", e.getCause());
            return false;
        }
    }

    /**
     * This method returns true if element is not displayed false otherwise
     *
     * @param element
     * @return true if web element is not displayed false otherwise
     */
    default boolean isNotDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            LOGGER.info("element is present..{}", element.getText());
            return false;
        } catch (Exception e) {
            LOGGER.error("element is not present..");
            return true;
        }
    }

    /**
     * This method returns text value of web element
     *
     * @param element
     * @return text value
     */

    default String readValueFromElement(WebElement element) {
        if (element == null) {
            LOGGER.info("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if (status) {
            LOGGER.info("element text is ..{}", element.getText());
            return element.getText();
        } else {
            return null;
        }
    }

    /**
     * This method returns browser window title
     *
     * @return window title
     */

    default String getTitle() {
        LOGGER.info("page title is:  {}", WebDriverManager.driver.getTitle());
        return WebDriverManager.driver.getTitle();
    }

    /**
     * This method returns true if web element is enabled
     *
     * @param element
     * @return true if element is enabled false otherwise
     */

    default boolean isEnabled(WebElement element) {
        try {
            element.isEnabled();
            LOGGER.info("element is enabled..{}", element.getText());
            return true;
        } catch (Exception e) {
            LOGGER.error("element is not enabled..");
            return false;
        }
    }

}
