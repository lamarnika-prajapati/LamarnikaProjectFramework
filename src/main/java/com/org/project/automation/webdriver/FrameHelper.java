package com.org.project.automation.webdriver;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class helps driver to handle browser frames with ease
 */
public interface FrameHelper {
    Logger LOGGER = LoggerFactory.getLogger(FrameHelper.class);

    /**
     * This method will switch to Frame based on frame index
     *
     * @param frameIndex
     */

    default void switchToFrame(int frameIndex) {
        WebDriverManager.driver.switchTo().frame(frameIndex);
        LOGGER.info("switched to: {} frame", frameIndex);
    }

    /**
     * This method will switchToFrame based on frame name
     *
     * @param frameName
     */

    default void switchToFrame(String frameName) {
        WebDriverManager.driver.switchTo().frame(frameName);
        LOGGER.info("switched to: {} frame", frameName);
    }

    /**
     * This method will switchToFrame based on frame WebElement
     *
     * @param element
     */

    default void switchToFrame(WebElement element) {
        WebDriverManager.driver.switchTo().frame(element);
        LOGGER.info("switched to: {} frame", element);
    }

}
