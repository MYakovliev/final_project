package com.epam.web.dao.impl;


import com.epam.web.dao.DaoException;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;
import com.epam.web.pool.ConnectionPoolException;
import com.epam.web.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final String LOGIN_STATEMENT = "SELECT idusers, name, mail, balance, roles.role, avatar " +
            "FROM users INNER JOIN roles ON users.role = roles.idroles WHERE login=? AND password=?";
    private static final String REGISTRATION_STATEMENT =
            "INSERT INTO users (name, mail, login, password, role) " +
                    "VALUE (?,?,?,?,(SELECT idroles FROM roles WHERE roles.role=?))";
    private static final String UPDATE_BID_STATUS_STATEMENT =
            "UPDATE bid_history SET status=(SELECT idstatus FROM status WHERE status.status='LOSE') " +
                    "WHERE id_lot=? AND status=(SELECT idstatus FROM status WHERE status.status='WINING')";
    private static final String MAKE_BID_STATEMENT =
            "INSERT INTO bid_history (id_buyer, id_lot, bid, status) " +
                    "VALUES (?,?,?,(SELECT idstatus FROM status WHERE status.status='WINING'))";
    private static final String FIND_USERS_BY_ID_STATEMENT =
            "SELECT idusers, name, mail, balance, roles.role, avatar " +
                    "FROM users INNER JOIN roles ON users.role = roles.idroles WHERE idusers=?";
    private static final String FIND_USERS_BY_NAME_STATEMENT =
            "SELECT idusers, name, mail, balance, roles.role, avatar " +
                    "FROM users INNER JOIN roles ON users.role = roles.idroles WHERE name=?";
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String FIND_BUYER_BY_LOT_ID_STATEMENT =
            "SELECT idusers, name, mail, balance, roles.role, avatar FROM bid_history " +
                    "INNER JOIN users ON bid_history.id_buyer = users.idusers " +
                    "INNER JOIN roles ON users.role = roles.idroles WHERE id_lot=?";
    private static final String FIND_ALL_USERS_STATEMENT =
            "SELECT idusers, name, mail, balance, roles.role, avatar FROM users " +
                    "INNER JOIN roles ON users.role = roles.idroles";
    private static final String BAN_STATEMENT = "SELECT isBanned FROM users WHERE idusers=?";

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public Optional<User> login(String login, String password) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(LOGIN_STATEMENT)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(createUser(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public void register(String name, String mail, String login, String password, UserRole role) throws DaoException {
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(REGISTRATION_STATEMENT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, mail);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setString(5, role.toString());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }
//fixme use update bid status
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

    @Override
    public Optional<User> findUserById(int id) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_ID_STATEMENT)) {
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(createUser(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_STATEMENT)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public List<User> findUserByName(String name) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_NAME_STATEMENT)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public List<User> findBuyersHistory(long lotId) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BUYER_BY_LOT_ID_STATEMENT)) {
            statement.setLong(1, lotId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(createUser(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public boolean isBanned(long userId) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(BAN_STATEMENT)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                result = resultSet.getBoolean(1);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return result;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String mail = resultSet.getString(3);
        BigDecimal balance = resultSet.getBigDecimal(4);
        String stringRole = resultSet.getString(5);
        String avatar = resultSet.getString(6);
        UserRole role = UserRole.valueOf(stringRole.toUpperCase());
        User user = new User(id, name, mail, balance, role, avatar);
        return user;
    }
}
