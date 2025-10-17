package com.org.project.automation.webdriver;

import com.org.project.automation.enums.DriverType;
import com.org.project.automation.enums.EnvironmentType;
import com.org.project.automation.readers.FileReaderManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverManager.class);
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    public static EventFiringWebDriver driver;
    public static WebDriver webDriver;
    EventListeners eventListeners;

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    /**
     * This method creates or returns webdriver instance
     *
     * @return webdriver
     */

    public EventFiringWebDriver getDriver() {
        if (driver == null) driver = createDriver();
        return driver;
    }

    /**
     * Method to create webdriver instance
     *
     * @return webdriver
     */

    private EventFiringWebDriver createDriver() {
        switch (environmentType) {
            case LOCAL:
                driver = createLocalDriver();
                break;
            case REMOTE:
                driver = createRemoteDriver();
                break;
        }
        return driver;
    }


    /**
     * Method to create remote webdriver
     *
     * @return webdriver
     */
    private EventFiringWebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebdriver is not yet implemented");
    }

    /**
     * Method to create local webdriver
     *
     * @return webdriver
     */
    private EventFiringWebDriver createLocalDriver() {
        LOGGER.info("creating web driver: {}", driverType);
        switch (driverType) {
            case FIREFOX:
                webDriver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath());
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("useAutomationExtension", false);
                options.addArguments("--lang=en-ca");
                if (FileReaderManager.getInstance().getConfigReader().isRemoteDriverEnabled()) {
                    try {
                        webDriver = new RemoteWebDriver(new URL(FileReaderManager.getInstance().getConfigReader().getRemoteDriverUrl()), options);
                    } catch (MalformedURLException ex) {
                        LOGGER.error(ex.getMessage(), ex);
                    }
                }
                webDriver = new ChromeDriver(options);
                break;
            case INTERNETEXPLORER:
                webDriver = new InternetExplorerDriver();
                break;
        }
        LOGGER.info("maximizing web browser window");
        if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
            webDriver.manage().window().maximize();
        LOGGER.info("web browser implicit wait set to: {}", FileReaderManager.getInstance().getConfigReader().getImplicitWait());
        webDriver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWait(), TimeUnit.SECONDS);
        driver = new EventFiringWebDriver(webDriver);
        eventListeners = new EventListeners();
        LOGGER.info("registering event listeners to web driver");
        driver.register(eventListeners);
        return driver;
    }

    /**
     * Method to close webdriver instance
     */
    public void closeDriver() {
        LOGGER.info("closing web browser");
        driver.close();
        driver.quit();
        LOGGER.info("unregistering event listeners from web driver");
        driver.unregister(eventListeners);
        driver = null;
    }

    /**
     * Method to capture browser screenshot
     *
     * @param fileName
     */

    public void captureScreenshot(String fileName) {
        LOGGER.info("capturing web browser screenshot in file: {}", fileName);
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String testName = fileName.replace(" ", "_").concat(".png");
            FileUtils.copyFile(screenshot, new File(FileReaderManager.getInstance().getConfigReader().getScreenshotPath() + testName));
        } catch (WebDriverException exception) {
            LOGGER.error(exception.getMessage(), exception);
        } catch (ClassCastException exception) {
            LOGGER.error(exception.getMessage(), exception);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage(), exception);
        }
    }
}
