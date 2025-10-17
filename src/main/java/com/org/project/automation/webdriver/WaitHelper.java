package com.org.project.automation.webdriver;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * This class helps driver to handle webdriver waits with ease
 */
public interface WaitHelper {
    Logger LOGGER = LoggerFactory.getLogger(WaitHelper.class);

    /**
     * Method to set implicit wait timeout
     *
     * @param timeout
     * @param unit
     */
    default void setImplicitWait(long timeout, TimeUnit unit) {
        LOGGER.info("Implicit Wait has been set to : {}", timeout);
        WebDriverManager.driver.manage().timeouts().implicitlyWait(timeout, unit);
    }

    /**
     * Method to get WebDriverWait object
     *
     * @param timeOutInSeconds
     * @param pollingEveryInMilliSec
     * @return
     */

    default WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMilliSec) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.driver, timeOutInSeconds);
        wait.pollingEvery(pollingEveryInMilliSec, TimeUnit.MILLISECONDS);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(ElementNotVisibleException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchFrameException.class);
        return wait;
    }

    /**
     * This method will return fluentWait object
     *
     * @param timeOutInSeconds
     * @param pollingEveryInMilliSec
     * @return
     */

    default Wait<WebDriver> getFluentWait(int timeOutInSeconds, int pollingEveryInMilliSec) {
        Wait<WebDriver> fwait = new FluentWait<WebDriver>(WebDriverManager.driver)
                .withTimeout(timeOutInSeconds, TimeUnit.SECONDS)
                .pollingEvery(pollingEveryInMilliSec, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        return fwait;
    }

    /**
     * This method will make sure element is visible
     *
     * @param element
     * @param timeOutInSeconds
     * @param pollingEveryInMilliSec
     */

    default void waitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for {} : seconds", element, timeOutInSeconds);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.visibilityOf(element));
        LOGGER.info("element is visible now");
    }

    /**
     * This method will make sure element is visible
     *
     * @param by
     * @param timeOutInSeconds
     * @param pollingEveryInMilliSec
     */

    default void waitForElementVisibleWithPollingTime(By by, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for {} : seconds", by, timeOutInSeconds);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LOGGER.info("element is visible now");
    }

    /**
     * This method will make sure element is clickable
     *
     * @param element
     * @param timeOutInSeconds
     */
    default void waitForElementClickable(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for {} : seconds", element, timeOutInSeconds);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        LOGGER.info("element is clickable now");
    }

    /**
     * This method will make sure element is clickable
     *
     * @param by
     * @param timeOutInSeconds
     */
    default void waitForElementClickable(By by, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for {} : seconds", by, timeOutInSeconds);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        LOGGER.info("element is clickable now");
    }

    /**
     * Method to wait for element to be clickable
     *
     * @param element
     * @param timeOutInSeconds
     * @param pollingEveryInMilliSec
     */

    default void waitForElementToBeClickable(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        LOGGER.info("element is clickable now");
    }

    /**
     * Method to wait for element to be clickable
     *
     * @param by
     * @param timeOutInSeconds
     * @param pollingEveryInMilliSec
     */

    default void waitForElementToBeClickable(By by, int timeOutInSeconds, int pollingEveryInMilliSec) {
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        LOGGER.info("element is clickable now");
    }

    /**
     * This method will make sure invisibility of element
     *
     * @param element
     * @param timeOutInSeconds
     * @return
     */

    default boolean waitForElementNotPresent(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for {} : seconds", element, timeOutInSeconds);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
        LOGGER.info("element is invisible now");
        return status;
    }

    /**
     * This method will make sure invisibility of element
     *
     * @param by
     * @param timeOutInSeconds
     * @return
     */

    default boolean waitForElementNotPresent(By by, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for {} : seconds", by, timeOutInSeconds);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        boolean status = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        LOGGER.info("element is invisible now");
        return status;
    }

    /**
     * This method will waitForFrameToBeAvailableAndSwitchToIt
     *
     * @param element
     * @param timeOutInSeconds
     */

    default void waitForFrameToBeAvailableAndSwitchToIt(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for {} : seconds", element, timeOutInSeconds);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        LOGGER.info("frame is available and switched");
    }

    /**
     * Method to set page load timeout
     *
     * @param timeout
     * @param unit
     */
    default void pageLoadTime(long timeout, TimeUnit unit) {
        LOGGER.info("waiting for page to load for : {} seconds ", unit);
        WebDriverManager.driver.manage().timeouts().pageLoadTimeout(timeout, unit);
        LOGGER.info("page is loaded");
    }

    /**
     * Method to wait for element using fluent wait
     *
     * @param element
     * @param timeOutInSeconds
     * @param pollingEveryInMilliSec
     */

    default void waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for : {} seconds with polling milliseconds: {}", element, timeOutInSeconds, pollingEveryInMilliSec);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.visibilityOf(element));
        LOGGER.info("element is visible now");
    }

    /**
     * Method to wait for element using fluent wait
     *
     * @param by
     * @param timeOutInSeconds
     * @param pollingEveryInMilliSec
     */

    default void waitForElement(By by, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for : {} seconds with polling milliseconds: {}", by, timeOutInSeconds, pollingEveryInMilliSec);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        LOGGER.info("element is visible now");
    }

    /**
     * Method to wait for element's attribute value
     *
     * @param by
     * @param attribute
     * @param value
     * @param timeOutInSeconds
     * @param pollingEveryInMilliSec
     */

    default void waitForElementAttributeContains(By by, String attribute, String value, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for : {} seconds with polling milliseconds: {}", by, timeOutInSeconds, pollingEveryInMilliSec);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.attributeContains(by, attribute, value));
        LOGGER.info("element with attribute {} = {} is visible now", attribute, value);
    }

    /**
     * Method to wait for element's attribute value
     *
     * @param element
     * @param attribute
     * @param value
     * @param timeOutInSeconds
     * @param pollingEveryInMilliSec
     */

    default void waitForElementAttributeContains(WebElement element, String attribute, String value, int timeOutInSeconds, int pollingEveryInMilliSec) {
        LOGGER.info("waiting for : {} for : {} seconds with polling milliseconds: {}", element, timeOutInSeconds, pollingEveryInMilliSec);
        Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilliSec);
        wait.until(ExpectedConditions.attributeContains(element, attribute, value));
        LOGGER.info("element with attribute {} = {} is visible now", attribute, value);
    }

}
