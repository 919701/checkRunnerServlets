package ru.clevertec.checkrunnerservlets.repository;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class ConnectionFactory {

    private static final String CONFIG_FILE_NAME = "app.properties";
    private static ConnectionDatasource datasource;

    static {
        Properties appProperties = new Properties();
        try {
            appProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        try {
            datasource = ConnectionDatasource.getInstance(
                    appProperties.getProperty("postgres.driver"),
                    appProperties.getProperty("postgres.uri"),
                    appProperties.getProperty("postgres.user"),
                    appProperties.getProperty("postgres.password"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

}
