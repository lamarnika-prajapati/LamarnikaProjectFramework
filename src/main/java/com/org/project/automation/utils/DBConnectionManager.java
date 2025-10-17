package com.org.project.automation.utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.org.project.automation.readers.ConfigFileReader;
import com.org.project.automation.webdriver.ResourceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * This class performs database operations
 */
public class DBConnectionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBConnectionManager.class);
    private String databaseClass;
    private String url;
    private String userName;
    private String password;
    private ComboPooledDataSource comboPooledDataSource;
    private String dbConfigurationFilePath;

    public DBConnectionManager() {
        Properties prop = new Properties();
        dbConfigurationFilePath = new ConfigFileReader().getDefaultDBConfigurationFilePath();

//        try (InputStream inputStream = new FileInputStream(new File(getClass().getClassLoader().getResource(ResourceHelper.getDefaultResourcesLocation() + dbConfigurationFilePath).getFile()))) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceHelper.getDefaultResourcesLocation() + dbConfigurationFilePath))) {
            prop.load(reader);
            databaseClass = prop.getProperty("db.driver.class.name");
            url = prop.getProperty("db.driver.url");
            userName = prop.getProperty("db.user.name");
            password = EncryptionManager.decrypt(prop.getProperty("db.user.password"));
            comboPooledDataSource = getDataSource();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public DBConnectionManager(String propertiesFile) {
        Properties prop = new Properties();

        try (BufferedReader reader = new BufferedReader(new FileReader(ResourceHelper.getDefaultResourcesLocation() + propertiesFile))) {
            prop.load(reader);
            databaseClass = prop.getProperty("db.driver.class.name");
            url = prop.getProperty("db.driver.url");
            userName = prop.getProperty("db.user.name");
            password = EncryptionManager.decrypt(prop.getProperty("db.user.password"));
            comboPooledDataSource = getDataSource();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public DBConnectionManager(String url, String userName, String password, String dbClass) {
        try {
            this.url = url;
            this.userName = userName;
            this.password = password;
            this.databaseClass = dbClass;
            comboPooledDataSource = getDataSource();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private ComboPooledDataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(userName);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setDriverClass(databaseClass);
        comboPooledDataSource.setInitialPoolSize(Constants.INITIAL_POOL_SIZE);
        comboPooledDataSource.setMinPoolSize(Constants.MIN_POOL_SIZE);
        comboPooledDataSource.setAcquireIncrement(Constants.ACQUIRE_INCREMENT);
        comboPooledDataSource.setMaxPoolSize(Constants.MAX_POOL_SIZE);
        comboPooledDataSource.setMaxStatements(Constants.MAX_STATEMENTS);
        return comboPooledDataSource;
    }

    /**
     * Converts the ResultSet to a List of Maps, where each Map represents a row with column Names and column values
     *
     * @param query
     * @return rows in Map
     * @throws java.sql.SQLException
     */
    public List<Map<String, Object>> executeQuery(String query) {
        List<Map<String, Object>> rows = new ArrayList<>();
        try (Connection connection = comboPooledDataSource.getConnection()) {
            Long startTime = System.currentTimeMillis();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int totalColumns = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>(totalColumns);
                for (int i = 1; i <= totalColumns; ++i) {
                    row.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
                }
                rows.add(row);
            }
            long timeElapsed = System.currentTimeMillis() - startTime;
            LOGGER.info("Query executed: {} in {} seconds", query, String.valueOf(timeElapsed / 1000));
            return rows;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return rows;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String query = "select Personid from tperson where FIRST_NAME='RAJAT'";

        List<Map<String, Object>> result = new DBConnectionManager("testdata/db.properties").executeQuery(query);
//        List<Map<String, Object>> result = new DBConnectionManager().executeQuery(query);

        System.out.println(result.get(0).get("PERSONID"));

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String userName = "system";
        String password = "admin";
        String databaseClass = "oracle.jdbc.driver.OracleDriver";
        System.out.println(new DBConnectionManager(url, userName, password, databaseClass).executeQuery(query).get(0).get("PERSONID"));


    }
}
