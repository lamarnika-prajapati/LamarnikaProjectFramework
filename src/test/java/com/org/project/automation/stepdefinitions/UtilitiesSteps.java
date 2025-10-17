package com.org.project.automation.stepdefinitions;

import com.org.project.automation.utils.DBConnectionManager;
import com.org.project.automation.utils.EncryptionManager;
import com.org.project.automation.webdriver.AssertHelper;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class UtilitiesSteps implements AssertHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilitiesSteps.class);
    DBConnectionManager dbConnectionManager;

    @Given("Verify encrypt method")
    public void verify_encrypt_method() {
        assertEquals("assert equal failed ", "07WfylTarjY=", EncryptionManager.encrypt("admin"));
    }

    @Given("Verify decrypt method")
    public void verify_decrypt_method() {
        assertEquals("assert equal failed ", EncryptionManager.decrypt("07WfylTarjY="), "admin");
    }

    @Given("Verify executeQuery method")
    public void verify_executeQuery_method() {
        String query = "select Personid from tperson where FIRST_NAME='RAJAT'";
        dbConnectionManager = new DBConnectionManager();
        List<Map<String, Object>> list = dbConnectionManager.executeQuery(query);
        LOGGER.info("query result set: ");
        for (Map<String, Object> row : list) {
            for (Map.Entry<String, Object> rowEntry : row.entrySet()) {
                LOGGER.info("{} = {}", rowEntry.getKey(), rowEntry.getValue());
            }
        }


    }

}
