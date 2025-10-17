package com.org.project.automation.webdriver;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class helps driver to handle javascript execution with ease
 */
public interface JavaScriptHelper {
    Logger LOGGER = LoggerFactory.getLogger(JavaScriptHelper.class);

    /**
     * This method will execute java script
     *
     * @param script
     * @return
     */

    default Object executeScript(String script, Object... arg) {
        JavascriptExecutor exe = (JavascriptExecutor) WebDriverManager.driver;
        return exe.executeScript(script, arg);
    }

    /**
     * This method will scroll till element
     *
     * @param element
     */

    default void scrollToElement(WebElement element) {
        LOGGER.info("scrolling to WebElement...");
        executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
    }

    /**
     * This method will scroll till element and click
     *
     * @param element
     */

    default void scrollToElementAndClick(WebElement element) {
        scrollToElement(element);
        element.click();
        LOGGER.info("element is clicked: {}", element.toString());
    }

    /**
     * Scroll till element view
     *
     * @param element
     */

    default void scrollIntoView(WebElement element) {
        LOGGER.info("scroll till web element: {}", element.toString());
        executeScript("arguments[0].scrollIntoView()", element);
    }

    /**
     * This method will Scroll till element view and click
     *
     * @param element
     */

    default void scrollIntoViewAndClick(WebElement element) {
        scrollToElement(element);
        element.click();
        LOGGER.info("element is clicked: {}", element.toString());
    }

    /**
     * This method will scroll down vertically
     */

    default void scrollDownVertically() {
        LOGGER.info("scrolling down vertically....");
        executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    /**
     * This method will scroll up vertically
     */

    default void scrollUpVertically() {
        LOGGER.info("scrolling up vertically....");
        executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }

    /**
     * This method will scroll till given pixel (e.g=1500)
     *
     * @param pixel
     */

    default void scrollDownByPixel(int pixel) {
        executeScript("window.scrollBY(0," + pixel + ")");
    }

    default void scrollUpByPixel(int pixel) {
        executeScript("window.scrollBY(0,-" + pixel + ")");
    }

    /**
     * This method will zoom screen by 100%
     */

    default void zoomInBy100Percentage() {
        executeScript("document.body.style.zoom='100%'");
    }

    /**
     * This method will zoom screen by 60%
     */

    default void zoomInBy60Percentage() {
        executeScript("document.body.style.zoom='60%'");
    }

    /**
     * This method will click on element
     *
     * @param element
     */

    default void clickElement(WebElement element) {
        executeScript("arguments[0].click();", element);
    }
}
