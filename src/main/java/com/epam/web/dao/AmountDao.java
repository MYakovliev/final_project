package com.epam.web.dao;

/**
 * The interface provides action for getting a size of a list from database
 *
 * @author Nikita Yakovlev
 */
public interface AmountDao {
    /**
     * @return Amount of all lots in database
     * @throws DaoException
     */
    int findAllLotAmount() throws DaoException;

    /**
     * @return Amount of lots in database that are active now
     * @throws DaoException
     */
    int findActiveLotAmount() throws  DaoException;

    /**
     * @param name the name to look for
     * @return Amount of lots in database which has names that contains a string
     * @throws DaoException
     */
    int findLotByNameAmount(String name) throws DaoException;

    /**
     * @param sellerId
     * @return Amount of lots in database that are selling by specific seller
     * @throws DaoException
     */
    int findLotBySellerIdAmount(long sellerId) throws DaoException;

    /**
     * @param buyerId
     * @return Amount of lots in database that were bid by a specific buyer
     * @throws DaoException
     */
    int findLotByBuyerIdAmount(long buyerId) throws DaoException;

    /**
     * @return Amount of all users in database
     * @throws DaoException
     */
    int findAllUserAmount() throws DaoException;

    /**
     * @param name the name to look for
     * @return Amount of users in database which contain a string in their name
     * @throws DaoException
     */
    int findUserByNameAmount(String name) throws DaoException;
}
