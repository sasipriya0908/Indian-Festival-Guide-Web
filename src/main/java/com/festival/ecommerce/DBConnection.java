package com.festival.ecommerce;

import java.sql.Connection;
import java.sql.SQLException;

import com.festival.util.DBConfig;

public class DBConnection {

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DBConfig.getConnection("indian_festival_store");
    }
}
