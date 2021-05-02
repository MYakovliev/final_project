package com.epam.web.dao;

import com.epam.web.entity.Lot;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * The interface provides action for getting lots from database
 *
 * @author Nikita Yakovlev
 */
public interface LotDao {
    /**
     * Method to create a new lot and put it into database
     *
     * @param name
     * @param description
     * @param startBid
     * @param startTime
     * @param finishTime
     * @param sellerId
     * @param images
     * @return Lot wrapped by Optional
     * @throws DaoException
     */
    Optional<Lot> createNewLot(String name, String description, BigDecimal startBid, Timestamp startTime, Timestamp finishTime, long sellerId, List<String> images) throws DaoException;

    /**
     * The method to get lot from database by id
     *
     * @param id
     * @return lot from database wrapped by Optional or Optional.empty() if can't find lot
     * @throws DaoException
     */
    Optional<Lot> findLotById(long id) throws DaoException;

    /**
     * Method to get list of lots in a row but exact amount from start point
     *
     * @param start  start point to count lots from
     * @param amount amount of lots to get
     * @return List of lots or empty list if no lots found
     * @throws DaoException
     */
    List<Lot> findAll(int start, int amount) throws DaoException;

    /**
     * Method to get list of lots contain exact string in their name but exact amount from start point
     *
     * @param name   string to find
     * @param start  start point to count from
     * @param amount amount of lots to get
     * @return List of lots or empty list if no lots found
     * @throws DaoException
     */
    List<Lot> findLotByName(String name, int start, int amount) throws DaoException;

    /**
     * Method to get list of lots that were bid by exact buyer by buyer id but exact amount from start point
     *
     * @param buyerId
     * @param start   start point to count from
     * @param amount  amount of lots to get
     * @return List of lots or empty list if no lots found
     * @throws DaoException
     */
    List<Lot> findLotByBuyerId(long buyerId, int start, int amount) throws DaoException;

    /**
     * Method to get list of lots that are selling by exact seller by seller id but exact amount from start point
     *
     * @param sellerId
     * @param start   start point to count from
     * @param amount  amount of lots to get
     * @return List of lots or empty list if no lots found
     * @throws DaoException
     */
    List<Lot> findLotBySellerId(long sellerId, int start, int amount) throws DaoException;

    /**
     * Method to get list of lots that are active right now but exact amount from start point
     *
     * @param start start point to count from
     * @param amount amount of lots to get
     * @return List of lots or empty list if no lots found
     * @throws DaoException
     */
    List<Lot> findActive(int start, int amount) throws DaoException;

    /**
     * Method to find out if the lot by lot id has already been submitted by admin
     *
     * @param lotId id of the lot to check
     * @return true if lot has been submitted or else false
     * @throws DaoException
     */
    boolean isLotSubmitted(long lotId) throws DaoException;
}
