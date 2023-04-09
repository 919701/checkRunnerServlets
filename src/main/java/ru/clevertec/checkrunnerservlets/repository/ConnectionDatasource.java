package ru.clevertec.checkrunnerservlets.repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionDatasource {

    private final String driver;
    private final String uri;
    private final String user;
    private final String password;

    private static volatile ConnectionDatasource instance;

    public ConnectionDatasource(String driver, String uri, String user, String password) {
        this.driver = driver;
        this.uri = uri;
        this.user = user;
        this.password = password;

        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static ConnectionDatasource getInstance(String driver, String uri, String user, String password) {
        if (instance == null) {
            synchronized (ConnectionDatasource.class) {
                if (instance == null) {
                    instance = new ConnectionDatasource(driver, uri, user, password);
                }
            }
        }
        return instance;
    }
}
