package com.festival.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Shared MySQL configuration for the Indian Festival Guide final project. */
public final class DBConfig {

    private DBConfig() {
    }

    private static final String HOST =
            getSetting("DB_HOST", "db.host", "localhost");

    private static final String PORT =
            getSetting("DB_PORT", "db.port", "3306");

    private static final String USER =
            getSetting("DB_USER", "db.user", "root");

    private static final String PASSWORD =
            getSetting("DB_PASSWORD", "db.password", "root");

    public static Connection getConnection(String database) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Connector/J is not available.", e);
        }

        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + database
                + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        return DriverManager.getConnection(url, USER, PASSWORD);
    }

    private static String getSetting(String envName,
                                     String systemProperty,
                                     String defaultValue) {
        String property = System.getProperty(systemProperty);
        if (property != null && !property.isBlank()) {
            return property;
        }

        String env = System.getenv(envName);
        if (env != null && !env.isBlank()) {
            return env;
        }

        return defaultValue;
    }
}
