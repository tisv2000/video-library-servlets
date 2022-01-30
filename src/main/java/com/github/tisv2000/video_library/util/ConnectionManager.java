package com.github.tisv2000.video_library.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;

@UtilityClass
public class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";

    static {
        initDriver();
    }

    @SneakyThrows
    private static void initDriver() {
        Class.forName("org.postgresql.Driver");
    }

    @SneakyThrows
    public static Connection get() {
        return DriverManager.getConnection(
                PropertiesUtils.get(URL_KEY),
                PropertiesUtils.get(USERNAME_KEY),
                PropertiesUtils.get(PASSWORD_KEY)
        );
    }

}
