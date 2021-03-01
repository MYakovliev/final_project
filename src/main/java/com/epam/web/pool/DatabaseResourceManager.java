package com.epam.web.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class DatabaseResourceManager {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String PROPERTIES_NAME = "dbproperties.properties";

    private DatabaseResourceManager(){}

    static {
        try {
            properties.load(ConnectionCreator.class.getClassLoader().getResourceAsStream(PROPERTIES_NAME));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    static String getParameter(String key){
        return properties.getProperty(key);
    }
}
