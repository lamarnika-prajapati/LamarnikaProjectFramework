package com.org.project.automation.listeners;

import com.org.project.automation.readers.ConfigFileReader;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;

public class ElasticHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticHelper.class);

    /**
     * Method to post test case execution status to ElasticSearch
     *
     * @param testCaseInfo
     */

    public static void saveTestExecutionStatus(TestCaseInfo testCaseInfo) {

        ConfigFileReader configFileReader = new ConfigFileReader();
        TransportClient transportClient;

        try {
            transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(configFileReader.getElasticSearchHost()), configFileReader.getElasticSearchPort()));
            LOGGER.info("initiated elastic search transport client");
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("testCase", testCaseInfo.getTestCaseTitle())
                    .field("testSuite", testCaseInfo.getTestSuite())
                    .field("result", testCaseInfo.getTestExecutionStatus())
                    .field("failureReason", testCaseInfo.getTestFailureReason())
                    .field("environment", testCaseInfo.getTestEnvironment())
                    .field("os", testCaseInfo.getOperatingSystem())
                    .field("browser", testCaseInfo.getBrowserName())
                    .field("failureStackTrace", testCaseInfo.getTestFailStackTrace())
                    .field("executionTime", testCaseInfo.getTestExecutionTotalTime())
                    .timeField("@timestamp", testCaseInfo.getTestExecutionTimeStamp())
                    .field("build", testCaseInfo.getTestExecutionBuild())
                    .endObject();
            IndexResponse response = transportClient.prepareIndex(configFileReader.getElasticSearchIndex(), "posts")
                    .setSource(builder).get();
            LOGGER.info("posted test case info to elastic search");
            transportClient.close();
            LOGGER.info("closing elastic search transport client");
        } catch (IOException ex) {
            LOGGER.info("error while posting test case info to elastic search: {}", ex.getStackTrace());
        }

    }


}
