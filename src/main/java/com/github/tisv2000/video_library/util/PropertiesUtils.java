package com.github.tisv2000.video_library.util;

import lombok.SneakyThrows;

import java.util.Properties;

public class PropertiesUtils {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    @SneakyThrows
    private static void loadProperties() {
        try (var inputStream =
                      PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");) {
            PROPERTIES.load(inputStream);
        }
    }

    private PropertiesUtils() {

    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
