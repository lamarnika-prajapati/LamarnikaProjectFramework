package com.org.project.automation.webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSWaiter {
    private Logger LOGGER = LoggerFactory.getLogger(JSWaiter.class);

    private void ajaxComplete() {
        ((JavascriptExecutor) WebDriverManager.driver).executeScript("var callback = arguments[arguments.length - 1];"
                + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
                + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
                + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
    }

    private void waitForJQueryLoad() {
        try {
            ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) WebDriverManager.driver)
                    .executeScript("return jQuery.active") == 0);

            boolean jqueryReady = (Boolean) ((JavascriptExecutor) WebDriverManager.driver).executeScript("return jQuery.active==0");

            if (!jqueryReady) {
                new WebDriverWait(WebDriverManager.driver, 10).until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitForAngularLoad() {
        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        angularLoads(angularReadyScript);
    }

    private void waitUntilJSReady() {
        try {
            ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) WebDriverManager.driver)
                    .executeScript("return document.readyState").toString().equals("complete");

            boolean jsReady = ((JavascriptExecutor) WebDriverManager.driver).executeScript("return document.readyState").toString().equals("complete");

            if (!jsReady) {
                new WebDriverWait(WebDriverManager.driver, 10).until(jsLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitUntilJQueryReady() {
        Boolean jQueryDefined = (Boolean) ((JavascriptExecutor) WebDriverManager.driver).executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined) {
            poll(20);

            waitForJQueryLoad();

            poll(20);
        }
    }

    public void waitUntilAngularReady() {
        try {
            Boolean angularUnDefined = (Boolean) ((JavascriptExecutor) WebDriverManager.driver).executeScript("return window.angular === undefined");
            if (!angularUnDefined) {
                Boolean angularInjectorUnDefined = (Boolean) ((JavascriptExecutor) WebDriverManager.driver).executeScript("return angular.element(document).injector() === undefined");
                if (!angularInjectorUnDefined) {
                    poll(20);

                    waitForAngularLoad();

                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void waitUntilAngular5Ready() {
        try {
            Object angular5Check = ((JavascriptExecutor) WebDriverManager.driver).executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
            if (angular5Check != null) {
                Boolean angularPageLoaded = (Boolean) ((JavascriptExecutor) WebDriverManager.driver).executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
                if (!angularPageLoaded) {
                    poll(20);

                    waitForAngular5Load();

                    poll(20);
                }
            }
        } catch (WebDriverException ignored) {
        }
    }

    private void waitForAngular5Load() {
        String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
        angularLoads(angularReadyScript);
    }

    private void angularLoads(String angularReadyScript) {
        try {
            ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript(angularReadyScript).toString());

            boolean angularReady = Boolean.valueOf(((JavascriptExecutor) WebDriverManager.driver).executeScript(angularReadyScript).toString());

            if (!angularReady) {
                new WebDriverWait(WebDriverManager.driver, 10).until(angularLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void waitAllRequest() {
        waitUntilJSReady();
        ajaxComplete();
        waitUntilJQueryReady();
        waitUntilAngularReady();
        waitUntilAngular5Ready();
    }

    /**
     * Method to make sure a specific element has loaded on the page
     *
     * @param by
     * @param expected
     */
    public void waitForElementAreComplete(By by, int expected) {
        ExpectedCondition<Boolean> angularLoad = driver -> {
            int loadingElements = WebDriverManager.driver.findElements(by).size();
            return loadingElements >= expected;
        };
        new WebDriverWait(WebDriverManager.driver, 10).until(angularLoad);
    }

    /**
     * Waits for the elements animation to be completed
     *
     * @param css
     */
    public void waitForAnimationToComplete(String css) {
        ExpectedCondition<Boolean> angularLoad = driver -> {
            int loadingElements = WebDriverManager.driver.findElements(By.cssSelector(css)).size();
            return loadingElements == 0;
        };
        new WebDriverWait(WebDriverManager.driver, 10).until(angularLoad);
    }

    private void poll(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
