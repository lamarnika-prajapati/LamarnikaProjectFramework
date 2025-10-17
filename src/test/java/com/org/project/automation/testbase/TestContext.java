package com.org.project.automation.testbase;

import com.org.project.automation.pageobjects.PageObjectManager;
import com.org.project.automation.readers.FileReaderManager;
import com.org.project.automation.webdriver.WebDriverManager;

/**
 * This class manages dependency injection for UI automation framework
 */

public class TestContext {

    private WebDriverManager webDriverManager;
    private PageObjectManager pageObjectManager;
    private FileReaderManager fileReaderManager;

    public TestContext() {
        webDriverManager = new WebDriverManager();
        pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
        fileReaderManager = new FileReaderManager();
    }

    /**
     * This methods returns PageObjectManager class instance
     *
     * @return PageObjectManager object
     */
    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    /**
     * This methods returns FileReaderManager class instance
     *
     * @return FileReaderManager object
     */

    public FileReaderManager getFileReaderManager() {
        return fileReaderManager;
    }
}
