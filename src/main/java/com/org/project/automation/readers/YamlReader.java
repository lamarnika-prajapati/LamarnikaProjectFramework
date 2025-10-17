package com.org.project.automation.readers;

import com.org.project.automation.webdriver.ResourceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class YamlReader {
    public static final Logger LOGGER = LoggerFactory.getLogger(YamlReader.class);
    private String filePath;

    public YamlReader() {
        filePath = new ConfigFileReader().getDefaultYAMLFilePath();
    }

    public YamlReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method to get YAML file content in Map
     *
     * @return map with key, value pairs
     */

    public Map<String, Object> getYamlMaps() {
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMaps = null;
        File yamlFile = new File(ResourceHelper.getDefaultResourcesLocation() + filePath);

//        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
//        try (Reader yamlFile = new BufferedReader(new FileReader(new File(new File(filePath).getAbsolutePath())))) {

        try (Reader reader = new FileReader(yamlFile)) {
            yamlMaps = (Map<String, Object>) yaml.load(reader);
        } catch (FileNotFoundException e) {
            LOGGER.error("Exception while reading YAML file ", e);
        } catch (IOException e) {
            LOGGER.error("Exception while reading YAML file ", e);
        }
        return yamlMaps;
    }


    public static void main(String[] args) {
        Map<String, Object> yamlMaps = new YamlReader().getYamlMaps();
        List<Map<String, Object>> moduleName = (List<Map<String, Object>>) yamlMaps.get("application_urls");
        for (int i = 0; i < moduleName.size(); i++) {
            if (moduleName.get(i).containsKey("UAT1")) {
                System.out.println(moduleName.get(i).get("UAT1"));
                ;
            }
        }


    }


}
