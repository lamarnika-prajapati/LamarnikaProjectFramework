package com.org.project.automation.webdriver;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements JUnit assertion helpers
 */
public interface AssertHelper {
    Logger LOGGER = LoggerFactory.getLogger(AssertHelper.class);

    /**
     * This method implements assertTrue helper
     *
     * @param message
     * @param condition
     */
    default void assertTrue(String message, boolean condition) {
        try {
            Assert.assertTrue(message, condition);
            LOGGER.info("AssertionTrue success");
        } catch (AssertionError assertionError) {
            LOGGER.error("AssertionTrue failed: {}", message);
            Assert.fail("Test case failed: assertTrue failed: " + message);
        }
    }


    /**
     * This method implements assertFalse helper
     *
     * @param message
     * @param condition
     */
    default void assertFalse(String message, boolean condition) {
        try {
            Assert.assertFalse(message, condition);
            LOGGER.info("AssertionFalse success");
        } catch (AssertionError assertionError) {
            LOGGER.error("AssertionFalse failed: {}", message);
            Assert.fail("Test case failed: assertFalse failed: " + message);
        }
    }

    /**
     * This method implements assertEquals helper
     *
     * @param message
     * @param expected
     * @param actual
     */
    default void assertEquals(String message, Object expected, Object actual) {
        try {
            Assert.assertEquals(message, expected, actual);
            LOGGER.info("Assertion Equals success: expected {} got {}", expected, actual);
        } catch (AssertionError assertionError) {
            LOGGER.error("AssertionEquals failed: expected {} got {}, message: {}", expected, actual, message);
            Assert.fail("Test case failed: assertEquals failed: expected " + expected.toString() + " got " + actual.toString() + message);
        }
    }


}
