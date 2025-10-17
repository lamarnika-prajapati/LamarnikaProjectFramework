package com.org.project.automation.rest;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public interface APITestDataReader {

    Logger LOGGER = LoggerFactory.getLogger(APITestDataReader.class);

    /**
     * This method is used to retrieve data of specific service
     *
     * @param value   is the name of the service
     * @param modules is the yml data
     * @return a List of data related to the service
     */

    default List<Map<String, Object>> getServiceData(String value, Map<String, Object> modules) {
        ObjectMapper objectMapper = new ObjectMapper();
        return (List<Map<String, Object>>) objectMapper.convertValue(modules.get(value), List.class);
    }

    /**
     * Method to get URL
     *
     * @param service
     * @return URL
     */
    default String getURL(List<Map<String, Object>> service) {
        return (String) service.get(0).get("URl");
    }

    /**
     * Method to get request headers
     *
     * @param service
     * @return request headers
     */
    default Map getHeader(List<Map<String, Object>> service) {
        return (Map) service.get(1).get("Headers");
    }

    /**
     * Method to get request body
     *
     * @param service
     * @return request body
     */
    default String getBody(List<Map<String, Object>> service) {
        return (String) service.get(3).get("Postbody");
    }

    /**
     * Method to get list of expected assertions
     *
     * @param service
     * @return map of assertions
     */
    default Map getAssertion(List<Map<String, Object>> service) {
        return (Map) service.get(2).get("Assertions");
    }


}
