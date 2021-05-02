package com.epam.web.service;

import com.epam.web.entity.Lot;
import com.epam.web.entity.User;
import com.epam.web.entity.UserRole;

import java.util.List;

/**
 * The interface provides action for user
 */
public interface UserService {
    /**
     * Service to sign in user
     *
     * @param login
     * @param password
     * @return
     * @throws ServiceException
     */
    User login(String login, String password) throws ServiceException;

    /**
     * Service to sign up user
     *
     * @param name
     * @param mail
     * @param login
     * @param password
     * @param role
     * @throws ServiceException
     */
    void register(String name, String mail, String login, String password, UserRole role) throws ServiceException;

    /**
     * Service for buyer to make bid
     *
     * @param user
     * @param bid
     * @param lot
     * @throws ServiceException
     */
    void makeBid(User user, String bid, Lot lot) throws ServiceException;

    /**
     * Service to find user by id
     *
     * @param id
     * @return found user
     * @throws ServiceException
     */
    User findUserById(long id) throws ServiceException;

    /**
     * Service to change user data (avatar, name, mail)
     *
     * @param userId
     * @param avatar
     * @param name
     * @param mail
     * @throws ServiceException
     */
    void changeUserData(long userId, String avatar, String name, String mail) throws ServiceException;

    /**
     * Service to change user password
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @throws ServiceException
     */
    void changeUserPassword(long userId, String oldPassword, String newPassword) throws ServiceException;

    /**
     * Service to add some money to user account
     *
     * @param userId
     * @param payment
     * @throws ServiceException
     */
    void addBalance(long userId, String payment) throws ServiceException;

    /**
     * Service to find list of users that has exact string in their name
     *
     * @param name
     * @param pageNumber number of page for paging
     * @param amountPerPage amount of users per one page
     * @return list of users or empty list if no users found
     * @throws ServiceException
     */
    List<User> findUserByName(String name, int pageNumber, int amountPerPage) throws ServiceException;


    /**
     * Service to find all buyers that bid the lot
     *
     * @param lotId
     * @return list of users or empty list if no users found
     * @throws ServiceException
     */
    List<User> findBuyersHistory(long lotId) throws ServiceException;

    /**
     * Service to check if user is banned
     *
     * @param userId
     * @return true if user is banned or else false
     * @throws ServiceException
     */
    boolean isBanned(long userId) throws ServiceException;

    /**
     * Service to find list of all users
     *
     * @param pageNumber number of page for paging
     * @param amountPerPage amount of users per one page
     * @return list of users or empty list if no users found
     * @throws ServiceException
     */
    List<User> findAll(int pageNumber, int amountPerPage) throws ServiceException;

    /**
     * Service to check if login user with such login already exists
     *
     * @param login
     * @return true if user exists or else false
     * @throws ServiceException
     */
    boolean isTaken(String login) throws ServiceException;
}
