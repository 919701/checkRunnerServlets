package ru.clevertec.checkrunnerservlets.util;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();
    private final static String PROPERTIES_NAME = "application.properties";

    static {
        loadProperties();
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_NAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private PropertiesUtil() {
    }
}
