package com.org.project.automation.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manages different test data file reader instances
 */

public class FileReaderManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileReaderManager.class);
    private static FileReaderManager fileReaderManager;
    private static ConfigFileReader configFileReader;
    private static ExcelReader excelReader;
    private static JSONReader jsonReader;
    private static YamlReader yamlReader;

    static {
        fileReaderManager = new FileReaderManager();
    }

    public FileReaderManager() {
    }

    /**
     * This method creates/returns FileReaderManager instance
     *
     * @return FileReaderManager instance
     */
   public static FileReaderManager getInstance(){
       return fileReaderManager;
   }

/**
 * This method creates/returns config file reader instance
 *
 * @return config file reader instance
 */

public ConfigFileReader getConfigReader(){
    if (configFileReader == null)  configFileReader = new ConfigFileReader();
    return configFileReader;
}

    /**
     * This method creates/returns excel file reader instance
     *
     * @return excel file reader instance
     */

    public ExcelReader getExcelReader(){
        if (excelReader == null)  excelReader = new ExcelReader();
        return excelReader;
    }

    /**
     * This method creates/returns JSON file reader instance
     *
     * @return JSON file reader instance
     */

    public JSONReader getJsonReader(){
        if (jsonReader == null)  jsonReader = new JSONReader();
        return jsonReader;
    }

    /**
     * This method creates/returns Yaml file reader instance
     *
     * @return Yaml file reader instance
     */

    public YamlReader getYamlReader(){
        if (yamlReader == null)  yamlReader = new YamlReader();
        return yamlReader;
    }


}
