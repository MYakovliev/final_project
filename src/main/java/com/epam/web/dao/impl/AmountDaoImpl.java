package com.epam.web.dao.impl;

import com.epam.web.dao.AmountDao;
import com.epam.web.dao.DaoException;
import com.epam.web.pool.ConnectionPool;
import com.epam.web.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AmountDaoImpl implements AmountDao {
    private static final Logger logger = LogManager.getLogger();
    private static final AmountDaoImpl instance = new AmountDaoImpl();
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String FIND_ALL_LOT_STATEMENT = "SELECT COUNT(*) FROM lots";
    private static final String ANY_AMOUNT_SQL_CHARACTER = "%";
    private static final String FIND_LOT_BY_NAME_STATEMENT = "SELECT COUNT(*) FROM lots WHERE name LIKE ?";
    private static final String FIND_LOT_BY_SELLER_ID_STATEMENT = "SELECT COUNT(*) FROM lots WHERE seller=?";
    private static final String FIND_WON_LOT_BY_BUYER_ID_STATEMENT = "SELECT COUNT(DISTINCT id_lot) FROM bid_history WHERE id_buyer=?";
    private static final String FIND_ALL_USERS_STATEMENT = "SELECT COUNT(*) FROM users ";
    private static final String FIND_USERS_BY_NAME_STATEMENT = "SELECT COUNT(*) FROM users WHERE name=?";
    private static final String FIND_ACTIVE_LOT_STATEMENT =
            "SELECT COUNT(*) FROM lots WHERE end_time > NOW() AND NOW() > start_time";


    private AmountDaoImpl() {
    }

    public static AmountDaoImpl getInstance() {
        return instance;
    }


    @Override
    public int findAllLotAmount() throws DaoException {
        int amount = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_LOT_STATEMENT)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return amount;
    }

    @Override
    public int findActiveLotAmount() throws DaoException {
        int amount = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ACTIVE_LOT_STATEMENT)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return amount;
    }

    @Override
    public int findLotByNameAmount(String name) throws DaoException {
        int amount = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOT_BY_NAME_STATEMENT)) {
            statement.setString(1, ANY_AMOUNT_SQL_CHARACTER + name + ANY_AMOUNT_SQL_CHARACTER);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return amount;
    }

    @Override
    public int findLotBySellerIdAmount(long sellerId) throws DaoException {
        int amount = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LOT_BY_SELLER_ID_STATEMENT)) {
            statement.setLong(1, sellerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return amount;
    }

    @Override
    public int findLotByBuyerIdAmount(long buyerId) throws DaoException {
        int amount = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_WON_LOT_BY_BUYER_ID_STATEMENT)) {
            statement.setLong(1, buyerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return amount;
    }

    @Override
    public int findAllUserAmount() throws DaoException {
        int amount = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_STATEMENT)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return amount;
    }

    @Override
    public int findUserByNameAmount(String name) throws DaoException {
        int amount = 0;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_NAME_STATEMENT)) {
            statement.setString(1, ANY_AMOUNT_SQL_CHARACTER + name + ANY_AMOUNT_SQL_CHARACTER);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                amount = resultSet.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return amount;
    }
}
