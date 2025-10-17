package com.org.project.automation.webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class helps driver to handle browser alerts with ease
 */
public interface AlertHelper {
    Logger LOGGER = LoggerFactory.getLogger(AlertHelper.class);

    /**
     * This method is used to switch to browser alert
     *
     * @return object of browser alert
     */

    default Alert getAlert() {
        LOGGER.info("alert test: {}", WebDriverManager.driver.switchTo().alert().getText());
        return WebDriverManager.driver.switchTo().alert();
    }

    /**
     * Method to accept browser alert
     */
    default void acceptAlert() {
        getAlert().accept();
        LOGGER.info("accept Alert is done...");
    }

    /**
     * Method to dismiss browser alert
     */
    default void dismissAlert() {
        getAlert().dismiss();
        LOGGER.info("dismiss Alert is done...");
    }

    /**
     * Method to get browser alert text
     *
     * @return browser alert text
     */
    default String getAlertText() {
        String text = getAlert().getText();
        LOGGER.info("alert text: {}", text);
        return text;
    }

    /**
     * This method is to check if alert is present
     *
     * @return true if alert is present false otherwise
     */

    default boolean isAlertPresent() {
        try {
            WebDriverManager.driver.switchTo().alert();
            LOGGER.info("alert is present");
            return true;
        } catch (NoAlertPresentException e) {
            LOGGER.info("alert is not present", e.getCause());
            return false;
        }
    }

    /**
     * This method is to accept alert if present
     */

    default void acceptAlertIfPresent() {
        if (isAlertPresent()) {
            acceptAlert();
        } else {
            LOGGER.info("Alert is not present...");
        }
    }

    /**
     * This method is to dismiss alert if present
     */

    default void dismissAlertIfPresent() {
        if (isAlertPresent()) {
            dismissAlert();
        } else {
            LOGGER.info("Alert is not present...");
        }
    }

    /**
     * Method to accept browser alert after submitting value
     *
     * @param value
     */
    default void acceptPrompt(String value) {
        if (isAlertPresent()) {
            Alert alert = getAlert();
            alert.sendKeys(value);
            alert.accept();
            LOGGER.info("alert text: {}", value);
        }
    }

}
