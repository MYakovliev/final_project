package com.epam.web.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private static final Logger logger = LogManager.getLogger();
    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    static {
        try {

            String driver = DatabaseResourceManager.getParameter(DRIVER);
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.fatal("Can't load driver", e);
            throw new ExceptionInInitializerError();
        }
    }

    static Connection createConnection() {
        Connection connection;
        try {
            String url = DatabaseResourceManager.getParameter(URL);
            String user = DatabaseResourceManager.getParameter(USER);
            String password = DatabaseResourceManager.getParameter(PASSWORD);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwable) {
            logger.fatal(throwable);
            throw new ExceptionInInitializerError();
        }
        return connection;
    }

    private ConnectionCreator() {
    }
}
