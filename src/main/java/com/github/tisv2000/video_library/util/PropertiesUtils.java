package com.github.tisv2000.video_library.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesUtils {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadPropertiesFromPropertiesFile();
    }

    @SneakyThrows
    private static void loadPropertiesFromPropertiesFile() {
        try (var inputStream =
                     PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");) {
            PROPERTIES.load(inputStream);
        }
    }

    public static String get3(String key) {
        return Optional.ofNullable(System.getProperty(key))
                .orElseGet(() -> Optional.ofNullable(System.getenv(key))
                        .orElseGet(() -> PROPERTIES.getProperty(key)));
    }

    public static String get2(String key) {
        String value;
        if ((value = System.getProperty(key)) != null) return value;
        if ((value = System.getenv(key)) != null) return value;
        return PROPERTIES.getProperty(key);
    }

    public static String get(String key) {
        String property = System.getProperty(key);
        if (property == null) {
            property = System.getenv(key);
            if (property == null) {
                property = PROPERTIES.getProperty(key);
            }
        }
        return property;
    }



}
