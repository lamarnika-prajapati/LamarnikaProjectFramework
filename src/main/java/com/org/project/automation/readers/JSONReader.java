package com.org.project.automation.readers;

import com.org.project.automation.webdriver.ResourceHelper;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {

    public static final Logger LOGGER = LoggerFactory.getLogger(JSONReader.class);
    private String filePath;

    public JSONReader() {
        this.filePath = new ConfigFileReader().getDefaultJSONFilePath();
    }

    public JSONReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method to read value from JSON file for given Key
     *
     * @param scenarioName
     * @param parameterName
     * @return value
     */

    public String getJSONData(String scenarioName, String parameterName) {
        JSONObject scenario = null;
        try (FileReader fileReader = new FileReader(new File(ResourceHelper.getDefaultResourcesLocation() + filePath))) {
            JSONObject scenarios = new JSONObject(new JSONTokener(fileReader));
            scenario = scenarios.getJSONObject(scenarioName);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return scenario.getString(parameterName);
    }

}
