package com.epam.web.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 *  The connection pool stores and gives connections to dao layer
 *
 * @author Nikita Yakovlev
 *
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int MIN_POOL_SIZE = Integer.parseInt(DatabaseResourceManager.getParameter("minPoolSize"));
    private static final int MAX_POOL_SIZE = Integer.parseInt(DatabaseResourceManager.getParameter("maxPoolSize"));
    private static ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<Connection> freeConnections = new LinkedBlockingDeque<>(MIN_POOL_SIZE);
    private BlockingQueue<Connection> givenConnections = new LinkedBlockingDeque<>(MAX_POOL_SIZE);

    public static ConnectionPool getInstance() {
        return instance;
    }

    /**
     * Connection pool constructor that initializes free connections
     */
    private ConnectionPool() {
        for (int i = 0; i < MIN_POOL_SIZE; i++) {
            Connection connection = ConnectionCreator.createConnection();
            Connection proxyConnection = new ProxyConnection(connection);
            freeConnections.add(proxyConnection);
        }
    }

    /**
     * Method to get connection to database
     *
     * @return Connection got from free or created if possible @see java.sql.Connection;
     * @throws ConnectionPoolException
     */
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        if (!freeConnections.isEmpty()) {
            try {
                connection = freeConnections.take();
            } catch (InterruptedException e) {
                logger.error("exception in taking connection from not empty connection pool", e);
                throw new ConnectionPoolException(e);
            }
        } else if (givenConnections.size() < MAX_POOL_SIZE) {
            Connection newConnection = ConnectionCreator.createConnection();
            connection = new ProxyConnection(newConnection);
        } else {
            throw new ConnectionPoolException("connection limit has reached");
        }
        givenConnections.offer(connection);
        return connection;
    }


    /**
     * Method to return connection to connection pool or close it if not possible to return
     *
     * @param connection to return
     */
    public void releaseConnection(Connection connection) {
        if (!(connection instanceof ProxyConnection)) {
            logger.error("connection is not proxy");
        } else if (!givenConnections.remove(connection)) {
            logger.error("Couldn't remove connection from given");
        } else if (!freeConnections.offer(connection)) {
            try {
                ((ProxyConnection) connection).reallyClose();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Method to close connection pool that closes all connections
     */
    public void destroyPool() {
        try {
            for (int i = 0; i < freeConnections.size() + givenConnections.size(); i++) {
                Connection connection = freeConnections.take();
                ((ProxyConnection) connection).reallyClose();
            }
            freeConnections.clear();
        } catch (SQLException | InterruptedException e) {
            logger.error(e);
        }
    }
}
