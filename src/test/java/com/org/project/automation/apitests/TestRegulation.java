package com.org.project.automation.apitests;

import com.jayway.restassured.response.Response;
import com.org.project.automation.readers.YamlReader;
import com.org.project.automation.rest.RestClient;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Listeners(CustomTestNGListeners.class)
public class TestRegulation extends RestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestRegulation.class);
    private Map<String, Object> modules;
    private Response response;
    private Map<String, String> headerMap;
    private String URL;
    private Map<String, String> assertMap;
    private YamlReader yamlReader;
    String DATA_SET_FILE = "testdata/testdataAPI.yml";

    @BeforeClass(description = "Load test data from")
    public void setUp() {
        yamlReader = new YamlReader(DATA_SET_FILE);
    }

    @Test(description = "Verify API for demo")
    public void checkClientRecord() throws Exception {
        modules = yamlReader.getYamlMaps();
        List<Map<String, Object>> clientRecordPOST = getServiceData("ClientRecordPOST", modules);
        URL = getURL(clientRecordPOST);
        headerMap = getHeader(clientRecordPOST);
        assertMap = getAssertion(clientRecordPOST);
        String body = getBody(clientRecordPOST);

        // API call
        response = post(URL, headerMap, body);

        // Assertions
        Assert.assertTrue(responseEquals(assertMap, response));
        Assert.assertTrue(statusCodeIs(202, response));

        // Storing values from response
        String resourceId = (String) getObjectValue(response, "party.client.resources[0].resourceId");

        List<Map<String, Object>> clientRegulations = getServiceData("ClientRegulationsGET", modules);
        URL = getURL(clientRegulations) + resourceId + "/regulations";
        headerMap = getHeader(clientRegulations);
        assertMap = getAssertion(clientRegulations);


        // API call
        response = get(URL, headerMap);

        // Assertions
        Assert.assertTrue(statusCodeIs(200, response));
        Assert.assertTrue("Error while asserting response", responseArrayListEquals(response.asString(), 0, assertMap));

    }

}
