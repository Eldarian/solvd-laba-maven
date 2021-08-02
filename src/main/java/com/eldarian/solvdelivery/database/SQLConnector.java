package com.eldarian.solvdelivery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
    private static final String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "12345";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
