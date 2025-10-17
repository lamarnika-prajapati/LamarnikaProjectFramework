package com.org.project.automation.readers;

import com.org.project.automation.enums.DriverType;
import com.org.project.automation.enums.EnvironmentType;
import com.org.project.automation.enums.OSType;
import com.org.project.automation.webdriver.ResourceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * This class handles configuration.properties_bk read operations
 */
public class ConfigFileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFileReader.class);
    private Properties properties;

    public static void main(String[] args) {
        System.out.println(new ConfigFileReader().getBuildName());
    }

    public ConfigFileReader() {
//        File configFile= new File(getClass().getClassLoader().getResource("configuration.properties_bk").getFile());
        File configFile = new File(ResourceHelper.getDefaultResourcesLocation() + "configuration.properties");

        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("Can't read configuration file from given location: {}", configFile.getAbsolutePath(), e);
            throw new RuntimeException("config properties file not found " + configFile.getAbsolutePath(), e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }

    }



    /**
     * This method returns implicit wait value
     *
     * @return implicit wait
     */

    public long getImplicitWait() {
        String implicitWait = properties.getProperty("driver.implicitwait");
        if (implicitWait != null) {
            return Long.parseLong(implicitWait);
        } else {
            LOGGER.error("implicit Wait not specified in the Configuration.properties file. ");
            throw new RuntimeException("implicit Wait not specified in the Configuration.properties file.");
        }
    }

    /**
     * This method returns test application base url
     *
     * @return base url
     */

    public String getBaseUrl() {
        String baseURL = properties.getProperty("athena.baseurl.of.application.tobe.automated");
        if (baseURL != null) {
            return baseURL;
        } else {
            LOGGER.error("base url not specified in the Configuration.properties file. ");
            throw new RuntimeException("base url not specified in the Configuration.properties file. ");
        }
    }

    /**
     * This method returns driver executable path
     *
     * @return driver path
     */
    public String getDriverPath() {
        String driverPath = properties.getProperty("driver.windows.path");
        if (driverPath != null) {
            return ResourceHelper.getResourcePath(driverPath);
        } else {
            LOGGER.error("Driver path not specified in the Configuration.properties file for Key:driverPath");
            throw new RuntimeException("Driver path not specified in the Configuration.properties file for Key:driverPath");
        }
    }

    /**
     * This method returns browser name
     *
     * @return browser name
     */

    public DriverType getBrowser() {
        String browserName = properties.getProperty("driver.browser.name");
        if (browserName == null || browserName.equals("chrome")) {
            return DriverType.CHROME;
        } else if (browserName.equalsIgnoreCase("firefox")) {
            return DriverType.FIREFOX;
        } else if (browserName.equals("iexplorer")) {
            return DriverType.INTERNETEXPLORER;
        } else {
            LOGGER.error("Browser Name Key value in Configuration.properties is not matched : {}", browserName);
            throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
        }
    }

    /**
     * This method returns browser window size
     *
     * @return browser window size
     */
    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("driver.window.maximize");
        if (windowSize != null) return Boolean.valueOf(windowSize);
        return true;
    }

    /**
     * This method returns the environment
     *
     * @return environment name
     */
    public EnvironmentType getEnvironment() {
        String environmentName = properties.getProperty("environment");
        if (environmentName == null || environmentName.equalsIgnoreCase("local")) {
            return EnvironmentType.LOCAL;
        } else if (environmentName.equals("remote")) {
            return EnvironmentType.REMOTE;
        } else {
            LOGGER.error("Environment Type Key value is not matched in Configuration.properties file : {}", environmentName);
            throw new RuntimeException("Environment Type Key value is not matched in Configuration.properties file :" + environmentName);
        }
    }

    /**
     * This method returns screenshot save path
     *
     * @return screenshot save directory
     */

    public String getScreenshotPath() {
        String screenshotPath = properties.getProperty("driver.screenshot.path");
        if (screenshotPath != null) {
            return ResourceHelper.getResourcePath(screenshotPath);
        } else {
            LOGGER.error("Screenshot path is not specified in the configuration.properties_bk file");
            throw new RuntimeException("Screenshot path is not specified in the configuration.properties_bk file");
        }
    }

    /**
     * This method returns default test data excel file path
     *
     * @return filepath
     */

    public String getDefaultExcelFilePath() {
        String defaultExcelFilePath = properties.getProperty("default.excel.file.path");
        if (defaultExcelFilePath != null) {
            return defaultExcelFilePath;
        } else {
            LOGGER.error("Default test data excel file path is not specified in the configuration.properties_bk file. ");
            throw new RuntimeException("Default test data excel file path is not specified in the configuration.properties_bk file. ");
        }
    }

    /**
     * This method returns default test data yaml file path
     *
     * @return filepath
     */

    public String getDefaultYAMLFilePath() {
        String defaultYAMLFilePath = properties.getProperty("default.yaml.file.path");
        if (defaultYAMLFilePath != null) {
            return defaultYAMLFilePath;
        } else {
            LOGGER.error("Default test data YAML file path is not specified in the configuration.properties_bk file. ");
            throw new RuntimeException("Default test data YAML file path is not specified in the configuration.properties_bk file. ");
        }
    }

    /**
     * This method returns default test data JSON file path
     *
     * @return filepath
     */

    public String getDefaultJSONFilePath() {
        String defaultJSONFilePath = properties.getProperty("default.json.file.path");
        if (defaultJSONFilePath != null) {
            return defaultJSONFilePath;
        } else {
            LOGGER.error("Default test data JSON file path is not specified in the configuration.properties_bk file. ");
            throw new RuntimeException("Default test data JSON file path is not specified in the configuration.properties_bk file. ");
        }
    }

    /**
     * This method returns default db configuration file path
     *
     * @return filepath
     */

    public String getDefaultDBConfigurationFilePath() {
        String defaultDBConfigurationFilePath = properties.getProperty("default.db.file.path");
        if (defaultDBConfigurationFilePath != null) {
            return defaultDBConfigurationFilePath;
        } else {
            LOGGER.error("Default db configuration file path is not specified in the configuration.properties file. ");
            throw new RuntimeException("Default db configuration file path is not specified in the configuration.properties file. ");
        }
    }

    /**
     * This method returns execution machine OS name
     *
     * @return OS name
     */

    public OSType getOS() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            return OSType.WINDOWS;
        } else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
            return OSType.LINUX;
        } else if (OS.contains("mac")) {
            return OSType.MAC;
        } else {
            LOGGER.error("OS is not supported : {}", OS);
            throw new RuntimeException("OS is not supported : " + OS);
        }
    }

    /**
     * This method returns test execution environment
     *
     * @return environment name
     */
    public String getTestEnvironment() {
        String testEnvironmentName = properties.getProperty("test.env");
        if (testEnvironmentName != null) {
            return testEnvironmentName;
        } else {
            LOGGER.error("Test Environment property is not specified in the configuration.properties_bk file");
            throw new RuntimeException("Test Environment property is not specified in the configuration.properties_bk file");
        }
    }

    /**
     * This method returns build name
     *
     * @return build name
     */
    public String getBuildName() {
        String testBuildName = properties.getProperty("test.build");
        if (testBuildName != null) {
            return testBuildName;
        } else {
            LOGGER.error("Test Build property is not specified in configuration.properties_bk file. ");
            throw new RuntimeException("Test Build property is not specified in configuration.properties_bk file. ");
        }
    }

    /**
     * This method returns elastic search host name
     *
     * @return host name
     */

    public String getElasticSearchHost() {
        String elasticSearchHost = properties.getProperty("report.elasticsearch.host");
        if (elasticSearchHost != null) {
            return elasticSearchHost;
        } else {
            LOGGER.error("ElasticSearch host property is not specified in the configuration.properties_bk file");
            throw new RuntimeException("ElasticSearch host property is not specified in the configuration.properties_bk file");
        }
    }

    /**
     * This method returns elastic search port number
     *
     * @return port number
     */

    public int getElasticSearchPort() {
        int elasticSearchPort = Integer.parseInt(properties.getProperty("report.elasticsearch.port"));
        if (elasticSearchPort != 0) {
            return elasticSearchPort;
        } else {
            LOGGER.error("ElasticSearch port property is not specified in the configuration.properties_bk file. ");
            throw new RuntimeException("ElasticSearch port property is not specified in the configuration.properties_bk file. ");
        }
    }

    /**
     * This method returns elastic search index
     *
     * @return index name
     */

    public String getElasticSearchIndex() {
        String elasticSearchIndex = properties.getProperty("report.elasticsearch.index");
        if (elasticSearchIndex != null) {
            return elasticSearchIndex;
        } else {
            LOGGER.error("ElasticSearch index property is not specified in the configuration.properties_bk file. ");
            throw new RuntimeException("ElasticSearch index property is not specified in the configuration.properties_bk file. ");
        }
    }

    /**
     * Method to check if elastic search reporting is enabled or not
     *
     * @return true if enabled false otherwise
     */
    public Boolean isElasticSearchReportActive() {
        String elasticSearchReportActive = properties.getProperty("report.elasticsearch.enabled");
        if (elasticSearchReportActive != null) {
            return elasticSearchReportActive.equals("true");
        } else {
            LOGGER.error("Elastic Search report enabled property is not specified in the configuration.properties_bk file. ");
            throw new RuntimeException("Elastic Search report enabled property is not specified in the configuration.properties_bk file. ");
        }
    }

    /**
     * This method returns remote driver url
     *
     * @return remote driver url
     */

    public String getRemoteDriverUrl() {
        String remoteDriverUrl = properties.getProperty("driver.remote.url");
        if (remoteDriverUrl != null) {
            return remoteDriverUrl;
        } else {
            LOGGER.error("Remote driver url property is not specified in the configuration.properties_bk file");
            throw new RuntimeException("Remote driver url property is not specified in the configuration.properties_bk file");
        }
    }

    /**
     * Method to check if remote driver execution is enabled or not
     *
     * @return true if enabled false otherwise
     */
    public Boolean isRemoteDriverEnabled() {
        String remoteDriverEnabled = properties.getProperty("driver.remote.enabled");
        if (remoteDriverEnabled != null) {
            return remoteDriverEnabled.equals("true");
        } else {
            LOGGER.error("Remote driver enabled property is not specified in the configuration.properties_bk file");
            throw new RuntimeException("Remote driver enabled property is not specified in the configuration.properties_bk file");
        }
    }

}


