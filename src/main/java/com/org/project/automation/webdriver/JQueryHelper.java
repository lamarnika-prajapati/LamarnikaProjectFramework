package com.org.project.automation.webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public interface JQueryHelper {

    Logger LOGGER = LoggerFactory.getLogger(JQueryHelper.class);

    /**
     * Method to get value using name attribute of element
     *
     * @param name
     * @return value attribute of web element
     */

    default String jsGetValueByName(String name) {
        LOGGER.info("fetching value by name for {}", name);
        new JSWaiter().waitAllRequest();
        String value = (String) ((JavascriptExecutor) WebDriverManager.driver).executeScript("return $(\"*[name=" + name + "]\").val();");
        return (value == null) ? null : value.trim();
    }

    /**
     * Method to get value using name attribute of element
     *
     * @param webElement
     * @return value attribute of web element
     */

    default String jsGetValueByName(WebElement webElement) {
        String name = webElement.getAttribute("name");
        LOGGER.info("fetching value by name for {}", name);
        new JSWaiter().waitAllRequest();
        String value = (String) ((JavascriptExecutor) WebDriverManager.driver).executeScript("return $(\"*[name=" + name + "]\").val();");
        return (value == null) ? null : value.trim();
    }

    /**
     * Method to set value by name
     *
     * @param name
     * @param value
     */

    default String jsSetValueByName(String name, String value) {
        new JSWaiter().waitAllRequest();
        ((JavascriptExecutor) WebDriverManager.driver).executeScript("$(\"*[name=" + name + "]\").val(" + value + ");");
        return (value == null) ? null : value.trim();
    }

    /**
     * Method to get element value by given attribute value
     *
     * @param attributeName
     * @param attributeValue
     * @return value
     */

    default String jsGetValueBy(String attributeName, String attributeValue) {
        new JSWaiter().waitAllRequest();
        String value = (String) WebDriverManager.driver.executeScript("return $(\"*[" + attributeName + "=" + attributeValue + "]\").val();", new Object[0]);
        return (value == null) ? null : value.trim();
    }

}
