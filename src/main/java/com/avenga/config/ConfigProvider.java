package com.avenga.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigProvider {

    private static final String PROPERTY_FILE_PATH = "src/test/resources/config/config.properties";
    private final Properties properties;
    private static ConfigProvider configProvider;

    private ConfigProvider() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PROPERTY_FILE_PATH));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + PROPERTY_FILE_PATH);
        }
    }

    public static ConfigProvider getInstance() {
        if(configProvider == null) {
            configProvider = new ConfigProvider();
        }
        return configProvider;
    }

    public String getBaseUrl() {
        // Check for system property or environment variable override
        String envBaseUrl = System.getProperty("baseUrl");
        if (envBaseUrl == null) {
            envBaseUrl = System.getenv("BASE_URL");
        }
        if (envBaseUrl != null && !envBaseUrl.isEmpty()) {
            return envBaseUrl;
        }

        // Fallback to config file
        String baseUrl = properties.getProperty("base_Url");
        if (baseUrl != null) return baseUrl;
        else throw new RuntimeException("base_Url not specified in the Configuration.properties file.");
    }
}