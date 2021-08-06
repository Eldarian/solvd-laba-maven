package com.eldarian.solvdelivery.database;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnector {
    private static final Logger LOGGER = Logger.getLogger(SQLConnector.class);

    public static Connection connect() {
        Connection connection = null;
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/postgresql.properties")) {
            Properties sqlProperties = new Properties();
            sqlProperties.load(fileInputStream);
            connection = DriverManager.getConnection(
                    sqlProperties.getProperty("url"),
                    sqlProperties.getProperty("user"),
                    sqlProperties.getProperty("password"));
            connection.setAutoCommit(false);
        }
        catch (IOException e) {
            LOGGER.error("Reading from sql.properties file failed");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
