package com.org.project.automation.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements custom web driver listeners
 */
public class EventListeners implements WebDriverEventListener, WaitHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventListeners.class);

    /**
     * This method defines code that will be executed before accepting browser alert
     *
     * @param webDriver
     */

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {
        //ToDo
    }

    /**
     * This method defines code that will be executed after accepting browser alert
     *
     * @param webDriver
     */

    @Override
    public void afterAlertAccept(WebDriver webDriver) {
        //ToDo
    }

    /**
     * This method defines code that will be executed after alert dismiss
     *
     * @param webDriver
     */
    @Override
    public void afterAlertDismiss(WebDriver webDriver) {
        //ToDo
    }

    /**
     * This method defines code that will be executed before alert dismiss
     *
     * @param webDriver
     */
    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {
        //ToDo
    }

    /**
     * This method defines code that will be executed before navigating to given url
     *
     * @param url
     * @param webDriver
     */

    @Override
    public void beforeNavigateTo(String url, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines code that will be executed after navigating to given url
     *
     * @param url
     * @param webDriver
     */

    @Override
    public void afterNavigateTo(String url, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines code that will be executed before browser navigates back
     *
     * @param webDriver
     */
    @Override
    public void beforeNavigateBack(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines code that will be executed after browser navigates back
     *
     * @param webDriver
     */
    @Override
    public void afterNavigateBack(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines code that will be executed before browser navigates forward
     *
     * @param webDriver
     */
    @Override
    public void beforeNavigateForward(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines code that will be executed after browser navigates forward
     *
     * @param webDriver
     */
    @Override
    public void afterNavigateForward(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines code that will be executed before refresh navigation
     *
     * @param webDriver
     */
    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines code that will be executed after refresh navigation
     *
     * @param webDriver
     */
    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines the code that will be executed before locating the web element
     *
     * @param by
     * @param webElement
     * @param webDriver
     */
    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines the code that will be executed after locating the web element
     *
     * @param by
     * @param webElement
     * @param webDriver
     */
    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines code that will be executed before click event
     *
     * @param webElement
     * @param webDriver
     */
    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        waitForElement(webElement, 20, 2);
    }

    /**
     * This method defines code that will be executed after click event
     *
     * @param webElement
     * @param webDriver
     */
    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        // TODO Aut
    }

    /**
     * This method defines code that will be executed before value change event
     *
     * @param webElement
     * @param webDriver
     * @param charSequence
     */
    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequence) {
        waitForElement(webElement, 20, 2);
    }

    /**
     * This method defines code that will be executed after value change event
     *
     * @param webElement
     * @param webDriver
     * @param charSequence
     */
    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequence) {
        waitForElement(webElement, 20, 2);
        LOGGER.info("checking input field value integrity after value change: {}", webElement.toString());
        if (charSequence == null || charSequence[0].toString().isEmpty())
            LOGGER.warn("set value is null/empty (possible in case of clear())");
        else if (!webElement.getAttribute("value").equalsIgnoreCase(charSequence[0].toString()))
            LOGGER.warn("set value changed after set value action");
    }

    /**
     * This method defines code that will be executed before script execution
     *
     * @param script
     * @param webDriver
     */
    @Override
    public void beforeScript(String script, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines code that will be executed after script execution
     *
     * @param script
     * @param webDriver
     */
    @Override
    public void afterScript(String script, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    /**
     * This method defines code that will be executed on exception
     *
     * @param throwable
     * @param webDriver
     */
    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }


    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver,String string) {
        // TODO Auto-generated method stub
    }

    public <X> void beforeGetScreenshotAs(OutputType<X> var1){

    }

    public <X> void afterGetScreenshotAs(OutputType<X> var1, X var2){

    }

    public void beforeGetText(WebElement var1, WebDriver var2){

    }

    public void beforeSwitchToWindow(String var1, WebDriver var2){

    }

    public void afterSwitchToWindow(String var1, WebDriver var2){

    }

}
