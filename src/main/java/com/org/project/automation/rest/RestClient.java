package com.org.project.automation.rest;

import com.jayway.restassured.response.Response;
import com.org.project.automation.webdriver.AssertHelper;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

/**
 * This class acts as a parent class for all API test cases
 */


public class RestClient implements APITestDataReader, APIUtility, AssertHelper {

    /**
     * This method is used to create CE & GL
     *
     * @param headerMap         : used to set headers
     * @param URL               : post url
     * @param assertMap         : Assertion map
     * @param clientRecordPost  : Map of complete record
     * @return
     */

    public String createData(Map<String, String> headerMap, String URL, Map<String, String> assertMap, List<Map<String, Object>> clientRecordPost) {
        String body = (String) clientRecordPost.get(3).get("postbody");
        Response response = post(URL, headerMap, body);
        Assert.assertTrue(responseEquals(assertMap, response));
        Assert.assertTrue(statusCodeIs(201, response));
        return response.jsonPath().getString("resourceId");
    }

}
