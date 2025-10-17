package com.org.project.automation.rest;

import com.jayway.restassured.response.Response;
import org.elasticsearch.search.aggregations.metrics.InternalHDRPercentiles;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public interface APIUtility {

    Logger LOGGER = LoggerFactory.getLogger(APIUtility.class);

    /**
     * This method is to perform GET
     *
     * @param Endpoint     is the complete URL
     * @param headerFields is a Map which has the headers
     * @return
     */
    default Response get(String Endpoint, Map<String, String> headerFields) {

        LOGGER.info("GET url: {} ", Endpoint);
        LOGGER.info("Headers: {} : ", headerFields);
        Response response = given().headers(headerFields).when().get(Endpoint).then().extract().response();
        LOGGER.info("Response body: {} ", response.asString());
        return response;
    }

    /**
     * This method is to perform POST
     *
     * @param Endpoint     is the complete URL
     * @param headerFields is a Map which has the headers
     * @param postbody     is the body
     * @return
     */
    default Response post(String Endpoint, Map<String, String> headerFields, String postbody) {

        LOGGER.info("POST url: {} ", Endpoint);
        LOGGER.info("Headers: {} : ", headerFields);
        LOGGER.info("Request body : {}", postbody);
        Response response = given().headers(headerFields).body(postbody).when().post(Endpoint).then().extract().response();
        LOGGER.info("Response body: {} ", response.asString());
        return response;
    }

    /**
     * This method is to perform PUT
     *
     * @param Endpoint     is the complete URL
     * @param headerFields is a Map which has the headers
     * @param postbody     is the body
     * @return
     */
    default Response put(String Endpoint, Map<String, String> headerFields, String postbody) {

        LOGGER.info("PUT url: {} ", Endpoint);
        LOGGER.info("Headers: {} : ", headerFields);
        LOGGER.info("Request body : {}", postbody);
        Response response = given().headers(headerFields).body(postbody).when().put(Endpoint).then().extract().response();
        LOGGER.info("Response body: {} ", response.asString());
        return response;
    }

    /**
     * This method is to perform DELETE
     *
     * @param Endpoint     is the complete URL
     * @param headerFields is a Map which has the headers
     * @param postbody     is the body
     * @return
     */
    default Response delete(String Endpoint, Map<String, String> headerFields, String postbody) {

        LOGGER.info("DELETE url: {} ", Endpoint);
        LOGGER.info("Headers: {} : ", headerFields);
        LOGGER.info("Request body : {}", postbody);
        Response response = given().headers(headerFields).body(postbody).when().put(Endpoint).then().extract().response();
        LOGGER.info("Response body: {} ", response.asString());
        return response;
    }

    /**
     * This method is to validate the json response
     *
     * @param responseFields is a Map which has the key and values which are to be validated against the response
     * @param response
     * @return true or false
     */

    default boolean responseEquals(Map<String, String> responseFields, Response response) {

        Iterator var3 = responseFields.entrySet().iterator();
        while (var3.hasNext()) {
            Map.Entry<String, Object> expected = (Map.Entry) var3.next();
            LOGGER.info("Expected : " + expected.getKey() + " = " + expected.getValue());

            Object responseValue = response.body().jsonPath().getJsonObject(expected.getKey());
            LOGGER.info("Actual : " + expected.getKey() + " = " + responseValue);

            if (responseNullCheck(expected, responseValue)) return false;
        }
        return true;
    }


    /**
     * This method is to validate the status code
     *
     * @param status   is the status code which needs to be validated
     * @param response
     * @return true or false
     */

    default boolean statusCodeIs(Integer status, Response response) {
        LOGGER.info("Status code expected: {} \n Actual status code {}", status, response.getStatusCode());
        if (response.getStatusCode() != status) {
            LOGGER.error("Status code expected: {} \n Actual status code {}", status, response.getStatusCode());
            return false;
        } else {
            return true;
        }
    }


    /**
     * This method is to validate the response, when the response is an arraylist
     *
     * @param jsonStr is the response as a string
     * @param index   is the index of the arraylist in which validation needs to be done
     * @param values  is the Map which has the key and value to be validated
     * @return true or false
     */

    default Boolean responseArrayListEquals(String jsonStr, int index, Map<String, String> values) {

        JSONArray jsonArray = new JSONArray(jsonStr);
        JSONObject jsonObject = jsonArray.getJSONObject(index);
        Iterator iterator = values.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Object> expected = (Map.Entry) iterator.next();
            LOGGER.info("Expected : " + expected.getKey() + " = " + expected.getValue());

            Object responseValue = jsonObject.get(expected.getKey());
            LOGGER.info("Actual : " + expected.getKey() + " = " + responseValue);

            if (responseNullCheck(expected, responseValue)) return false;
        }
        return true;
    }

    /**
     * Method to check if expected or actual response is null
     *
     * @param expected
     * @param responseValue
     * @return true if null
     */
    default boolean responseNullCheck(Map.Entry<String, Object> expected, Object responseValue) {
        if ((responseValue != null && expected.getValue() != null) && (!responseValue.equals(expected.getValue()))) {
            LOGGER.error("Expected value: {} \n Actual value: {}", expected.getValue(), responseValue);
            return true;
        } else if ((responseValue == null && expected.getValue() != null) || (responseValue != null && expected.getValue() == null)) {
            LOGGER.info("Expected value: {} \n Actual value: {}", expected.getValue(), responseValue);
            return true;
        }
        return false;
    }

    /**
     * This method is to capture value of specific attribute in the response
     *
     * @param response is the response
     * @param jsonPath is the path of the attribute
     * @return the value of the attribute as a string
     */

    default Object getObjectValue(Response response, String jsonPath) throws Exception {
        try {
            LOGGER.info("Value found on Json Path {} is: {}", jsonPath, response.body().jsonPath().getJsonObject(jsonPath).toString());
            return response.body().jsonPath().getJsonObject(jsonPath);
        } catch (Exception e) {
            LOGGER.error("Not able to find given json path: {}", jsonPath);
            throw e;
        }
    }

    /**
     * Method to get updated String body
     *
     * @param body request body
     * @param key is the json path at which the value needs to be updated
     * @param value
     * @param index
     * @return updated String body
     */

    default String updateValueInBody(String body, String key, Object value, int index) {
        JSONArray jsonArray = new JSONArray(body);
        JSONObject jsonObject = jsonArray.getJSONObject(index);

        LOGGER.info("Body before update: {} ", body);

        if (value.getClass().equals(String.class)) {
            jsonObject.put(key, value.toString());
        } else if (value.getClass().equals(Integer.class)) {
            jsonObject.put(key, value);
        } else if (value.getClass().equals(Long.class)) {
            jsonObject.put(key, (value));
        } else {
            jsonObject.put(key, (value));
        }
        body=jsonArray.toString();
        LOGGER.info("Updated body: {}",body);
        return body;
    }



}
