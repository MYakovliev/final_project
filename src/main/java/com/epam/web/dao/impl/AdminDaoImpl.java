package com.epam.web.dao.impl;

import com.epam.web.dao.AdminDao;
import com.epam.web.dao.DaoException;
import com.epam.web.pool.ConnectionPool;
import com.epam.web.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class AdminDaoImpl implements AdminDao {
    private static final Logger logger = LogManager.getLogger();
    private static final AdminDao instance = new AdminDaoImpl();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String SET_BAN_STATUS_USER_BY_ID_STATEMENT = "UPDATE users SET isBanned=? WHERE idusers=?";
    private static final String SUBMIT_WINNER_STATEMENT =
            "UPDATE bid_history SET status=(SELECT idstatus FROM status WHERE status.status='WON') " +
                    "WHERE id_lot=? AND id_buyer=?";
    private static final String CHANGE_SELLER_BALANCE = "UPDATE users SET balance=balance + " +
            "(SELECT MAX(bid) FROM bid_history WHERE id_lot=? AND id_buyer=?) " +
            "WHERE idusers=(SELECT seller FROM lots WHERE idlots=?)";

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

    @Override
    public void submitWinner(long userId, long lotId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SUBMIT_WINNER_STATEMENT);

            statement.setLong(1, lotId);
            statement.setLong(2, userId);
            statement.executeUpdate();
            statement.close();
            statement = connection.prepareStatement(CHANGE_SELLER_BALANCE);
            statement.setLong(1, lotId);
            statement.setLong(2, userId);
            statement.setLong(3, lotId);
           statement.executeUpdate();
            connection.commit();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error(ex);
                    throw new DaoException(ex);
                }
            }
            throw new DaoException(e);
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwable) {
                    logger.error(throwable);
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e);
                    throw new DaoException(e);
                }
            }
        }
    }
}
