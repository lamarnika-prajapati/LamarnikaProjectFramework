package com.org.project.automation.stepdefinitions;

import com.org.project.automation.readers.ExcelReader;
import com.org.project.automation.readers.JSONReader;
import com.org.project.automation.readers.YamlReader;
import com.org.project.automation.testbase.TestContext;
import com.org.project.automation.webdriver.AssertHelper;
import com.org.project.automation.webdriver.BasePage;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class FileReadersSteps implements AssertHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReadersSteps.class);
    TestContext testContext;
    YamlReader yamlReader;
    JSONReader jsonReader;
    ExcelReader excelReader;

    public FileReadersSteps(TestContext context) {

        this.testContext = context;
        yamlReader = testContext.getFileReaderManager().getYamlReader();
        jsonReader = testContext.getFileReaderManager().getJsonReader();
        excelReader = testContext.getFileReaderManager().getExcelReader();
    }

    @Given("Verify YAML file reader")
    public void verify_YAML_file_reader() {
        assertEquals("YAML value not matching", "https://www.google.co.in/", yamlReader.getYamlMaps().get("application_url"));
        assertEquals("YAML value not matching", "https://www.google.co.in/", new YamlReader("testdata/testdata.yml").getYamlMaps().get("application_url"));

    }

    @Given("Verify EXCEL file reader")
    public void verify_EXCEL_file_reader() {
        assertEquals("Excel value not matching", "username", excelReader.getExcelData("accounts")[0][0]);
        assertEquals("Excel value not matching", "password", new ExcelReader("testdata/testdata.xls").getExcelData("accounts")[0][1]);
    }

    @Given("Verify JSON file reader")
    public void verify_JSON_file_reader() {
        assertEquals("JSON value not matching", "rajat123", jsonReader.getJSONData("Scenario1", "Password"));
        assertEquals("JSON value not matching", "rajat1234", new JSONReader("testdata/testdata.json").getJSONData("Scenario2", "Password"));
    }


}
