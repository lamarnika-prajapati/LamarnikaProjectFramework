package com.org.project.automation.apitests;

import com.org.project.automation.readers.YamlReader;
import com.org.project.automation.rest.RestClient;
import org.elasticsearch.common.xcontent.yaml.YamlXContentGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Listeners(CustomTestNGListeners.class)
public class TestGLCECreation extends RestClient {

    private Map<String, Object> modules;
    private Map<String, String> headerMap;
    private String URL;
    private Map<String, String> assertMap;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestGLCECreation.class);

    @BeforeClass(description = "Load test data from")
    public void setup() {

    }

    @Test(description = "Verify CE creation", enabled = true)
    public void createCE() {
        String DATA_SET_FILE = "testdata/testdataCE.yml";
        modules = new YamlReader(DATA_SET_FILE).getYamlMaps();

        List<Map<String, Object>> ceRecordPOST = getServiceData("CERecordPOST", modules);
        URL = getURL(ceRecordPOST);
        headerMap = getHeader(ceRecordPOST);
        assertMap = getAssertion(ceRecordPOST);

        String CENumber = createData(headerMap, URL, assertMap, ceRecordPOST);
        LOGGER.info("CE created successfully: " + CENumber);
    }

    @Test(description = "Verify GL creation", enabled = true)
    public void createGL() {
        String DATA_SET_FILE = "testdata/testdataAccount.yml";
        modules = new YamlReader(DATA_SET_FILE).getYamlMaps();

        List<Map<String, Object>> accountRecordPOST = getServiceData("AccountRecordPOST", modules);
        URL = getURL(accountRecordPOST);
        headerMap = getHeader(accountRecordPOST);
        assertMap = getAssertion(accountRecordPOST);

        String GLNumber = createData(headerMap, URL, assertMap, accountRecordPOST);
        LOGGER.info("Account created successfully: " + GLNumber);
    }

}
