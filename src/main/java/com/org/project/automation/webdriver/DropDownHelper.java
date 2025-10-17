package com.org.project.automation.webdriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class helps driver to handle dropdown with ease
 */
public interface DropDownHelper {

    Logger LOGGER = LoggerFactory.getLogger(DropDownHelper.class);

    /**
     * Method to select dropdown option using value
     *
     * @param webElement
     * @param value
     */

    default void selectUsingValue(WebElement webElement, String value) {
        Select select = new Select(webElement);
        LOGGER.info("selectUsingValue and value is: {}", value);
        select.selectByValue(value);
    }

    /**
     * Method to select dropdown option using value
     *
     * @param webElement
     * @param index
     */

    default void selectUsingIndex(WebElement webElement, int index) {
        Select select = new Select(webElement);
        LOGGER.info("selectUsingIndex and index is: {}", index);
        select.selectByIndex(index);
    }

    /**
     * Method to select dropdown option using visible text
     *
     * @param webElement
     * @param visibleText
     */

    default void selectUsingVisibleText(WebElement webElement, String visibleText) {
        Select select = new Select(webElement);
        LOGGER.info("selectUsingVisibleText and visibleText is: {}", visibleText);
        select.selectByVisibleText(visibleText);
    }

    /**
     * Method to deselect dropdown option using value
     *
     * @param webElement
     * @param value
     */

    default void deSelectUsingValue(WebElement webElement, String value) {
        Select select = new Select(webElement);
        LOGGER.info("deSelectUsingValue and value is: {}", value);
        select.deselectByValue(value);
    }

    /**
     * Method to deselect dropdown option using index
     *
     * @param webElement
     * @param index
     */

    default void deSelectUsingIndex(WebElement webElement, int index) {
        Select select = new Select(webElement);
        LOGGER.info("deSelectUsingIndex and index is: {}", index);
        select.deselectByIndex(index);
    }

    /**
     * Method to deselect dropdown option using visible text
     *
     * @param webElement
     * @param visibleText
     */

    default void deSelectUsingVisibleText(WebElement webElement, String visibleText) {
        Select select = new Select(webElement);
        LOGGER.info("deSelectUsingVisibleText and visibleText is: {}", visibleText);
        select.deselectByVisibleText(visibleText);
    }

    /**
     * Method to get all dropdown options
     *
     * @param webElement
     * @return list of dropdown option values
     */

    default List<String> getAllDropDownValues(WebElement webElement) {
        Select select = new Select(webElement);
        List<WebElement> elementList = select.getOptions();
        List<String> valueList = new LinkedList<String>();
        for (WebElement element : elementList) {
            LOGGER.info(element.getText());
            valueList.add(element.getText());
        }
        return valueList;
    }

    /**
     * Method to get dropdown selected value
     *
     * @param webElement
     * @return selected option value
     */

    default String getDropdownSelectedValue(WebElement webElement) {
        String fieldValue;
        try {
            fieldValue = new Select(webElement).getFirstSelectedOption().getText().trim();
        } catch (NoSuchElementException e) {
            fieldValue = new BasePage().jsGetValueByName(webElement);
        }
        return fieldValue;
    }

}
