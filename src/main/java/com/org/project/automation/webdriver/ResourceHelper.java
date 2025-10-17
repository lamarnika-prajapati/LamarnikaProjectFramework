package com.org.project.automation.webdriver;


/**
 * This class helps automation framework to calculate project directory path with ease
 */
public class ResourceHelper {

    /**
     * This method returns project root directory path appended with path argument
     *
     * @param path
     * @return calculated absolute file path
     */
    public static String getResourcePath(String path) {
        String basePath = System.getProperty("user.dir");
        return basePath + "/" + path;
    }

    public static String getDefaultResourcesLocation() {
        return ResourceHelper.getResourcePath("src/test/resources/");
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir") + " << Printing current directory");
        System.out.println(getDefaultResourcesLocation() + " << Printing current directory");

    }
}
