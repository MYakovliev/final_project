package com.epam.web.dao.impl;


import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.pool.ConnectionPoolException;
import com.epam.web.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final String LOGIN_STATEMENT = "SELECT name, mail FROM users WHERE login=? AND password=?";
    private static final String REGISTRATION_STATEMENT = "INSERT INTO users (name, mail, login, password) VALUE (?,?,?,?)";
    private static final String MAKE_BID_STATEMENT = "UPDATE lots SET bid=? WHERE idlots=?";
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    public Optional<User> login(String login, String password) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(LOGIN_STATEMENT)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(1);
                String mail = resultSet.getString(2);
                user = Optional.of(new User(name, mail));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }

        return user;
    }


    public void register(String name, String mail, String login, String password) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(REGISTRATION_STATEMENT)) {
            statement.setString(1, name);
            statement.setString(2, mail);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void makeBid(User user, BigDecimal bid, Lot lot) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(MAKE_BID_STATEMENT)) {
            statement.setString(1, String.valueOf(bid));
            statement.setString(2, String.valueOf(lot.getId()));
            statement.executeQuery();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }
}
