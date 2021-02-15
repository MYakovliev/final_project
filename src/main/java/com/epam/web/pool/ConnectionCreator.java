package com.epam.web.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;


public class ConnectionCreator {
    private static final Logger logger = LogManager.getLogger();
    private static final String URL = "jdbc:mysql://localhost:3306/auction";
    private static final String USER = "root";
    private static final String PASSWORD = "1234567890";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.fatal("Can't load driver", e);
            throw new ExceptionInInitializerError();
        }
    }

    static Connection createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwable) {
            logger.fatal(throwable);
            throw new ExceptionInInitializerError();
        }
        return connection;
    }

    private ConnectionCreator() {
    }
}
