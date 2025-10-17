package com.org.project.automation.webdriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * This class helps driver to handle browser windows with ease
 */
public interface WindowHelper {
    Logger LOGGER = LoggerFactory.getLogger(WindowHelper.class);

    /**
     * This method will switch to parent window
     */
    default void switchToParentWindow() {
        LOGGER.info("switching to parent window...");
        WebDriverManager.driver.switchTo().defaultContent();
    }

    /**
     * This method will switch to child window based on index
     *
     * @param index
     */

    default void switchToWindow(int index) {
        Set<String> windows = WebDriverManager.driver.getWindowHandles();
        int i = 1;
        for (String window : windows) {
            if (i == index) {
                LOGGER.info("switch to : {} window", index);
                WebDriverManager.driver.switchTo().window(window);
            } else {
                i++;
            }
        }
    }

    /**
     * This method will close all tabbed windows and
     * will switch to main window
     */
    default void closeAllTabsAndSwitchToMainWindow() {
        Set<String> windows = WebDriverManager.driver.getWindowHandles();
        String mainWindow = WebDriverManager.driver.getWindowHandle();

        for (String window : windows) {
            if (!window.equalsIgnoreCase(mainWindow)) {
                WebDriverManager.driver.close();
            }
        }
        LOGGER.info("switched to main window");
        WebDriverManager.driver.switchTo().window(mainWindow);
    }

    /**
     * This method will do browser back navigation
     */

    default void navigateBack() {
        LOGGER.info("navigating back");
        WebDriverManager.driver.navigate().back();
    }

    /**
     * This method will do browser forward navigation
     */

    default void navigateForward() {
        LOGGER.info("navigating forward");
        WebDriverManager.driver.navigate().forward();
    }
}
