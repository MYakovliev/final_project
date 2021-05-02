package com.epam.web.dao;

import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface provides action for getting users and their info from database
 *
 * @author Nikita Yakovlev
 */
public interface UserDao {
    /**
     * Method that allows user to sign in
     *
     * @param login user login
     * @param password encoded user password
     * @return User wrapped by optional or Optional.empty() if no user found
     * @throws DaoException
     */
    Optional<User> login(String login, String password) throws DaoException;

    /**
     * Method to put a new user into database
     *
     * @param name user name
     * @param mail user mail
     * @param login user login
     * @param password user password
     * @param role user role
     * @throws DaoException
     */
    void register(String name, String mail, String login, String password, UserRole role) throws DaoException;

    /**
     * Method for buyer to make a bid on exact lot
     *
     * @param userId
     * @param bid
     * @param lotId
     * @throws DaoException
     */
    void makeBid(long userId, BigDecimal bid, long lotId) throws DaoException;

    /**
     * Method to change user info (avatar, name, mail) by user id
     *
     * @param userId
     * @param avatar
     * @param name
     * @param mail
     * @throws DaoException
     */
    void changeUserData(long userId, String avatar, String name, String mail) throws DaoException;

    /**
     * Method to change user password
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @throws DaoException
     */
    void changeUserPassword(long userId, String oldPassword, String newPassword) throws DaoException;

    /**
     * Method to add some money to user balance
     *
     * @param userId
     * @param payment
     * @throws DaoException
     */
    void addBalance(long userId, BigDecimal payment) throws DaoException;

    /**
     * Method to get user from database by id
     *
     * @param id
     * @return user wrapped by Optional or Optional.empty() if no user found
     * @throws DaoException
     */
    Optional<User> findUserById(long id) throws DaoException;

    /**
     * Method to get list of users in a row but exact amount from start point
     *
     * @param start  start point to count lots from
     * @param amount amount of users to get
     * @return List of users or empty list if no users found
     * @throws DaoException
     */
    List<User> findAll(int start, int amount) throws DaoException;

    /**
     * Method to get list of users contain exact string in their name but exact amount from start point
     *
     * @param name   string to find
     * @param start  start point to count from
     * @param amount amount of users to get
     * @return List of users or empty list if no users found
     * @throws DaoException
     */
    List<User> findUserByName(String name, int start, int amount) throws DaoException;

    /**
     * Method to get list of users that made bid on exact lot
     *
     * @param lotId
     * @return List of users or empty list if no users found
     * @throws DaoException
     */
    List<User> findBuyersHistory(long lotId) throws DaoException;

    /**
     * Method to check if exact user is banned
     *
     * @param userId
     * @return true if user is banned or else false
     * @throws DaoException
     */
    boolean isBanned(long userId) throws DaoException;

    /**
     * Method to check if any user already has login
     *
     * @param login
     * @return true if login is already taken or else false
     * @throws DaoException
     */
    boolean isTaken(String login) throws DaoException;
}
