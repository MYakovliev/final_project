package com.epam.web.dao;

/**
 * The interface provides action for admin dao
 *
 * @author Nikita Yakovlev
 */
public interface AdminDao {
    /**
     * To ban user by id
     *
     * @param userId to ban
     * @throws DaoException
     */
    void ban(long userId) throws DaoException;

    /**
     * To unban user by id
     * @param userId to unban
     * @throws DaoException
     */
    void unban(long userId) throws DaoException;

    /**
     * To submit that exact user won the lot
     * @param userId the id of buyer
     * @param lotId lot id that is submitting
     * @throws DaoException
     */
    void submitWinner(long userId, long lotId) throws DaoException;
}
