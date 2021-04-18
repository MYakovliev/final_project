package com.epam.web.dao.impl;

import com.epam.web.dao.AdminDao;
import com.epam.web.dao.DaoException;
import com.epam.web.pool.ConnectionPool;
import com.epam.web.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    private static final Logger logger = LogManager.getLogger();
    private static final AdminDao instance = new AdminDaoImpl();
    private static final String SET_BAN_STATUS_USER_BY_ID_STATEMENT = "UPDATE users SET isBanned=? WHERE idusers=?";
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private AdminDaoImpl() {
    }

    public static AdminDao getInstance() {
        return instance;
    }


    @Override
    public void ban(long userId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_BAN_STATUS_USER_BY_ID_STATEMENT)) {
            statement.setBoolean(1, true);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void unban(long userId) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SET_BAN_STATUS_USER_BY_ID_STATEMENT)) {
            statement.setBoolean(1, false);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }
}
